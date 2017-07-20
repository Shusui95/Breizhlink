package dao;

import java.util.ArrayList;

/**
 * Dao : url click
 */
public interface UrlClickDao {
	public void addUrlClick(int urlId);
	public ArrayList getUrlClickByUrlId(int urlId);
}
