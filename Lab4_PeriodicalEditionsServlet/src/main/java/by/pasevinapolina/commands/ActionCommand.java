package by.pasevinapolina.commands;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public interface ActionCommand {

    public String execute(HttpServletRequest request);
}
