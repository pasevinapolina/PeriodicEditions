package by.pasevinapolina.dao;

import by.pasevinapolina.models.Payment;
import by.pasevinapolina.models.Subscription;
import by.pasevinapolina.utils.DAOException;

import java.util.Date;
import java.util.List;

/**
 * Interface for Payment DAO
 * @author Polina Pasevina
 * @version 1.0
 */
public interface PaymentDao {

    /**
     * Inserts new payment into database
     * @param subscription Subscription
     * @param payDate Date of the payment
     * @param paySum Sum to pay
     * @return Payment object
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public Payment createPayment(Subscription subscription,
                                 Date payDate, double paySum) throws DAOException;

    /**
     * Gets payment under given id if exists
     * @param id Payment id
     * @return Payment object
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public Payment getPayment(long id) throws DAOException;

    /**
     * Deletes payment under given id from database if exists
     * @param id Payment id to delete
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public void deletePayment(long id) throws DAOException;

    /**
     * Updates payment under given id
     * @param payment Payment to update
     * @return Updated payment instance
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public Payment updatePayment(Payment payment) throws DAOException;

    /**
     * Gets all payments from the database
     * @return List of payment objects
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public List<Payment> getAllPayments() throws DAOException;
}
