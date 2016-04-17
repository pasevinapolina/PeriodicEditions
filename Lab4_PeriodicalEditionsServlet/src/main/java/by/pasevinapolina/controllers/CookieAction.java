package by.pasevinapolina.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Prepares cookie files
 */
public class CookieAction {
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
