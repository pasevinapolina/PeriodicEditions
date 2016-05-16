package by.pasevinapolina.commands;

import by.pasevinapolina.dao.PaymentDao;
import by.pasevinapolina.dao.impl.PaymentDaoImpl;
import by.pasevinapolina.models.Payment;
import by.pasevinapolina.utils.DAOException;
import by.pasevinapolina.utils.MessageManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Command to edit payment
 * @author Polina Pasevina
 * @version 1.0
 */
public class EditPaymentCommand implements ActionCommand {

    /**
     * Logger
     */
    public static Logger LOGGER = Logger.getLogger(DeletePaymentCommand.class);

    private PaymentDao paymentDao;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Payment newPayment = null;
        long paymentId;
        Date payDate;
        double paySum;

        try {
            paymentId = Long.parseLong(request.getParameter("editPayId"));
            paySum = Double.parseDouble(request.getParameter("editPaySum").trim().replace(',', '.'));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            payDate = dateFormat.parse(request.getParameter("editPayDate"));

            EntityManager entityManager = (EntityManager) request.getServletContext().getAttribute("em");
            paymentDao = new PaymentDaoImpl(entityManager);
            newPayment = paymentDao.getPayment(paymentId);
            newPayment.setPayDate(payDate);
            newPayment.setPaySum(paySum);
            newPayment = paymentDao.updatePayment(newPayment);

        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } catch (ClassCastException e) {
            LOGGER.error(MessageManager.getProperty("message.attribute.error"), e);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }

        if(newPayment == null) {
            request.setAttribute("addError", MessageManager.getProperty("message.edit.error"));
        } else {
            request.setAttribute("addSuccess", MessageManager.getProperty("message.editpayment.success"));
        }

        page = new PaymentCommand().execute(request);
        return page;
    }
}
