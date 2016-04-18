package by.pasevinapolina.commands;

import by.pasevinapolina.dao.PaymentDao;
import by.pasevinapolina.dao.impl.PaymentDaoImpl;
import by.pasevinapolina.models.Payment;
import by.pasevinapolina.utils.ConfigurationManager;
import by.pasevinapolina.utils.DAOException;
import by.pasevinapolina.utils.MessageManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Command to get payments list
 * @author Polina Pasevina
 * @version 1.0
 */
public class PaymentCommand implements ActionCommand {

    public static Logger LOGGER = Logger.getLogger(PaymentCommand.class);
    private PaymentDao paymentDao;

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        List<Payment> payments = new ArrayList<Payment>();

        try {
            EntityManager entityManager = (EntityManager)request.getServletContext().getAttribute("em");
            paymentDao = new PaymentDaoImpl(entityManager);

            payments = paymentDao.getAllPayments();

        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (ClassCastException e) {
            LOGGER.error(MessageManager.getProperty("message.attribute.error"), e);
            e.printStackTrace();
        }

        request.setAttribute("payments", payments);
        page = ConfigurationManager.getProperty("path.page.payments");
        return page;
    }
}
