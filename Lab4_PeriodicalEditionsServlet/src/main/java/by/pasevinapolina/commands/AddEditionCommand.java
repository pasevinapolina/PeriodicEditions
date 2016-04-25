package by.pasevinapolina.commands;

import by.pasevinapolina.dao.EditionDao;
import by.pasevinapolina.dao.impl.EditionDaoImpl;
import by.pasevinapolina.models.Edition;
import by.pasevinapolina.utils.ConfigurationManager;
import by.pasevinapolina.utils.DAOException;
import by.pasevinapolina.utils.MessageManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Locale;

/**
 * Command to register new edition
 * @author Polina Pasevina
 * @version 1.0
 */
public class AddEditionCommand implements ActionCommand {

    /**
     * Logger
     */
    public static Logger LOGGER = Logger.getLogger(AddEditionCommand.class);

    private EditionDao editionDao;

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        Edition edition = null;
        String author = null;
        try {
            author = request.getParameter("author");
            String name = request.getParameter("name");
            int outFreq = Integer.parseInt(request.getParameter("outFreq"));

            EntityManager entityManager = (EntityManager) request.getServletContext().getAttribute("em");
            editionDao = new EditionDaoImpl(entityManager);
            edition = editionDao.createEdition(author, name, outFreq);

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

        if(edition == null) {
            request.setAttribute("addError", MessageManager.getProperty("message.editionerror"));
        } else {
            request.setAttribute("addSuccess", MessageManager.getProperty("message.editionadded"));
        }

        page = new EditionCommand().execute(request);
        return page;
    }
}
