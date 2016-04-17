package by.pasevinapolina.commands;

import by.pasevinapolina.dao.SubscriptionDao;
import by.pasevinapolina.dao.impl.SubscriptionDaoImpl;
import by.pasevinapolina.models.Reader;
import by.pasevinapolina.models.Subscription;
import by.pasevinapolina.utils.ConfigurationManager;
import by.pasevinapolina.utils.DAOException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 */
public class SubcriptionCommand implements ActionCommand {

    private SubscriptionDao subscriptionDao;
    private static boolean isUnpaidChecked = false;

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        List<Subscription> subscriptions = new ArrayList<Subscription>();
        List<Subscription> unpaidSubscriptions = new ArrayList<Subscription>();
        HashSet<Reader> readers = new HashSet<Reader>();

        try {
            EntityManager entityManager = (EntityManager)request.getServletContext().getAttribute("em");
            subscriptionDao = new SubscriptionDaoImpl(entityManager);

            subscriptions = subscriptionDao.getAllSubscriptions();
            unpaidSubscriptions = subscriptionDao.getUnpaidSubscriptions();

        } catch (DAOException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        for (Subscription s : subscriptions) {
            readers.add(s.getReader());
            if(!unpaidSubscriptions.contains(s)) {
                s.setPaid(true);
            }
        }

        String reader = request.getParameter("readerName");

        request.setAttribute("readers", readers);
        if(isUnpaidChecked = (request.getParameter("unpaidCheck") != null)) {
            request.setAttribute("subscriptions", unpaidSubscriptions);
        } else {
            request.setAttribute("subscriptions", subscriptions);
        }
        request.setAttribute("unpaidCheck", isUnpaidChecked);
        page = ConfigurationManager.getProperty("path.page.subscriptions");
        return page;
    }
}
