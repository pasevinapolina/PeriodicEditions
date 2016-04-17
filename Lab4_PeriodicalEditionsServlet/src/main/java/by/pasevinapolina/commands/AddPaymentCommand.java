package by.pasevinapolina.commands;

import by.pasevinapolina.dao.PaymentDao;
import by.pasevinapolina.dao.SubscriptionDao;
import by.pasevinapolina.dao.impl.PaymentDaoImpl;
import by.pasevinapolina.dao.impl.SubscriptionDaoImpl;
import by.pasevinapolina.models.Payment;
import by.pasevinapolina.models.Subscription;
import by.pasevinapolina.utils.DAOException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 *
 */
public class AddPaymentCommand implements ActionCommand {

    private PaymentDao paymentDao;
    private SubscriptionDao subscriptionDao;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Payment payment = null;

        try {
            long subscrId = Long.parseLong(request.getParameter("subscr_id"));
            int duration = Integer.parseInt(request.getParameter("duration"));

            EntityManager entityManager = (EntityManager) request.getServletContext().getAttribute("em");
            paymentDao = new PaymentDaoImpl(entityManager);
            subscriptionDao = new SubscriptionDaoImpl(entityManager);

            Subscription subscription = subscriptionDao.getSubscription(subscrId);
            if(subscription != null) {
                payment = paymentDao.createPayment(subscription, new Date(),
                        countPaySum(subscription, duration));
            }

        } catch (DAOException e) {
            e.printStackTrace();

        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if(payment != null)
            page = new EditionCommand().execute(request);
        return page;
    }

    private double countPaySum(Subscription subscription, int duration) {
        return 37.2 * duration;
    }
}
