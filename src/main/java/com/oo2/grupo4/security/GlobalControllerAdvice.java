package com.oo2.grupo4.security;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.oo2.grupo4.security.UserDetailsImpl;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("usuario")
    public String usuario(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            return userDetails.getLogin().getPersona().getNombre();
        }
        return null;
    }

    @ModelAttribute("rol")
    public String rol(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            return userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(auth -> auth.getAuthority()) 
                    .orElse("ROLE_CLIENTE");
        }
        return null;
    }

}
