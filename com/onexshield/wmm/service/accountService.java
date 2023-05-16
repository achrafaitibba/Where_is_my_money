package com.onexshield.wmm.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.onexshield.wmm.authentication_configuration.config.JwtService;
import com.onexshield.wmm.authentication_configuration.token.Token;
import com.onexshield.wmm.authentication_configuration.token.TokenType;
import com.onexshield.wmm.model.account;
import com.onexshield.wmm.mappers.accountMapper;
import com.onexshield.wmm.repository.IAccountRepository;
import com.onexshield.wmm.repository.ITokenRepository;
import com.onexshield.wmm.request.authenticationRequest;
import com.onexshield.wmm.request.registerRequest;
import com.onexshield.wmm.response.registraterAndAuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class accountService {
    private final IAccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ITokenRepository ITokenRepository;
    private final accountMapper accountMapper;

    public registraterAndAuthenticationResponse register(registerRequest request) {

        account savedUser = repository.save(accountMapper.requestToAccount(request));
        var jwtToken = jwtService.generateToken(accountMapper.requestToAccount(request));
        var refreshToken = jwtService.generateRefreshToken(accountMapper.requestToAccount(request));
        saveUserToken(savedUser, jwtToken);
        return registraterAndAuthenticationResponse.builder()
                .email(savedUser.getUsername())
                .firstname(savedUser.getPerson().getFirstName())
                .refreshToken(refreshToken)
                .accessToken(jwtToken)
                .build();
    }



    public registraterAndAuthenticationResponse authenticate(authenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return registraterAndAuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }
    private void revokeAllUserTokens(account account){
        var validUserTokens = ITokenRepository.findAllValidTokensByUser(account.getAccountId());
        if( validUserTokens.isEmpty())
            return;
        validUserTokens
                .stream()
                .map(t -> {
                    t.setExpired(true);
                    t.setRevoked(true);
                    return t;
                })
                .collect(toList());
        ITokenRepository.saveAll(validUserTokens);
    }
    private void saveUserToken(account account, String jwtToken) {
        var token = Token.builder()
                .account(account)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        ITokenRepository.save(token);
    }

    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response
    )throws Exception {
        final String authHeader = request.getHeader("Authorization");
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        refreshToken = authHeader.substring(7); // 7 = "Bearer".length + 1 , space
        // extract user email from JWT token; because we set the email as username in the user Model
        userEmail = jwtService.extractUsername(refreshToken);
        if(userEmail != null ){
            var user = this.repository.findByEmail(userEmail).orElseThrow();
            if(jwtService.isTokenValid(refreshToken, user)){
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResonse = registraterAndAuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper()
                        .writeValue(
                                response.getOutputStream(),
                                authResonse);
            }
        }
    }

}
