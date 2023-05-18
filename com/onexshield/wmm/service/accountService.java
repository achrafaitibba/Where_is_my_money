package com.onexshield.wmm.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.onexshield.wmm.authentication_configuration.token.JwtService;
import com.onexshield.wmm.authentication_configuration.token.Token;
import com.onexshield.wmm.authentication_configuration.token.TokenType;
import com.onexshield.wmm.model.*;
import com.onexshield.wmm.mappers.accountMapper;
import com.onexshield.wmm.repository.IAccountRepository;
import com.onexshield.wmm.repository.IAddressRepository;
import com.onexshield.wmm.repository.ISecurityAnswerRepository;
import com.onexshield.wmm.repository.ITokenRepository;
import com.onexshield.wmm.request.*;
import com.onexshield.wmm.response.accountResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
@Transactional
public class accountService {
    private final IAccountRepository accountRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ITokenRepository ITokenRepository;
    private final accountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final ISecurityAnswerRepository securityAnswerRepository;
    private final IAddressRepository addressRepository;

    public accountResponse register(registerRequest request) {

        account savedUser = accountRepository.save(accountMapper.requestToAccount(request));
        var jwtToken = jwtService.generateToken(accountMapper.requestToAccount(request));
        var refreshToken = jwtService.generateRefreshToken(accountMapper.requestToAccount(request));
        saveUserToken(savedUser, jwtToken);
        return accountMapper.accountToResponse(savedUser, jwtToken, refreshToken);
    }

    public accountResponse authenticate(authenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        account user = accountRepository.findByEmail(request.getEmail())
                .orElseThrow();
        if(user.getAccountStatus().equals(status.INACTIVE)) // to check if the account is ACTIVE before authentication, if INACTIVE ignores the rest
            return null;
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return accountMapper.accountToResponse(user, jwtToken, refreshToken);
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
            var user = this.accountRepository.findByEmail(userEmail).orElseThrow();
            if(jwtService.isTokenValid(refreshToken, user)){
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResonse = accountResponse.builder()
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

    public void deleteAccount(Integer id) {
        accountRepository.setInactive(id);
        revokeAllUserTokens(accountRepository.findByAccountId(id));

    }

    public int updatePassword(Integer id, String oldPassword, String newPassword) {
        if(passwordEncoder.matches(oldPassword, accountRepository.findByAccountId(id).getPassword()))
            return accountRepository.updatePassword(id, passwordEncoder.encode(newPassword));
        return 0;
    }


    public int recoverPassword(List<securityAnswerRequest> request, String email, String newPassword) {
        int c = 0;
        int updatedSuccess = 0;
        for (securityAnswerRequest r: request) {
            securityAnswer sa = securityAnswerRepository.findByAccount_EmailAndQuestionQuestionId(email, r.getQuestionId());
            if(sa.getAnswer().toUpperCase().equals(r.getAnswer().toUpperCase())){
                c++;
            }
        }
        if(c == 3)
            updatedSuccess = accountRepository.updatePassword(email,passwordEncoder.encode(newPassword));
        return updatedSuccess ;
    }

    public accountResponse updateUserInfos(userInfoRequest request, Integer id) {
        account accountToUpdate = accountRepository.findByAccountId(id);
        if(accountToUpdate != null){
            accountToUpdate.getPerson().setFirstName(request.getFirstName());
            accountToUpdate.getPerson().setLastName(request.getLastName());
            accountToUpdate.getPerson().setGender(gender.valueOf(request.getGender()));
            accountToUpdate.getPerson().setBirthDate(request.getBirthDate());
            accountToUpdate.getPerson().setPhoneNumber(request.getPhoneNumber());
            addressRepository.updateByAddressId(
                    request.getAddressLabel(),
                    request.getCountry(),
                    request.getCity(),
                    accountToUpdate
                            .getPerson()
                            .getAddress()
                            .getAddressId()
            ); // todo , check if address updated successfully , it should return 1;
        }
        var jwtToken = jwtService.generateToken(accountToUpdate);
        var refreshToken = jwtService.generateRefreshToken(accountToUpdate);
        revokeAllUserTokens(accountToUpdate);
        saveUserToken(accountToUpdate, jwtToken);
        return accountMapper.accountToResponse(accountRepository.save(accountToUpdate),
                jwtToken,
                refreshToken);

    }

    public accountResponse updateAccountInfos(accountInfoRequest request, Integer id) {
        account accountToUpdate = accountRepository.findByAccountId(id);
        if(accountRepository.findByEmail(request.getEmail()).isPresent() && accountToUpdate == null){
            return null;
        } else {
            accountToUpdate.setCurrency(currency.valueOf(request.getCurrency()));
            accountToUpdate.setEmail(request.getEmail());
        }

        var jwtToken = jwtService.generateToken(accountToUpdate);
        var refreshToken = jwtService.generateRefreshToken(accountToUpdate);
        revokeAllUserTokens(accountToUpdate);
        saveUserToken(accountToUpdate, jwtToken);
        return accountMapper.accountToResponse(accountRepository.save(accountToUpdate),
                jwtToken,
                refreshToken);

    }

    public accountResponse updateSecurityInfos(List<securityAnswerRequest> request, Integer id) {
        account accountToUpdate = accountRepository.findByAccountId(id);
        for(securityAnswerRequest sa : request){
            securityAnswerRepository.updateByAccount_AccountIdAndAnswerId(
                    sa.getAnswerId(),
                    id,
                    sa.getAnswer(),
                    sa.getQuestionId()
            );
        };
        var jwtToken = jwtService.generateToken(accountToUpdate);
        var refreshToken = jwtService.generateRefreshToken(accountToUpdate);
        revokeAllUserTokens(accountToUpdate);
        saveUserToken(accountToUpdate, jwtToken);
        return accountMapper.accountToResponse(accountRepository.save(accountToUpdate),
                jwtToken,
                refreshToken);
    }
}
