package by.pasevinapolina.dao.impl;

import by.pasevinapolina.dao.DAO;
import by.pasevinapolina.dao.EditionDao;
import by.pasevinapolina.models.Edition;
import by.pasevinapolina.utils.DAOException;
import by.pasevinapolina.utils.MessageManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * Implementation for EditionDao
 * @see EditionDao
 * @author Polina Pasevina
 * @version 1.0
 */
public class EditionDaoImpl  extends DAO
        implements EditionDao {

    /**
     * Constructor. Initializes entity manager field {@link EditionDaoImpl#entityManager}
     * @param entityManager Entity Manager
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public EditionDaoImpl(EntityManager entityManager) throws DAOException {
        super(entityManager);
    }

    @Override
    public Edition createEdition(String author, String name,
                                 int outputFrequency) throws DAOException {
        EntityTransaction transaction = null;
        Edition edition = null;

        if(!isValid(author, name, outputFrequency))
            return null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            edition = new Edition();
            edition.setAuthor(author);
            edition.setName(name);
            edition.setOutputFrequency(outputFrequency);

            entityManager.persist(edition);
            entityManager.flush();
            transaction.commit();
        }
        catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return edition;
    }

    @Override
    public Edition getEdition(long id) throws DAOException {
        Edition edition = null;
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            edition = entityManager.find(Edition.class, id);

            entityManager.flush();
            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return edition;
    }

    @Override
    public Edition updateEdition(Edition edition) throws DAOException {
        EntityTransaction transaction = null;
        Edition newEdition = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            final Edition find = entityManager.find(Edition.class, edition.getId());
            if (find == null) {
                throw new DAOException(MessageManager.getProperty("message.rownotfound"));
            }
            edition.setId(find.getId());
            newEdition = entityManager.merge(edition);
            entityManager.flush();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return newEdition;
    }

    @Override
    public void deleteEdition(Edition edition) throws DAOException {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            edition = entityManager.merge(edition);
            entityManager.remove(edition);

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
    public List<Edition> getAllEditions() throws DAOException {
        EntityTransaction transaction = null;
        List<Edition> editions = null;

        try {
        transaction = entityManager.getTransaction();
        transaction.begin();

        Query query = entityManager.createNamedQuery("allEditions");
        editions = query.getResultList();

        entityManager.flush();
        transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return editions;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Edition> getReaderEditions(long readerId) throws DAOException {
        List<Edition> editions = null;
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query query = entityManager.createNamedQuery("readerEditions");
            query.setParameter("readerId", readerId);
            editions = query.getResultList();

            entityManager.flush();
            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return editions;
    }

    private boolean isValid(String author,
                            String name, int outputFrequency) {
        return author.length() != 0 && name.length() != 0 && outputFrequency > 0;
    }
}
