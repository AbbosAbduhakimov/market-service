package uz.abbos.market.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.abbos.market.configuration.CustomUserDetails;
import uz.abbos.market.exception.ApplicationException;

public class SecurityUtil {
    public static Integer getUserId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getId();
        } catch (Exception ex) {
            throw new ApplicationException("ERROR");
        }
    }

    public static String get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
