package by.pasevinapolina.filters;

import by.pasevinapolina.utils.MessageManager;

import javax.servlet.*;
import java.io.IOException;

/**
 *
 */
public class ValidationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String login = servletRequest.getParameter("login");
        String password = servletRequest.getParameter("password");
        String confirm = servletRequest.getParameter("confirm");
        String name = servletRequest.getParameter("name");

        if(login.isEmpty() || !password.equals(confirm) || name.isEmpty()) {
            servletRequest.getServletContext().setAttribute("errorMessage", MessageManager.getProperty("message.registererror"));
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
