package by.pasevinapolina.commands;

import by.pasevinapolina.utils.ConfigurationManager;
import by.pasevinapolina.utils.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Command to login
 * @author Polina Pasevina
 * @version 1.0
 */
public class LoginCommand implements ActionCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        if(checkLogin(login, password)) {
            request.setAttribute("user", login);
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
        }
        return page;
    }

    //to do
    private boolean checkLogin(String login, String password) {
        return true;
    }
}
