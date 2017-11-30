package servlets;

import entities.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Util {

    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
    private static final int SECONDS = 60;
    private static final int MINUTES = 60;
    private static final int HOURS = 24;
    private static final int MIN_TIME = 0;
    private static final String USER = "user";


    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        session.setAttribute(USER, loginedUser);
    }

    public static User getLoginedUser(HttpSession session) {
        User loginedUser = (User) session.getAttribute(USER);
        return loginedUser;
    }

    public static void storeUserCookie(HttpServletResponse response, User user) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getLogin());
        cookieUserName.setMaxAge(HOURS * MINUTES * SECONDS);
        response.addCookie(cookieUserName);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        cookieUserName.setMaxAge(MIN_TIME);
        response.addCookie(cookieUserName);
    }

}