package by.pasevinapolina.dao;

import by.pasevinapolina.models.Edition;
import by.pasevinapolina.models.Reader;
import by.pasevinapolina.models.Subscription;
import by.pasevinapolina.utils.DAOException;

import java.util.List;

/**
 * Interface for Subscription DAO
 * @author Polina Pasevina
 * @version 1.0
 */
public interface SubscriptionDao {

    /**
     * Inserts new subscription
     * @param edition Edition to subscript to
     * @param reader Reader who subscripts
     * @param duration Subscription duration
     * @return Subscription object
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public Subscription createSubscription(Edition edition, Reader reader,
                                           int duration) throws DAOException;

    /**
     * Gets the subscription with the given id if such exists
     * @param id Subscription id
     * @return Subscription object
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public Subscription getSubscription(long id) throws DAOException;

    /**
     * Updates the subscription under given id
     * @param subscription Subscription to update
     * @return Updated subscription instance
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public Subscription updateSubscription(Subscription subscription) throws DAOException;

    /**
     * Deletes subscription under given id from database if such exists
     * @param subscription Subscription to delete
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public void deleteSubscription(Subscription subscription) throws DAOException;

    /**
     * Gets all subscriptions from database
     * @return List of subscription objects
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public List<Subscription> getAllSubscriptions() throws DAOException;

    /**
     * Gets all unpaid subscriptions
     * @return List of unpaid subscriptions
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public List<Subscription> getUnpaidSubscriptions() throws DAOException;
}
