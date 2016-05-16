package by.pasevinapolina.dao;

import by.pasevinapolina.models.Reader;
import by.pasevinapolina.utils.DAOException;

import java.util.List;

/**
 * Interface for Reader DAO
 * @author Polina Pasevina
 * @version 1.0
 */
public interface ReaderDao {

    /**
     * Inserts new reader in database
     * @param name Reader's name
     * @param login  Reader's login
     * @param password  Reader's password
     * @return Reader object
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public Reader createReader(String login, String password,
                               String name) throws DAOException;

    /**
     * Gets reader under given id if exists
     * @param id Reader id
     * @return Reader object
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public  Reader getReader(long id) throws DAOException;

    /**
     * Gets reader by login and password if exists
     * @param login Reader login
     * @param password Reader password
     * @return Reader object
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public Reader getReader(String login, String password) throws DAOException;

    /**
     * Deletes reader under given id from database if exists
     * @param reader Reader to update
     * @return Updated reader instance
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public Reader updateReader(Reader reader) throws DAOException;

    /**
     * Deletes reader under given id from database if exists
     * @param reader Reader to delete
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public void deleteReader(Reader reader) throws DAOException;

    /**
     * Gets all readers from database
     * @return List of Reader objects
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public List<Reader> getAllReaders() throws DAOException;

    /**
     * Gets all readers with given role from database
     * @param userRole User role
     * @return List of Reader objects
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public List<Reader> getAllReaders(int userRole) throws DAOException;
}
