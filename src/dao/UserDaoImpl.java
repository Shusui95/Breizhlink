package dao;

import beans.User;
import utils.Jdbc;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao implementation : User
 * @see dao.UserDao
 */
public class UserDaoImpl implements UserDao {

    private Jdbc jdbc = new Jdbc();
    private Connection conn;


    /**
     * Encrypt into SHA1
     * @param stringToEncrypt - password to encrypt
     * @return SHA1 encryption
     */
    public String encryptSHA(String stringToEncrypt){
        MessageDigest md;
        try{
            md = MessageDigest.getInstance("SHA-1");
            md.reset();
            md.update(stringToEncrypt.getBytes());
            String pwd = new String(md.digest(), StandardCharsets.UTF_8);
            return pwd;

        }catch (Exception e){
            System.out.println("Exception throw : "+e.getMessage());
        }
        return null;

    }

    /**
     * @param emailToCheck - string to check
     * @return true if valid, false if not
     */
    public boolean validEmail(String emailToCheck){
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        Boolean b = emailToCheck.matches(EMAIL_REGEX);
        return b;
    }

    /**
     * Add User in DB
     * @param user - User object to insert
     * Fields : login, email, password, statut, activated, confirmAccount
     *
     * @see beans.User
     */
    @Override
    public void addUser(User user){
        String sql = "INSERT INTO users (login, email, password, statut, activated, confirmAccount) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            Connection conn = this.jdbc.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, encryptSHA(user.getPassword()));
            statement.setString(4, user.getStatut());
            statement.setInt(5, 0);

            statement.setString(6, user.getConfirmAccount());
            statement.executeUpdate();
            statement.close();
            conn.close();
        }catch (Exception e){
            System.out.println("Exception throw : "+e.getMessage());
        }
    }

    /**
     * Active an user
     * @param user - user to active
     * Field : activated, email
     *
     * @see beans.User
     */
    @Override
    public void activateUser(User user){
        String sql = "UPDATE users SET activated = ? WHERE email = ?";
        try{
            Connection conn = this.jdbc.connect();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, user.getActivated());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();
            statement.close();
            conn.close();
        }catch (Exception e){
            System.out.println("e "+e.toString());
            System.out.println("Exception throw : "+e.getMessage());
        }
    }

    /**
     * Get last user id
     * @return - int id
     */
    @Override
    public int getLastId(){
        String sql = "SELECT id FROM user ORDER BY id DESC LIMIT 0,1";
        User userGet = new User();
        try{
            Connection conn = this.jdbc.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
            	userGet.setId(result.getInt("id"));
                System.out.println("request : "+result.getInt("id"));
            }
            statement.close();
            conn.close();
            return userGet.getId();
        }catch (Exception e){
            System.out.println("Exception throw : "+e.getMessage());
            return 0;
        }
    }

    /**
     * Get user by email
     * @param userSearch - user to search with email
     * Field : email
     * @return - user find
     *
     * @see beans.User
     */
    @Override
    public User getUserByEmail(User userSearch){
        String sql = "SELECT * FROM users WHERE email = (?)";
        User userGet = null;
        try{
            Connection conn = this.jdbc.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userSearch.getEmail());
            ResultSet result = statement.executeQuery();

            while (result.next()){
                userGet = new User();
                userGet.setId(result.getInt("id"));
                userGet.setLogin(result.getString("login"));
                userGet.setEmail(result.getString("email"));
                userGet.setPassword(result.getString("password"));
                userGet.setStatut(result.getString("statut"));
            }
            statement.close();
            conn.close();
            return userGet;
        }catch (Exception e){
            System.out.println("Exception throw : "+e.toString());
            return null;
        }
    }

    /**
     * Get user by id
     * @param userSearch - user to search with id
     * Field : id
     * @return - user find
     *
     * @see beans.User
     */
    @Override
    public User getUserById(User userSearch){
        String sql = "SELECT * FROM users WHERE id = (?)";
        User userGet = null;
        try{
            Connection conn = this.jdbc.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userSearch.getId());
            ResultSet result = statement.executeQuery();

            while (result.next()){
            	
                userGet = new User();
                userGet.setId(result.getInt("id"));
                userGet.setLogin(result.getString("login"));
                userGet.setEmail(result.getString("email"));
                userGet.setPassword(result.getString("password"));
                userGet.setStatut(result.getString("statut"));
                userGet.setActivated(result.getInt("activated"));
                userGet.setConfirmAccount(result.getString("confirmAccount"));
                userGet.setCreated_date(result.getDate("created_date"));
            }
            
            statement.close();
            conn.close();
            return userGet;
        }catch (Exception e){
            System.out.println("Exception throw : "+e.toString());
            return null;
        }
    }

    /**
     * Get user by login and password
     * @param userSearch - user to search with login / password
     * @return - user find
     *
     * @see beans.User
     */
    @Override
    public User getUserByLoginAndPassword(User userSearch){
        String sql = "SELECT * FROM users WHERE login = (?) AND password = (?)";
        User userGet = null;
        System.out.println("auth : "+userSearch.toString());
        System.out.println("mdp hash "+encryptSHA(userSearch.getPassword()));
        try{
            Connection conn = this.jdbc.connect();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userSearch.getLogin());
            statement.setString(2, userSearch.getPassword());
            ResultSet result = statement.executeQuery();

            while (result.next()){
                userGet = new User();
                userGet.setId(result.getInt("id"));
                userGet.setLogin(result.getString("login"));
                userGet.setEmail(result.getString("email"));
                userGet.setPassword(result.getString("password"));
                userGet.setStatut(result.getString("statut"));
                userGet.setActivated(result.getInt("activated"));
                userGet.setConfirmAccount(result.getString("confirmAccount"));
            }
            statement.close();
            conn.close();
            return userGet;
        }catch (Exception e){
            System.out.println("Exception throw : "+e.toString());
            return null;
        }
    }

    /**
     * Get users list
     * @return user list
     *
     * @see beans.User
     */
    @Override
    public List<User> getUsers(){
        String sql = "SELECT * FROM users";
        List<User> usersList = new ArrayList<User>();
        User userGet;
        try{
            Connection conn = this.jdbc.connect();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                userGet = new User();
                userGet.setId(result.getInt("id"));
                userGet.setLogin(result.getString("login"));
                userGet.setEmail(result.getString("email"));
                userGet.setPassword(result.getString("password"));
                userGet.setStatut(result.getString("statut"));
                usersList.add(userGet);
                userGet = null;
            }
            statement.close();
            conn.close();
            return usersList;
        }catch (Exception e){
            System.out.println("Exception throw : "+e.toString());
            return null;
        }
    }
}
