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
 * Command to login
 * @author Polina Pasevina
 * @version 1.0
 */
public class LoginCommand implements ActionCommand {

    /**
     * Logger
     */
    public static Logger LOGGER = Logger.getLogger(LoginCommand.class);

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        Reader reader = checkLogin(login, password, request);
        if(reader != null) {
            request.getSession().setAttribute("user", reader);

            if(reader.getUserRole() == 2) {
                request.getSession().setAttribute("userType", ClientType.ADMIN);
            }
            else {
                request.getSession().setAttribute("userType", ClientType.USER);
            }
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
        }
        return page;
    }


    private Reader checkLogin(String login, String password, HttpServletRequest request) {
        Reader reader = null;
        try {
            EntityManager entityManager = (EntityManager) request.getServletContext().getAttribute("em");
            ReaderDao readerDao = new ReaderDaoImpl(entityManager);
            reader = readerDao.getReader(login, password);

        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (ClassCastException e) {
            LOGGER.error(MessageManager.getProperty("message.attribute.error"), e);
            e.printStackTrace();
        }
        return reader;
    }
}
