package beans;

import java.util.Date;

/**
 * Bean : urlClick
 * Fields : id, date_created, url_id
 */
public class UrlClick {
	private int id;
    private Date date_created;
    private int url_id;

    public UrlClick() {
    }

    public UrlClick(int id, Date date_created, int url_id) {
        this.id = id;
        this.date_created = date_created;
        this.url_id = url_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public int getUrl_id() {
        return url_id;
    }

    public void setUrl_id(int url_id) {
        this.url_id = url_id;
    }
}
