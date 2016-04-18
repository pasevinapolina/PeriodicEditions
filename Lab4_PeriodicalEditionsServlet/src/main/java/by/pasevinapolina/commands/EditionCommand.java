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
import java.util.ArrayList;
import java.util.List;

/**
 * Command to get edition list
 * @author Polina Pasevina
 * @version 1.0
 */
public class EditionCommand implements ActionCommand {

    public static Logger LOGGER = Logger.getLogger(EditionCommand.class);

    private EditionDao editionDao;

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        List<Edition> editions = new ArrayList<Edition>();

        try {
            EntityManager entityManager = (EntityManager) request.getServletContext().getAttribute("em");
            editionDao = new EditionDaoImpl(entityManager);
            editions = editionDao.getAllEditions();
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (ClassCastException e) {
            LOGGER.error(MessageManager.getProperty("message.attribute.error"), e);
            e.printStackTrace();
        }

        request.setAttribute("editions", editions);
        page = ConfigurationManager.getProperty("path.page.editions");
        return page;
    }
}
