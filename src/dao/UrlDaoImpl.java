package dao;

import beans.Url;
import beans.User;
import utils.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Dao implemantation : url
 * @see dao.UrlDao
 */
public class UrlDaoImpl implements UrlDao {

    private Jdbc jdbc = new Jdbc();
    private Connection conn;

    /**
     * Add url with password
     * @param urlToAdd url to add
     * Fields : short url, long url, use init to 0, url password
     * @see beans.Url
     *
     * Note : use in index view, when user is connected or not
     */
    public void addUrlWithPassword(Url urlToAdd){
        String sql = "INSERT INTO url (url_long, url_short, utilisation, password) VALUES ( ?, ?, ?, ?)";
        try{
            Connection conn = this.jdbc.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, urlToAdd.getUrl_long());
            statement.setString(2, urlToAdd.getUrl_short());
            statement.setInt(3, 0);
            statement.setString(4, urlToAdd.getPassword());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new url was inserted successfully!");
            }
            statement.close();
            conn.close();
        }catch (Exception e){
            System.out.println("Exception throw : "+e);
        }
    }

    /**
     * Add classic url
     * @param urlToAdd url to add
     * Fields : short url, long url, use init to 0
     * @see beans.Url
     *
     * Note : use in index view, when user is connected or not
     */
    @Override
    public void addUrl(Url urlToAdd){
        String sql = "INSERT INTO url (url_long, url_short, utilisation) VALUES ( ?, ?, ?)";
        try{
            Connection conn = this.jdbc.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, urlToAdd.getUrl_long());
            statement.setString(2, urlToAdd.getUrl_short());
            statement.setInt(3, 0);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new url was inserted successfully!");
            }
            statement.close();
            conn.close();
        }catch (Exception e){
            System.out.println("Exception throw : "+e);
        }
    }

    /**
     * Generic add url
     * @param urlToAdd url to add
     * Fields required : short url, long url, use init to 0, user_id
     * Fields optional : date_expiration, period_start, max_use, captcha, password, user email
     * @see beans.Url
     *
     */
    @Override
    public void addUrlRac(Url urlToAdd){
        String sql = "INSERT INTO url (";

        sql += "url_long, url_short, utilisation";
        String values = ") VALUES ( ?, ?, ?";
        if(urlToAdd.getDate_expiration() != null){
            sql += ", date_expiration";
            values += ", ?";
            java.sql.Date date_expiration = new java.sql.Date(urlToAdd.getDate_expiration().getTime());
        }
        if(urlToAdd.getPeriod_start() != null){
            sql += ", period_start";
            values += ", ?";
            java.sql.Date periodStart = new java.sql.Date(urlToAdd.getPeriod_start().getTime());
        }
        if(urlToAdd.getCaptcha() == 1){
            sql += ", captcha";
            values += ", ?";
        }
        if(urlToAdd.getUser_id() != 0){
            sql += ", user_id";
            values += ", ?";
        }
        if(urlToAdd.getMax_use() != 0){
            sql += ", max_use";
            values += ", ?";
        }
        if(urlToAdd.getEmail() != null){
            sql += ", email";
            values += ", ?";
        }
        if(urlToAdd.getPassword() != null){
            sql += ", password";
            values += ", ?";
        }
        sql+=values+")";
        try{
            Connection conn = this.jdbc.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            int counter = 3;
            
            statement.setString(1, urlToAdd.getUrl_long());
            statement.setString(2, urlToAdd.getUrl_short());
            statement.setInt(3, 0);

            if(urlToAdd.getDate_expiration() != null){
                counter+=1;
                java.sql.Date date_expiration = new java.sql.Date(urlToAdd.getDate_expiration().getTime());
                statement.setDate(counter, date_expiration);
            }
            if(urlToAdd.getPeriod_start() != null){
                counter+=1;
                java.sql.Date periodStart = new java.sql.Date(urlToAdd.getPeriod_start().getTime());
                statement.setDate(counter, periodStart);
            }
            if(urlToAdd.getCaptcha() == 1){
                counter+=1;
                statement.setInt(counter, urlToAdd.getCaptcha());
            }
            if(urlToAdd.getUser_id() != 0){
                counter+=1;
                statement.setInt(counter, urlToAdd.getUser_id());
            }
            if(urlToAdd.getMax_use() != 0){
                counter+=1;
                statement.setInt(counter, urlToAdd.getMax_use());
            }
            if(urlToAdd.getEmail() != null){
                counter+=1;
                statement.setString(counter, urlToAdd.getEmail());
            }
            if(urlToAdd.getPassword() != null){
            	counter+=1;
                statement.setString(counter, urlToAdd.getPassword());
            }

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new url was inserted successfully!");
            }
            statement.close();
            conn.close();
        }catch (Exception e){
            System.out.println("Exception throw : "+e);
        }
    }

    /**
     * Update url use
     * @param urlToAdd url to increment
     * Field : url use, url id
     *
     * @see beans.Url
     */
    @Override
    public void updateUrlUse(Url urlToAdd){
        String sql = "UPDATE url SET utilisation = (?) WHERE id = (?)";
        try{
            Connection conn = this.jdbc.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, urlToAdd.getUtilsation());
            statement.setInt(2, urlToAdd.getId());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new url was inserted successfully!");
            }
            statement.close();
            conn.close();
        }catch (Exception e){
            System.out.println("Exception throw : "+e);
        }
    }

    /**
     * Get url by short url
     * @param urlSearch url to search
     * Field : short url
     * @return bean url find
     *
     * Note : init disponible to 1
     *        check if use < max_use and period_start < today < date_expiration
     *        else set disponible to 0
     *
     * @see beans.Url
     */
    @Override
    public Url getUrlByShortUrl(Url urlSearch){
        String sql = "SELECT * FROM url WHERE url_short = (?)";
        Url urlGet = null;
        try{
            Connection conn = this.jdbc.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, urlSearch.getUrl_short());
            ResultSet result = statement.executeQuery();
            Date today = new Date();
            while (result.next()){
                urlGet = new Url();
                urlGet.setId(result.getInt("id"));
                urlGet.setUrl_long(result.getString("url_long"));
                urlGet.setUrl_short(result.getString("url_short"));
                urlGet.setUtilsation(result.getInt("utilisation"));
                urlGet.setPassword(result.getString("password"));
                urlGet.setCaptcha(result.getInt("captcha"));
                urlGet.setEmail(result.getString("email"));
                urlGet.setPeriod_start(result.getDate("period_start"));
                urlGet.setDate_expiration(result.getDate("date_expiration"));
                urlGet.setMax_use(result.getInt("max_use"));
                urlGet.setUser_id(result.getInt("user_id"));
                urlGet.setDisponible(1);
                
                if (urlGet.getPeriod_start() != null && today.before(urlGet.getPeriod_start())){

                    urlGet.setDisponible(0);
                }
                if (urlGet.getDate_expiration() != null && today.after(urlGet.getDate_expiration())){

                    urlGet.setDisponible(0);
                }
                if (urlGet.getMax_use() != 0 && urlGet.getUtilsation() > urlGet.getMax_use()){

                    urlGet.setDisponible(0);
                }
            }
            statement.close();
            conn.close();
            return urlGet;
        }catch (Exception e){
            System.out.println("Exception throw : "+e.getMessage());
            return null;
        }
    }

    /**
     * Get url by long url
     * @param urlSearch url to search
     * Field = long url
     * @return url find
     *
     * @see beans.Url
     */
    @Override
    public Url getUrlByLongUrl(Url urlSearch){
        String sql = "SELECT * FROM url WHERE url_long = (?)";
        Url urlGet = null;
        try{
            Connection conn = this.jdbc.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, urlSearch.getUrl_long());
            ResultSet result = statement.executeQuery();

            while (result.next()){
                urlGet = new Url();
                urlGet.setId(result.getInt("id"));
                urlGet.setUrl_long(result.getString("url_long"));
                urlGet.setUrl_short(result.getString("url_short"));
                urlGet.setUtilsation(result.getInt("utilisation"));

            }
            statement.close();
            conn.close();
            return urlGet;
        }catch (Exception e){
            System.out.println("Exception throw : "+e.getMessage());
            return null;
        }
    }

    /**
     * Get user list urls
     * @param userUrl user to search
     * Field : user_id
     * @return url list
     *
     * @see beans.Url
     */
    @Override
    public ArrayList<beans.Url> getUrlsByUser(User userUrl){
        String sql = "SELECT * FROM url WHERE user_id = (?)";
        ArrayList<Url> urlsList = new ArrayList<Url>();
        Url urlGet = null;
        try{
            Connection conn = this.jdbc.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userUrl.getId());
            ResultSet result = statement.executeQuery();
            Date today = new Date();
            while (result.next()){
                urlGet = new Url();
                urlGet.setUrl_short(result.getString("url_short"));
                urlGet.setUrl_long(result.getString("url_long"));
                urlGet.setMax_use(result.getInt("max_use"));
                urlGet.setPeriod_start(result.getDate("period_start"));
                urlGet.setUtilsation(result.getInt("utilisation"));
                urlGet.setDate_expiration(result.getDate("date_expiration"));
                urlGet.setDate_created(result.getDate("date_created"));
                urlGet.setDisponible(1);
                
                if (urlGet.getPeriod_start() != null && today.before(urlGet.getPeriod_start())){
                    urlGet.setDisponible(0);
                }
                if (urlGet.getDate_expiration() != null && today.after(urlGet.getDate_expiration())){
                    urlGet.setDisponible(0);
                }
                if (urlGet.getMax_use() != 0 && urlGet.getUtilsation() > urlGet.getMax_use()){
                    urlGet.setDisponible(0);
                }
                urlsList.add(urlGet);
                urlGet = null;
            }
            statement.close();
            conn.close();
            return urlsList;
        }catch (Exception e){
            System.out.println("Exception throw : "+e.getMessage());
            return null;
        }
    }

    /**
     * Get url list
     * @return all url list
     *
     * @see beans.Url
     */
    public List<Url> getUrls(){
        String sql = "SELECT * FROM url";
        List<Url> urlsList = new ArrayList<Url>();
        Url urlGet;
        try{
            Connection conn = this.jdbc.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                urlGet = new Url();
                urlGet.setId(result.getInt("id"));
                urlGet.setUrl_long(result.getString("url_long"));
                urlGet.setUrl_short(result.getString("url_short"));
                urlGet.setUtilsation(result.getInt("utilisation"));
                urlsList.add(urlGet);
                urlGet = null;
            }
            statement.close();
            conn.close();
            return urlsList;
        }catch (Exception e){
            System.out.println("Exception throw : "+e.getMessage());
            return null;
        }
    }

    /**
     * Get last url id
     * @return int url id
     *
     * @see beans.Url
     */
    public int getLastId(){
        String sql = "SELECT id FROM url ORDER BY id DESC LIMIT 0,1";
        Url urlGet = new Url();
        try{
            Connection conn = this.jdbc.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                urlGet.setId(result.getInt("id"));
                System.out.println("request : "+result.getInt("id"));
            }
            statement.close();
            conn.close();
            return urlGet.getId();
        }catch (Exception e){
            System.out.println("Exception throw : "+e.getMessage());
            return 0;
        }
    }
}
