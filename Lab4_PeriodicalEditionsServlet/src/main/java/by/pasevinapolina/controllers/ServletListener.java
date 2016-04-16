package by.pasevinapolina.controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Context Listener contains Entity Manager
 * @author Polina Pasevina
 * @version 1.0
 */
public class ServletListener implements ServletContextListener {

    private EntityManager entityManager;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PeriodicEditionJPA");
        entityManager = factory.createEntityManager();
        context.setAttribute("em", entityManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        entityManager = (EntityManager)context.getAttribute("em");

        if(entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
