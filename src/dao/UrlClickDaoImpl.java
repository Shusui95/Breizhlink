package dao;

import utils.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *  Dao implementation : UrlClick
 *  @see dao.UrlClickDao
 */
public class UrlClickDaoImpl implements UrlClickDao{

	private Jdbc jdbc = new Jdbc();
	private Connection conn;

    /**
     * Add url click with the current datetime
     * @param urlId - int url id
     */
	@Override
	public void addUrlClick(int urlId) {
		String sql = "INSERT INTO urlclick (url_id) VALUES (?)";
		try{
			Connection conn = this.jdbc.connect();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, urlId);

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
     * Get url click by url id
     * @param urlId - int url id
     * @return List of url click counter group by month
     * Format : [{ 'count' : 12, 'month': 4, 'year': 17}, ...]
     */
	@Override
	public ArrayList getUrlClickByUrlId(int urlId) {
		String sql = "SELECT count(*) as count,"
				+ " MONTH(created_date) as month, "
				+ "YEAR(created_date) as year "
				+ "FROM `urlclick` where url_id = (?) group by YEAR(created_date), MONTH(created_date) order by created_date asc";
		ArrayList urlsList = new ArrayList();
		Map<String, String> urlGet = new HashMap<String, String>();
		
		try{
			Connection conn = this.jdbc.connect();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, urlId);
			ResultSet result = statement.executeQuery();
		
			while (result.next()){
				urlGet = new HashMap<String, String>();
				urlGet.put("count", result.getString("count"));
				urlGet.put("month", result.getString("month"));
				urlGet.put("year", result.getString("year"));
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

}
