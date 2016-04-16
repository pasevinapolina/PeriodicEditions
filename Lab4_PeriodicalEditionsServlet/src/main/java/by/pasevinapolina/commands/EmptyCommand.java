package by.pasevinapolina.commands;

import by.pasevinapolina.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public class EmptyCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
}
