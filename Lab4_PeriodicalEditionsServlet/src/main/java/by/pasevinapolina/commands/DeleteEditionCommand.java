package by.pasevinapolina.commands;

import by.pasevinapolina.dao.EditionDao;
import by.pasevinapolina.dao.impl.EditionDaoImpl;
import by.pasevinapolina.models.Edition;
import by.pasevinapolina.utils.DAOException;
import by.pasevinapolina.utils.MessageManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Command to delete edition
 * @author Polina Pasevina
 * @version 1.0
 */
public class DeleteEditionCommand implements ActionCommand {

    /**
     * Logger
     */
    public static Logger LOGGER = Logger.getLogger(DeletePaymentCommand.class);

    private EditionDao editionDao;

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        long editionId;

        try {
            editionId = Long.parseLong(request.getParameter("delEditionId"));

            EntityManager entityManager = (EntityManager) request.getServletContext().getAttribute("em");
            editionDao = new EditionDaoImpl(entityManager);
            editionDao.deleteEdition(editionId);

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

        page = new EditionCommand().execute(request);
        return page;
    }
}
