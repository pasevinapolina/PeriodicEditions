package by.pasevinapolina.controllers;

import by.pasevinapolina.commands.ActionCommand;
import by.pasevinapolina.commands.ActionFactory;
import by.pasevinapolina.utils.ConfigurationManager;
import by.pasevinapolina.utils.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebServlet(name = "PeriodicEditionName", urlPatterns = "/PeriodicEdition")
public class HomeController extends HttpServlet {

    public HomeController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);

        page = command.execute(request);
        if(page != null) {
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(page);
            requestDispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            request.getSession().setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
