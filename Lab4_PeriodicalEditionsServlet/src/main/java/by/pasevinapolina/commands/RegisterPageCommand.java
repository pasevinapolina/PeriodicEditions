package by.pasevinapolina.commands;

import by.pasevinapolina.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Command to redirect client to registration page
 * @author Polina Pasevina
 * @version 1.0
 */
public class RegisterPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.register");
    }
}
