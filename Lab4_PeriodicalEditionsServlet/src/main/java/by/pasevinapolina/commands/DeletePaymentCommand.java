package by.pasevinapolina.commands;

import by.pasevinapolina.dao.PaymentDao;
import by.pasevinapolina.dao.impl.PaymentDaoImpl;
import by.pasevinapolina.models.Payment;
import by.pasevinapolina.utils.DAOException;
import by.pasevinapolina.utils.MessageManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Command to delete payment
 * @author Polina Pasevina
 * @version 1.0
 */
public class DeletePaymentCommand implements ActionCommand {

    /**
     * Logger
     */
    public static Logger LOGGER = Logger.getLogger(DeletePaymentCommand.class);

    private PaymentDao paymentDao;

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        Payment payment = null;
        long paymentId;

        try {
            paymentId = Long.parseLong(request.getParameter("delPayId"));

            EntityManager entityManager = (EntityManager) request.getServletContext().getAttribute("em");
            paymentDao = new PaymentDaoImpl(entityManager);
            paymentDao.deletePayment(paymentId);

        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } catch (ClassCastException e) {
            LOGGER.error(MessageManager.getProperty("message.attribute.error"), e);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }

        page = new PaymentCommand().execute(request);
        return page;
    }
}
