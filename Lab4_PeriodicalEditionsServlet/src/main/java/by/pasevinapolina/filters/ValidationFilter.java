package by.pasevinapolina.filters;

import by.pasevinapolina.commands.ActionCommand;
import by.pasevinapolina.commands.ActionFactory;
import by.pasevinapolina.commands.RegisterCommand;
import by.pasevinapolina.utils.ConfigurationManager;
import by.pasevinapolina.utils.MessageManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter that validates whether the fields of the register form are filled correctly
 * @author Polina Pasevina
 * @version 1.0
 */
public class ValidationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand((HttpServletRequest) servletRequest);

        if(command instanceof RegisterCommand) {

            if(hasErrors(servletRequest)) {
                RequestDispatcher dispatcher = servletRequest.getServletContext()
                        .getRequestDispatcher(ConfigurationManager.getProperty("path.page.register"));
                dispatcher.forward((HttpServletRequest)servletRequest, (HttpServletResponse)servletResponse);
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean hasErrors(ServletRequest servletRequest) {
        boolean flag = false;
        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        String confirm = servletRequest.getParameter("confirm");
        String name = servletRequest.getParameter("name");

        servletRequest.getServletContext().removeAttribute("errorMessage");
        servletRequest.getServletContext().removeAttribute("mismatchMessage");
        servletRequest.getServletContext().removeAttribute("duplicateMessage");

        if (login.isEmpty() || name.isEmpty()) {
            servletRequest.getServletContext().setAttribute("errorMessage",
                    MessageManager.getProperty("message.emptyfields"));
            flag = true;
        }

        if(!password.equals(confirm)) {
            servletRequest.getServletContext().setAttribute("mismatchMessage",
                    MessageManager.getProperty("message.passwordnotmatch"));
            flag = true;
        }
        return flag;
    }

    @Override
    public void destroy() {

    }
}
