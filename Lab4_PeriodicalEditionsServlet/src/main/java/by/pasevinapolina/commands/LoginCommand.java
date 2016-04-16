package by.pasevinapolina.commands;

import by.pasevinapolina.services.LoginService;
import by.pasevinapolina.utils.ConfigurationManager;
import by.pasevinapolina.utils.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public class LoginCommand implements ActionCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);

        if(LoginService.checkLogin(login, password)) {
            request.setAttribute("user", login);
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
        }
        return page;
    }
}
