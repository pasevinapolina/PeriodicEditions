package by.pasevinapolina.commands;

import by.pasevinapolina.utils.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Factory for commands
 */
public class ActionFactory {

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
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return currentCommand;
    }
}
