package dao;

import beans.Url;
import beans.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Dao : Url
 *
 * @see beans.Url
 */
public interface
UrlDao {
    public void addUrl(Url url);
    public void addUrlRac(Url url);
    public void updateUrlUse(Url url);
    public void addUrlWithPassword(Url url);
    public Url getUrlByShortUrl(Url url);
    public Url getUrlByLongUrl(Url url);
    public List<Url> getUrls();
    public int getLastId();
    public ArrayList<beans.Url> getUrlsByUser(User userUrl);
}
