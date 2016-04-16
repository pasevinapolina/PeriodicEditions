package by.pasevinapolina.dao;

import by.pasevinapolina.models.Edition;
import by.pasevinapolina.utils.DAOException;

import java.util.List;

/**
 * Interface for Edition DAO
 * @author Polina Pasevina
 * @version 1.0
 */
public interface EditionDao {

    /**
     * Inserts new edition in database
     * @param author Edition author
     * @param name Edition name
     * @param outputFrequency Output frequency
     * @return Edition object
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public Edition createEdition(String author, String name, int outputFrequency) throws DAOException;

    /**
     * Gets edition under given id if exists
     * @param id Edition id
     * @return Edition object
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public  Edition getEdition(long id) throws DAOException;

    /**
     * Updates edition under given id if exists
     * @param edition Edition to update
     * @return Updated edition instance
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public Edition updateEdition(Edition edition) throws DAOException;

    /**
     * Deletes edition under given id from database if exists
     * @param edition Edition to delete
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public void deleteEdition(Edition edition) throws DAOException;

    /**
     * Gets all editions from the database
     * @return List of edition objects
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public List<Edition> getAllEditions() throws DAOException;

    /**
     * Gets all editions for a reader under given id
     * @param readerId Reader id
     * @return list editions which the reader subscribes to
     * @throws DAOException If any SQL, database or connection exception occurs
     */
    public List<Edition> getReaderEditions(long readerId) throws DAOException;
}
