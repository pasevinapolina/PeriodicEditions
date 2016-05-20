package by.pasevinapolina.commands;

import by.pasevinapolina.dao.EditionDao;
import by.pasevinapolina.dao.ReaderDao;
import by.pasevinapolina.dao.SubscriptionDao;
import by.pasevinapolina.dao.impl.EditionDaoImpl;
import by.pasevinapolina.dao.impl.ReaderDaoImpl;
import by.pasevinapolina.dao.impl.SubscriptionDaoImpl;
import by.pasevinapolina.models.ClientType;
import by.pasevinapolina.models.Edition;
import by.pasevinapolina.models.Reader;
import by.pasevinapolina.models.Subscription;
import by.pasevinapolina.utils.DAOException;
import by.pasevinapolina.utils.MessageManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Command to add subscription
 * @author Polina Pasevina
 * @version 1.0
 */
public class SubscribeCommand implements ActionCommand {

    public static Logger LOGGER = Logger.getLogger(SubscribeCommand.class);

    private SubscriptionDao subscriptionDao;
    private EditionDao editionDao;

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        Subscription subscription = null;

        try {
            EntityManager entityManager = (EntityManager)request.getServletContext().getAttribute("em");
            subscriptionDao = new SubscriptionDaoImpl(entityManager);
            editionDao = new EditionDaoImpl(entityManager);

            long id = Long.parseLong(request.getParameter("selEditionId"));
            int duration = Integer.parseInt(request.getParameter("duration"));

            Edition edition = editionDao.getEdition(id);
            Reader reader = (Reader)request.getSession().getAttribute("user");

            subscription = subscriptionDao.createSubscription(edition, reader, duration);

        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (ClassCastException e) {
            LOGGER.error(MessageManager.getProperty("message.attribute.error"), e);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            LOGGER.error(MessageManager.getProperty("message.attribute.error"), e);
            e.printStackTrace();
        }

        if(subscription == null) {
            request.setAttribute("addError", MessageManager.getProperty("message.subscribe.error"));
        } else {
            request.setAttribute("addSuccess", MessageManager.getProperty("message.subscribe.success"));
        }

        page = new SubscriptionCommand().execute(request);
        return page;
    }
}
