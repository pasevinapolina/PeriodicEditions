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

@NamedQuery(name = "allReaders", query = "SELECT r FROM Reader r")
public class Reader implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reader_id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "reader")
    List<Subscription> subscriptions;

    /**
     * Default constructor
     */
    public Reader() {
        subscriptions = new ArrayList<Subscription>();
    }

    /**
     * Constructor.
     * Initializes fields {@link Reader#id}, {@link Reader#name}
     * @param id Reader id
     * @param name Reader name
     */
    public Reader(long id, String name) {
        this.id = id;
        this.name = name;
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
