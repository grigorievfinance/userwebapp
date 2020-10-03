package org.softmaker.userwebapp.util;

import org.softmaker.userwebapp.AuthorizedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.requireNonNull;

public class SecurityUtil {

    public SecurityUtil() {
    }

    public static AuthorizedUser safeGet(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ((authentication == null)){
            return null;
        }
        Object principal = authentication.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get(){
        return requireNonNull(safeGet(), "No authorized user found");
    }

    public static int authUserId(){
        return get().getUserTo().id();
    }
}
