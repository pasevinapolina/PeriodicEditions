package by.pasevinapolina.filters;

import by.pasevinapolina.commands.*;
import by.pasevinapolina.models.ClientType;
import by.pasevinapolina.utils.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter to limit access for different types of clients
 */
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();

        ClientType clientType = (ClientType) session.getAttribute("userType");
        ActionFactory client = new ActionFactory();
        ActionCommand currentCommand = client.defineCommand(httpRequest);

        if(!(currentCommand instanceof RegisterPageCommand) && !(currentCommand instanceof RegisterCommand) &&
                !(currentCommand instanceof EmptyCommand) && !(currentCommand instanceof LoginCommand)) {
            if(clientType == ClientType.GUEST) {
                RequestDispatcher dispatcher = servletRequest.getServletContext()
                        .getRequestDispatcher(ConfigurationManager.getProperty("path.page.login"));
                dispatcher.forward(httpRequest, httpResponse);
                return;
            }

            if (clientType == null) {
                clientType = ClientType.GUEST;
                session.setAttribute("userType", clientType);
                RequestDispatcher dispatcher = servletRequest.getServletContext()
                        .getRequestDispatcher(ConfigurationManager.getProperty("path.page.login"));
                dispatcher.forward(httpRequest, httpResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
