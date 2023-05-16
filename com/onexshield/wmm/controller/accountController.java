package com.onexshield.wmm.controller;



import com.onexshield.wmm.request.authenticationRequest;
import com.onexshield.wmm.request.registerRequest;
import com.onexshield.wmm.response.registraterAndAuthenticationResponse;
import com.onexshield.wmm.service.accountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class accountController {
    private final accountService accountService;

    @PostMapping("/register")
    public ResponseEntity<registraterAndAuthenticationResponse> register(
            @RequestBody registerRequest request
    ){
        return ResponseEntity.ok(accountService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<registraterAndAuthenticationResponse> register(
            @RequestBody authenticationRequest request
    ){
        return ResponseEntity.ok(accountService.authenticate(request));

    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        accountService.refreshToken(request, response);
    }

}
