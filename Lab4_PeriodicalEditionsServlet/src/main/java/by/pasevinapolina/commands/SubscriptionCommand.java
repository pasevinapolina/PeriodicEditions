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
import by.pasevinapolina.utils.ConfigurationManager;
import by.pasevinapolina.utils.DAOException;
import by.pasevinapolina.utils.MessageManager;
import org.apache.log4j.Logger;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Command to get subscriptions list
 * @author Polina Pasevina
 * @version 1.0
 */
public class SubscriptionCommand implements ActionCommand {

    public static Logger LOGGER = Logger.getLogger(SubscriptionCommand.class);

    private SubscriptionDao subscriptionDao;
    private ReaderDao readerDao;
    private EditionDao editionDao;
    private static boolean isUnpaidChecked = false;
    private static boolean isReaderSelected = false;

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        List<Subscription> subscriptions = new ArrayList<Subscription>();
        List<Subscription> unpaidSubscriptions = new ArrayList<Subscription>();
        HashSet<Reader> readers = new HashSet<Reader>();
        List<Subscription> readerSubscriptions = new ArrayList<Subscription>();
        List<Edition> editions = new ArrayList<Edition>();
        long readerId = 0;
        String reader = null;

        try {
            EntityManager entityManager = (EntityManager)request.getServletContext().getAttribute("em");
            subscriptionDao = new SubscriptionDaoImpl(entityManager);

            editionDao = new EditionDaoImpl(entityManager);
            editions = editionDao.getAllEditions();

            readerDao = new ReaderDaoImpl(entityManager);
            readers = new HashSet<Reader>(readerDao.getAllReaders(ClientType.USER.getRole()));

            reader = request.getParameter("readerName");
            if(isReaderSelected = (reader != null) && !reader.equals("0")) {
                readerId = Long.parseLong(reader);
                readerSubscriptions = subscriptionDao.getReaderSubscriptions(readerId);
            } else {
                subscriptions = subscriptionDao.getAllSubscriptions();
            }

            unpaidSubscriptions = subscriptionDao.getUnpaidSubscriptions();

        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (ClassCastException e) {
            LOGGER.error(MessageManager.getProperty("message.attribute.error"), e);
            e.printStackTrace();
        }

        for (Subscription s : subscriptions) {
            if(!unpaidSubscriptions.contains(s)) {
                s.setPaid(true);
            } else {
                s.setPaid(false);
            }
        }

        isUnpaidChecked = request.getParameter("unpaidCheck") != null;

        if(isUnpaidChecked && isReaderSelected) {
            unpaidSubscriptions.retainAll(readerSubscriptions);
        }

        request.setAttribute("readers", readers);
        request.setAttribute("editions", editions);

        if(isUnpaidChecked) {
            request.setAttribute("subscriptions", unpaidSubscriptions);
        } else if(isReaderSelected) {
            request.setAttribute("subscriptions", readerSubscriptions);
            request.setAttribute("selectedReader", reader);
        } else {
            request.setAttribute("subscriptions", subscriptions);
        }
        request.setAttribute("unpaidCheck", isUnpaidChecked);
        page = ConfigurationManager.getProperty("path.page.subscriptions");
        return page;
    }
}
