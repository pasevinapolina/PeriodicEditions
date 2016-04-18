package by.pasevinapolina.commands;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for commands
 * @author Polina Pasevina
 * @version 1.0
 */
public interface ActionCommand {

    /**
     * Processes command from client
     * @param request Http request
     * @return Path to redirect
     */
    public String execute(HttpServletRequest request);
}
