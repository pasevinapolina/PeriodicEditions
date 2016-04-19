package by.pasevinapolina.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains Subscription object
 * @author Polina Pasevina
 * @version 1.0
 */
@Entity
@Table(name = "subscription")

@NamedQueries({
        @NamedQuery(name = "allSubscriptions", query = "SELECT s FROM Subscription s"),
        @NamedQuery(name = "readerSubscriptions", query = "SELECT s FROM Subscription s WHERE s.reader.id = :readerId")
})

public class Subscription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subscription_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "edition_id")
    private Edition edition;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @Column(name = "duration")
    private int duration;

    @OneToMany(mappedBy = "subscription")
    private List<Payment> payments;

    private boolean paid;

    /**
     * Default constructor
     */
    public Subscription() {
        payments = new ArrayList<Payment>();
    }

    /**
     * Constructor.
     * Initializes fields {@link Subscription#id}, {@link Subscription#edition},
     * {@link Subscription#reader}, {@link Subscription#duration}
     * @param id Subscription id
     * @param edition Edition to subscript to
     * @param reader Reader who subscripts
     * @param duration Duration of the subscription
     */
    public Subscription(long id, Edition edition,
                        Reader reader, int duration) {
        this.id = id;
        this.edition = edition;
        this.reader = reader;
        this.duration = duration;
        this.payments = new ArrayList<Payment>();
        this.paid = false;
    }

    /**
     * Checks whether the subscription is paid
     * @return True if the subscription is paid, false otherwise
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Sets subscription as paid
     * @param paid True if the subscription is paid, false otherwise
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /**
     * Gets subscription id
     * @return Subscription id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets new id for the subscription
     * @param id Subscription id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets edition
     * @return Edition
     */
    public Edition getEdition() {
        return edition;
    }

    /**
     * Sets new edition
     * @param edition New edition
     */
    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    /**
     * Gets reader
     * @return Reader
     */
    public Reader getReader() {
        return reader;
    }

    /**
     * Sets new reader
     * @param reader New reader
     */
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    /**
     * Gets subscription duration
     * @return Subscription duration in days
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets new subscription duration
     * @param duration New subscription duration in days
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets list of payments
     * @return List of payments
     */
    public List<Payment> getPayments() {
        return payments;
    }

    /**
     * Sets list of payments
     * @param payments List of payments
     */
    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Subscription: " +
                "id " + id +
                "\t Edition id: " + edition.getId() +
                "\t Reader id: " + reader.getId() +
                "\t Duration: " + duration + " days";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (id != that.id) return false;
        if (duration != that.duration) return false;
        if (edition != null ? !edition.equals(that.edition) : that.edition != null) return false;
        if (reader != null ? !reader.equals(that.reader) : that.reader != null) return false;
        return !(payments != null ? !payments.equals(that.payments) : that.payments != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (edition != null ? edition.hashCode() : 0);
        result = 31 * result + (reader != null ? reader.hashCode() : 0);
        result = 31 * result + duration;
        return result;
    }
}
