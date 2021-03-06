package by.pasevinapolina.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Prepares cookie files
 * @author Polina Pasevina
 * @version 1.0
 */
public class CookieAction {

    /**
     * Sets cookies for response
     * @param request Http request
     * @param response Http response
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String value = countRequests(request) + "";
        Cookie cookie = new Cookie("requestCount", value);
        response.addCookie(cookie);

        value = new Date(session.getLastAccessedTime()) + "";
        cookie = new Cookie("lastAccessedTime", value);
        response.addCookie(cookie);

        value = new Date(session.getCreationTime()) + "";
        cookie = new Cookie("creationTime", value);
        response.addCookie(cookie);
    }

    /**
     * Counts user visits
     * @param request Http request
     * @return Current count
     */
    private static int countRequests(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer counter = (Integer)session.getAttribute("counter");
        if(counter == null) {
            counter = 1;
        } else {
            ++counter;
        }
        session.setAttribute("counter", counter);
        return counter;
    }
}
