package by.pasevinapolina.commands;

import by.pasevinapolina.dao.PaymentDao;
import by.pasevinapolina.dao.SubscriptionDao;
import by.pasevinapolina.dao.impl.PaymentDaoImpl;
import by.pasevinapolina.dao.impl.SubscriptionDaoImpl;
import by.pasevinapolina.models.Payment;
import by.pasevinapolina.models.Subscription;
import by.pasevinapolina.utils.DAOException;
import by.pasevinapolina.utils.MessageManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Command to pay for a subscription
 * @author Polina Pasevina
 * @version 1.0
 */
public class AddPaymentCommand implements ActionCommand {

    public static Logger LOGGER = Logger.getLogger(AddPaymentCommand.class);
    private PaymentDao paymentDao;
    private SubscriptionDao subscriptionDao;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Payment payment = null;
        Subscription subscription = null;
        List<Subscription> unpaidSubscriptions = new ArrayList<Subscription>();
        boolean isUnpaid = true;

        try {
            long subscrId = Long.parseLong(request.getParameter("subscr_id"));
            int duration = Integer.parseInt(request.getParameter("duration"));

            EntityManager entityManager = (EntityManager) request.getServletContext().getAttribute("em");
            paymentDao = new PaymentDaoImpl(entityManager);
            subscriptionDao = new SubscriptionDaoImpl(entityManager);

            subscription = subscriptionDao.getSubscription(subscrId);
            unpaidSubscriptions = subscriptionDao.getUnpaidSubscriptions();
            isUnpaid = unpaidSubscriptions.contains(subscription);

            if(subscription != null && isUnpaid) {
                payment = paymentDao.createPayment(subscription, new Date(),
                        countPaySum(duration));
            }

        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (ClassCastException e) {
            LOGGER.error(MessageManager.getProperty("message.attribute.error"), e);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        }

        if(payment == null) {
            request.setAttribute("addError", MessageManager.getProperty("message.paymenterror"));
            if(!isUnpaid)
                request.setAttribute("addError", MessageManager.getProperty("message.alreadypaid"));
        } else {
            request.setAttribute("addSuccess", MessageManager.getProperty("message.successpayment"));
        }
        page = new PaymentCommand().execute(request);
        return page;
    }

    private double countPaySum(int duration) {
        return 37.2 * duration;
    }
}
