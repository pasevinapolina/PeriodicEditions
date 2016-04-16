package by.pasevinapolina.dao.impl;

import by.pasevinapolina.dao.DAO;
import by.pasevinapolina.dao.PaymentDao;
import by.pasevinapolina.models.Payment;
import by.pasevinapolina.models.Subscription;
import by.pasevinapolina.utils.DAOException;
import by.pasevinapolina.utils.MessageManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Implementation for PaymentDao
 * @see PaymentDao
 * @author Polina Pasevina
 * @version 1.0
 */
public class PaymentDaoImpl extends DAO
        implements PaymentDao {

    /**
     * Constructor. Initializes entity manager field {@link PaymentDaoImpl#entityManager}
     * @param entityManager Entity Manager
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public PaymentDaoImpl(EntityManager entityManager) throws DAOException {
        super(entityManager);
    }

    @Override
    public Payment createPayment(Subscription subscription,
                                 Date payDate, double paySum) throws DAOException {
        Payment payment = null;
        EntityTransaction transaction = null;

        if(!isValid(paySum))
            return null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            payment = new Payment();
            payment.setSubscription(subscription);
            payment.setPayDate(payDate);
            payment.setPaySum(paySum);

            entityManager.persist(payment);
            entityManager.flush();
            transaction.commit();
        }
        catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return payment;
    }

    @Override
    public Payment getPayment(long id) throws DAOException {
        Payment payment = null;
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            payment = entityManager.find(Payment.class, id);

            entityManager.flush();
            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return payment;
    }

    @Override
    public void deletePayment(Payment payment) throws DAOException {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            payment = entityManager.merge(payment);
            entityManager.remove(payment);

            entityManager.flush();
            transaction.commit();
        } catch(Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
    }

    @Override
    public Payment updatePayment(Payment payment) throws DAOException {
        EntityTransaction transaction = null;
        Payment newPayment = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            final Payment find = entityManager.find(Payment.class, payment.getId());
            if (find == null) {
                throw new DAOException(MessageManager.getProperty("message.rownotfound"));
            }
            payment.setId(find.getId());
            newPayment = entityManager.merge(payment);
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

    @SuppressWarnings("unchecked")
    @Override
    public List<Payment> getAllPayments() throws DAOException {
        List<Payment> payments = null;
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query query = entityManager.createNamedQuery("allPayments");
            payments = query.getResultList();

            entityManager.flush();
            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return payments;
    }

    private boolean isValid(double paySum) {
        return paySum > 0;
    }
}
