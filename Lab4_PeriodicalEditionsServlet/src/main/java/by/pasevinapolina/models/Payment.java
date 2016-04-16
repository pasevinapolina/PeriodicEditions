package by.pasevinapolina.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Contains Payment object
 * @author Polina Pasevina
 * @version 1.0
 */
@Entity
@Table(name = "payment")

@NamedQuery(name = "allPayments", query = "SELECT p FROM Payment p")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private long id;

    @Column(name = "pay_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date payDate;

    @Column(name = "pay_sum", nullable = false, precision = 2)
    private double paySum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    /**
     * Default constructor
     */
    public Payment() {
    }

    /**
     * Constructor
     * Initializes fields {@link Payment#id}, {@link Payment#subscription},
     * {@link Payment#payDate}, {@link Payment#paySum},
     * @param id Payment id
     * @param subscription Subscription to pay for
     * @param payDate Date of the payment
     * @param paySum Sum to pay
     */
    public Payment(long id, Subscription subscription,
                   Date payDate, double paySum) {
        this.id = id;
        this.subscription = subscription;
        this.payDate = payDate;
        this.paySum = paySum;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    /**
     * Gets payment id
     * @return Payment id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets new id for the payment
     * @param id Payment id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets date of the payment
     * @return Date of the payment
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * Sets new date of the payment
     * @param payDate New date of the payment
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * Gets sum to pay
     * @return Sum to pay
     */
    public double getPaySum() {
        return paySum;
    }

    /**
     * Sets new sum to pay
     * @param paySum New sum to pay
     */
    public void setPaySum(double paySum) {
        this.paySum = paySum;
    }

    @Override
    public String toString() {
        return "Payment: " +
                "id " + id +
                "\t Subscription id: " + subscription.getId() +
                "\t Pay Date: " + payDate +
                "\t Pay Sum: " + paySum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != payment.id) return false;
        if (Double.compare(payment.paySum, paySum) != 0) return false;
        if (payDate != null ? !payDate.equals(payment.payDate) : payment.payDate != null) return false;
        return !(subscription != null ? !subscription.equals(payment.subscription) : payment.subscription != null);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (payDate != null ? payDate.hashCode() : 0);
        temp = Double.doubleToLongBits(paySum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (subscription != null ? subscription.hashCode() : 0);
        return result;
    }
}
