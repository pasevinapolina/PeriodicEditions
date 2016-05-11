package by.pasevinapolina.commands;

import by.pasevinapolina.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Command to redirect client to login page
 */
public class LoginPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.login");
    }
}
