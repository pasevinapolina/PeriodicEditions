package by.pasevinapolina.commands;

import by.pasevinapolina.dao.ReaderDao;
import by.pasevinapolina.dao.impl.ReaderDaoImpl;
import by.pasevinapolina.models.ClientType;
import by.pasevinapolina.models.Reader;
import by.pasevinapolina.utils.ConfigurationManager;
import by.pasevinapolina.utils.DAOException;
import by.pasevinapolina.utils.MessageManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public class RegisterCommand implements ActionCommand {

    public static Logger LOGGER = Logger.getLogger(RegisterCommand.class);

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_NAME = "name";

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        ReaderDao readerDao = null;
        Reader reader = null;

        try {
            EntityManager entityManager = (EntityManager) request.getServletContext().getAttribute("em");
            readerDao = new ReaderDaoImpl(entityManager);

            String login = request.getParameter(PARAM_NAME_LOGIN);
            String name = request.getParameter(PARAM_NAME_NAME);
            String password = request.getParameter(PARAM_NAME_PASSWORD);
            reader = readerDao.createReader(login, password, name);
        }
        catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (ClassCastException e) {
            LOGGER.error(MessageManager.getProperty("message.attribute.error"), e);
            e.printStackTrace();
        }

        if(reader != null) {
            request.setAttribute("user", reader);
            request.setAttribute("userType", ClientType.USER);
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorMessage", MessageManager.getProperty("message.loginerror"));
        }
        return page;
    }
}
