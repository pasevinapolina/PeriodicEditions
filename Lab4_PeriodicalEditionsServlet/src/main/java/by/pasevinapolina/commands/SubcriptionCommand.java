package by.pasevinapolina.commands;

import by.pasevinapolina.dao.SubscriptionDao;
import by.pasevinapolina.dao.impl.SubscriptionDaoImpl;
import by.pasevinapolina.models.Subscription;
import by.pasevinapolina.utils.ConfigurationManager;
import by.pasevinapolina.utils.DAOException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SubcriptionCommand implements ActionCommand {

    private SubscriptionDao subscriptionDao;

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;

        List<Subscription> subscriptions = new ArrayList<Subscription>();
        List<Subscription> unpaidSubscriptions = new ArrayList<Subscription>();

        try {
            EntityManager entityManager = (EntityManager)request.getServletContext().getAttribute("em");
            subscriptionDao = new SubscriptionDaoImpl(entityManager) ;

            subscriptions = subscriptionDao.getAllSubscriptions();
            unpaidSubscriptions = subscriptionDao.getUnpaidSubscriptions();

        } catch (DAOException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        request.setAttribute("subscriptions", subscriptions);
        request.setAttribute("unpaid", unpaidSubscriptions);
        page = ConfigurationManager.getProperty("path.page.subscriptions");
        return page;
    }
}