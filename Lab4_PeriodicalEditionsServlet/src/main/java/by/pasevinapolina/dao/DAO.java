package by.pasevinapolina.dao;

import javax.persistence.EntityManager;

/**
 * Base class of DAO
 * @author Polina Pasevina
 * @version 1.0
 */
public class DAO {

    /**
     * Entity manager to interact with persistence context
     */
    protected EntityManager entityManager;

    /**
     * Constructor. Initializes entity manager field {@link DAO#entityManager}
     * @param entityManager Entity Manager
     */
    public DAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Closes entity manager
     */
    public void closeManager() {
        if(entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
