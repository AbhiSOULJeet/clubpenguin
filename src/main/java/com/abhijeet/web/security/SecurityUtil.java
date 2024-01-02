package com.abhijeet.web.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static int check = 0;
    public static String currentUsername;

    public static String getSessionuser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUsername = authentication.getName();
            check = 1;
            return currentUsername;

        }
        return null;
    }
}
