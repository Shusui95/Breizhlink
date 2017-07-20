package beans;

import java.util.Date;

/**
 * Bean : Url
 * Fields : id, url_long, url_short, utilisation, date_expiration,
 *          captcha flag, period_start, email, max_use, date_created, user_id,
 *          disponible flag, showUrl flag
 */
public class Url {

    private int id;
    private String url_long;
    private String url_short;
    private int utilsation;
    private Date date_expiration;
    private int captcha;
    private Date period_start;
    private String email;
    private int max_use;
    private Date date_created;
    private int user_id;
    private int disponible;
    private int showUrl;

    public Url() {}

    public Date getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }

    public int getCaptcha() {
        return captcha;
    }

    public void setCaptcha(int captcha) {
        this.captcha = captcha;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }
    public Date getPeriod_start() {
        return period_start;
    }

    public void setPeriod_start(Date period_start) {
        this.period_start = period_start;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public int getUtilsation() {
        return utilsation;
    }

    public int getMax_use() {
        return max_use;
    }

    public void setMax_use(int max_use) {
        this.max_use = max_use;
    }

    public void setUtilsation(int utilsation) {
        this.utilsation = utilsation;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public int getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(int showUrl) {
        this.showUrl = showUrl;
    }

    public Url(int id, String url_long, String url_short, int utilsation, Date date_expiration, int captcha, Date period_start, String email, int max_use, Date date_created, int user_id, int disponible, int showUrl, String password) {
        this.id = id;
        this.url_long = url_long;
        this.url_short = url_short;
        this.utilsation = utilsation;
        this.date_expiration = date_expiration;
        this.captcha = captcha;
        this.period_start = period_start;
        this.email = email;
        this.max_use = max_use;
        this.date_created = date_created;
        this.user_id = user_id;
        this.disponible = disponible;
        this.showUrl = showUrl;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", url_long='" + url_long + '\'' +
                ", url_short='" + url_short + '\'' +
                ", utilsation=" + utilsation +
                ", date_expiration=" + date_expiration +
                ", captcha=" + captcha +
                ", period_start=" + period_start +
                ", email='" + email + '\'' +
                ", max_use=" + max_use +
                ", date_created=" + date_created +
                ", user_id=" + user_id +
                ", disponible=" + disponible +
                ", showUrl=" + showUrl +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl_long() {
        return url_long;
    }

    public void setUrl_long(String url_long) {
        this.url_long = url_long;
    }

    public String getUrl_short() {
        return url_short;
    }

    public void setUrl_short(String url_short) {
        this.url_short = url_short;
    }
}
