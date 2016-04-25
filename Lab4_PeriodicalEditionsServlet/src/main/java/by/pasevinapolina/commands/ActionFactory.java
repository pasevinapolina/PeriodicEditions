package by.pasevinapolina.commands;

import by.pasevinapolina.utils.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Factory for commands
 * @author Polina Pasevina
 * @version 1.0
 */
public class ActionFactory {

    /**
     * Logger
     */
    public static Logger LOGGER = Logger.getLogger(ActionCommand.class);

    /**
     * Defines what command to create
     * @param request Http request
     * @return New command
     */
    public ActionCommand defineCommand(HttpServletRequest request) {

        ActionCommand currentCommand = new EmptyCommand();
        String action = request.getParameter("command");

        if(action == null || action.isEmpty()) {
            return currentCommand;
        }

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            currentCommand = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            LOGGER.error(MessageManager.getProperty("message.wrongaction"));
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return currentCommand;
    }
}
