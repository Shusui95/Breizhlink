package beans;

import java.util.Date;

/**
 * Bean : User
 * Fields : id, email, login, password, statut, activated, confirmAccount, created_date
 */
public class User {

    private int id;
    private String email;
    private String login;
    private String password;
    private String statut;
    private int activated;
    private String confirmAccount;
    private Date created_date;

    public User() {}

    public User(int id, String email, String login, String password, String statut, int activated, String confirmAccount, Date created_date) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.password = password;
        this.statut = statut;
        this.activated = activated;
        this.confirmAccount = confirmAccount;
        this.created_date = created_date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", statut='" + statut + '\'' +
                ", activated=" + activated +
                ", confirmAccount='" + confirmAccount + '\'' +
                '}';
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getActivated() {
        return activated;
    }

    public void setActivated(int activated) {
        this.activated = activated;
    }

    public String getConfirmAccount() {
        return confirmAccount;
    }

    public void setConfirmAccount(String confirmAccount) {
        this.confirmAccount = confirmAccount;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
