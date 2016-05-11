package by.pasevinapolina.dao.impl;

import by.pasevinapolina.dao.DAO;
import by.pasevinapolina.dao.ReaderDao;
import by.pasevinapolina.models.Reader;
import by.pasevinapolina.utils.DAOException;
import by.pasevinapolina.utils.MessageManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * Implementation for ReaderDao
 * @see ReaderDao
 * @author Polina Pasevina
 * @version 1.0
 */
public class ReaderDaoImpl extends DAO
        implements ReaderDao {

    /**
     * Constructor. Initializes entity manager field {@link ReaderDaoImpl#entityManager}
     * @param entityManager Entity Manager
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public ReaderDaoImpl(EntityManager entityManager) throws DAOException {
        super(entityManager);
    }

    @Override
    public Reader createReader(String login, String password,
                               String name) throws DAOException {

        Reader reader = null;
        EntityTransaction transaction = null;

        if(!isValid(name))
            return null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            reader = new Reader();
            reader.setName(name);
            reader.setLogin(login);
            reader.setPassword(password);
            reader.setUserRole(1);

            entityManager.persist(reader);
            entityManager.flush();
            transaction.commit();
        }
        catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return reader;
    }

    @Override
    public Reader getReader(long id) throws DAOException {
        Reader reader = null;
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            reader = entityManager.find(Reader.class, id);

            entityManager.flush();
            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return reader;
    }

    @Override
    public Reader getReader(String login, String password) throws DAOException {
        Reader reader = null;
        List<Reader> readers = null;
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createNamedQuery("loginReader");
            query.setParameter("login", login);
            query.setParameter("password", password);

            readers = query.getResultList();
            if(readers.size() > 0) {
                reader = readers.get(0);
            }

            entityManager.flush();
            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return reader;
    }

    @Override
    public Reader updateReader(Reader reader) throws DAOException {
        EntityTransaction transaction = null;
        Reader newReader = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            final Reader find = entityManager.find(Reader.class, reader.getId());
            if (find == null) {
                throw new DAOException(MessageManager.getProperty("message.rownotfound"));
            }
            reader.setId(find.getId());
            newReader = entityManager.merge(reader);
            entityManager.flush();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return newReader;
    }

    @Override
    public void deleteReader(Reader reader) throws DAOException {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            reader = entityManager.merge(reader);
            entityManager.remove(reader);

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
    public List<Reader> getAllReaders() throws DAOException {
        EntityTransaction transaction = null;
        List<Reader> readers = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Query query = entityManager.createNamedQuery("allReaders");
            readers = query.getResultList();

            entityManager.flush();
            transaction.commit();
        }
        catch(Exception e) {
            if(transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(MessageManager.getProperty("message.transaction.error"), e);
        }
        return readers;
    }

    private boolean isValid(String name) {
        return name.length() != 0;
    }
}
