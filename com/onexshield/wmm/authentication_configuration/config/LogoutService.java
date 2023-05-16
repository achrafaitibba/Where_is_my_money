package com.onexshield.wmm.authentication_configuration.config;

import com.onexshield.wmm.repository.ITokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final ITokenRepository ITokenRepository;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        System.out.println("-------------------------------------------------");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        jwt = authHeader.substring(7);
        var storedToken = ITokenRepository.findByToken(jwt)
                .orElse(null);
        if(storedToken != null){
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            ITokenRepository.save(storedToken);
        }

    }
}
