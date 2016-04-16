package by.pasevinapolina.controllers;

import javax.servlet.*;
import java.io.IOException;

/**
 *
 */
public class EncodingFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        try {
            servletRequest.setCharacterEncoding("Cp1251");
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ServletException e) {
            filterConfig.getServletContext().log(e.getMessage());
        } catch (IOException e) {
            filterConfig.getServletContext().log(e.getMessage());
        }
    }

    @Override
    public void destroy() {
    }
}
