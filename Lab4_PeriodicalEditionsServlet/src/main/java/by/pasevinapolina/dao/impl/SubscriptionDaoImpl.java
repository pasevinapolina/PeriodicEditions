package by.pasevinapolina.dao.impl;

import by.pasevinapolina.dao.DAO;
import by.pasevinapolina.dao.SubscriptionDao;
import by.pasevinapolina.models.Edition;
import by.pasevinapolina.models.Reader;
import by.pasevinapolina.models.Subscription;
import by.pasevinapolina.utils.DAOException;
import by.pasevinapolina.utils.MessageManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * Implementation for SubscriptionDao
 * @see SubscriptionDao
 * @author Polina Pasevina
 * @version 1.0
 */
public class SubscriptionDaoImpl extends DAO
        implements SubscriptionDao {

    private final String UNPAID_SUBSCRIPTIONS = "SELECT s.subscription_id, s.edition_id, s.reader_id, s.duration " +
            "FROM Subscription s LEFT OUTER JOIN Payment p ON s.subscription_id = p.subscription_id " +
            "WHERE p.payment_id IS NULL OR datediff(sysdate(), p.pay_date) > s.duration;";

    /**
     * Constructor. Initializes entity manager field {@link SubscriptionDaoImpl#entityManager}
     * @param entityManager Entity Manager
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public SubscriptionDaoImpl(EntityManager entityManager) throws DAOException {
        super(entityManager);
    }

    @Override
    public Subscription createSubscription(Edition edition, Reader reader,
                                           int duration) throws DAOException {
        Subscription subscription = null;
        EntityTransaction transaction = null;

        if(!isValid(duration))
            return null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            subscription = new Subscription();
            subscription.setEdition(edition);
            subscription.setReader(reader);
            subscription.setDuration(duration);

            entityManager.persist(subscription);
            entityManager.flush();
            transaction.commit();
        }
        catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return subscription;
    }

    @Override
    public Subscription getSubscription(long id) throws DAOException {
        Subscription subscription = null;
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            subscription = entityManager.find(Subscription.class, id);

            entityManager.flush();
            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return subscription;
    }

    @Override
    public Subscription updateSubscription(Subscription subscription)
            throws DAOException {
        EntityTransaction transaction = null;
        Subscription newSubscription = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            final Subscription find = entityManager.find(Subscription.class, subscription.getId());
            if (find == null) {
                throw new DAOException(MessageManager.getProperty("message.rownotfound"));
            }
            subscription.setId(find.getId());
            newSubscription = entityManager.merge(subscription);
            entityManager.flush();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return null;
    }

    @Override
    public void deleteSubscription(Subscription subscription) throws DAOException {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            subscription = entityManager.merge(subscription);
            entityManager.remove(subscription);

            entityManager.flush();
            transaction.commit();
        } catch(Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Subscription> getAllSubscriptions() throws DAOException {
        List<Subscription> subscriptions = null;
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query query = entityManager.createNamedQuery("allSubscriptions");
            subscriptions = query.getResultList();

            entityManager.flush();
            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return subscriptions;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Subscription> getUnpaidSubscriptions() throws DAOException {
        List<Subscription> subscriptions = null;
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query query = entityManager.createNativeQuery(UNPAID_SUBSCRIPTIONS, Subscription.class);
            subscriptions = query.getResultList();

            entityManager.flush();
            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return subscriptions;
    }

    private boolean isValid(int duration) {
        return duration > 0;
    }
}
