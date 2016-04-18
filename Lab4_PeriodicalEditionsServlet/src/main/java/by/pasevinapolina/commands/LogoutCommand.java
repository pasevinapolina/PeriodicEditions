package by.pasevinapolina.commands;

import by.pasevinapolina.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Command to logout
 * @author Polina Pasevina
 * @version 1.0
 */
public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        request.getSession().invalidate();
        return page;
    }
}
