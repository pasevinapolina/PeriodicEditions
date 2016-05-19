package by.pasevinapolina.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains Reader object
 * @author Polina Pasevina
 * @version 1.0
 */
@Entity
@Table(name = "reader")

@NamedQueries({
        @NamedQuery(name = "allReaders", query = "SELECT r FROM Reader r"),
        @NamedQuery(name = "allReadersWithRole", query = "SELECT r FROM Reader r WHERE r.userRole= :userRole"),
        @NamedQuery(name = "loginReader",
                query = "SELECT r FROM Reader r WHERE r.login= :login AND r.password = :password")})
public class Reader implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reader_id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_role")
    private int userRole;

    @OneToMany(mappedBy = "reader")
    List<Subscription> subscriptions;

    /**
     * Default constructor
     */
    public Reader() {
        subscriptions = new ArrayList<Subscription>();
    }

    /**
     * Constructor. Initializes fields {@link #name}, {@link #login}, {@link #password}, {@link #userRole}
     * @param name Reader name
     * @param login Reader login
     * @param password Password
     * @param userRole User role
     */
    public Reader(String name, String login, String password, int userRole) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }

    /**
     * Gets reader id
     * @return Reader id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets new id for the reader
     * @param id Reader id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets reader name
     * @return Reader name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new reader name
     * @param name New reader name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets user login
     * @return Login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets new login
     * @param login New login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets new password
     * @param password Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets user role
     * @return Role
     */
    public int getUserRole() {
        return userRole;
    }

    /**
     * Sets new role for user
     * @param userRole New user role
     */
    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    /**
     * Gets list of subscriptions
     * @return List of subscriptions
     */
    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    /**
     * Sets list of subscriptions
     * @param subscriptions New list of subscriptions
     */
    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public String toString() {
        return "Reader: " +
                "id " + id +
                "\t Name='" + name + "\'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reader reader = (Reader) o;

        if (id != reader.id) return false;
        if (name != null ? !name.equals(reader.name) : reader.name != null) return false;
        return !(subscriptions != null ? !subscriptions.equals(reader.subscriptions) : reader.subscriptions != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
