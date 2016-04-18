package by.pasevinapolina.commands;

import by.pasevinapolina.dao.PaymentDao;
import by.pasevinapolina.dao.impl.PaymentDaoImpl;
import by.pasevinapolina.models.Payment;
import by.pasevinapolina.utils.ConfigurationManager;
import by.pasevinapolina.utils.DAOException;

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
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        request.setAttribute("payments", payments);
        page = ConfigurationManager.getProperty("path.page.payments");
        return page;
    }
}
