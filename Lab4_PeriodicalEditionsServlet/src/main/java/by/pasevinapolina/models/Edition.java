package by.pasevinapolina.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Contains Edition object
 * @author Polina Pasevina
 * @version 1.0
 */

@Entity
@Table(name = "edition")

@NamedQueries({
        @NamedQuery(name = "allEditions", query = "SELECT e FROM Edition e"),
        @NamedQuery(name = "readerEditions",
                query = "SELECT s.edition FROM Subscription s WHERE s.reader.id = :readerId")
})
public class Edition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "edition_id")
    private long id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "output_frequency")
    private int outputFrequency;

    @OneToMany(mappedBy = "edition")
    List<Subscription> subscriptions;

    /**
     * Default constructor
     */
    public Edition() {
        subscriptions = new ArrayList<Subscription>();
    }

    /**
     * Constructor.
     * Initializes fields {@link Edition#id}, {@link Edition#author},
     * {@link Edition#name}, {@link Edition#outputFrequency}
     * @param id Edition id
     * @param author Edition author
     * @param name Name of the edition
     * @param outputFrequency How often is it published
     */
    public Edition(long id, String author, String name, int outputFrequency) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.outputFrequency = outputFrequency;
        this.subscriptions = new ArrayList<Subscription>();
    }

    /**
     * Gets edition id
     * @return Edition id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets new id for edition
     * @param id Edition id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets output frequency
     * @return Output frequency
     */
    public int getOutputFrequency() {
        return outputFrequency;
    }

    /**
     * Sets new output frequency
     * @param outputFrequency New output frequency
     */
    public void setOutputFrequency(int outputFrequency) {
        this.outputFrequency = outputFrequency;
    }

    /**
     * Gets name of the edition
     * @return Name of the edition
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new name of the edition
     * @param name New name of the edition
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets edition author
     * @return Edition author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets new edition author
     * @param author New edition author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets list of subscriptions
     * @return List of subscriptions
     */
    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    /**
     * Sets new list of subscriptions
     * @param subscriptions New list of subscriptions
     */
    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public String toString() {
        return "Edition: " + "id " + id +
                "\t Author: '" + author + '\'' +
                "\t Name: '" + name + '\'' +
                "\t Output Frequency: once in " + outputFrequency + " days.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edition edition = (Edition) o;

        if (id != edition.id) return false;
        if (outputFrequency != edition.outputFrequency) return false;
        if (author != null ? !author.equals(edition.author) : edition.author != null) return false;
        if (name != null ? !name.equals(edition.name) : edition.name != null) return false;
        return !(subscriptions != null ? !subscriptions.equals(edition.subscriptions) : edition.subscriptions != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + outputFrequency;
        return result;
    }
}
