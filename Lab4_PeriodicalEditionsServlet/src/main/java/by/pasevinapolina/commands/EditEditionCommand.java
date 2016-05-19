package by.pasevinapolina.commands;

import by.pasevinapolina.dao.EditionDao;
import by.pasevinapolina.dao.impl.EditionDaoImpl;
import by.pasevinapolina.models.Edition;
import by.pasevinapolina.utils.DAOException;
import by.pasevinapolina.utils.MessageManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * Command to edit payment
 * @author Polina Pasevina
 * @version 1.0
 */
public class EditEditionCommand implements ActionCommand {

    /**
     * Logger
     */
    public static Logger LOGGER = Logger.getLogger(EditEditionCommand.class);

    private EditionDao editionDao;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Edition newEdition = null;

        try {
            long editionId = Long.parseLong(request.getParameter("editEditionId"));
            String name = request.getParameter("editEditionName");
            String author = request.getParameter("editAuthor");
            int frequency = Integer.parseInt(request.getParameter("editFrequency"));

            EntityManager entityManager = (EntityManager) request.getServletContext().getAttribute("em");
            editionDao = new EditionDaoImpl(entityManager);
            newEdition = editionDao.getEdition(editionId);
            newEdition.setName(name);
            newEdition.setAuthor(author);
            newEdition.setOutputFrequency(frequency);
            newEdition = editionDao.updateEdition(newEdition);

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

        if(newEdition == null) {
            request.setAttribute("addError", MessageManager.getProperty("message.edit.error"));
        } else {
            request.setAttribute("addSuccess", MessageManager.getProperty("message.editedition.success"));
        }

        page = new EditionCommand().execute(request);
        return page;
    }
}
