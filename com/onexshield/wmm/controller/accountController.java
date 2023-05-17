package com.onexshield.wmm.controller;



import com.onexshield.wmm.request.authenticationRequest;
import com.onexshield.wmm.request.registerRequest;
import com.onexshield.wmm.response.accountResponse;
import com.onexshield.wmm.service.accountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class accountController {
    private final accountService accountService;


    @PostMapping("/register")
    public ResponseEntity<accountResponse> register(
            @RequestBody registerRequest request
    ){
        return ResponseEntity.ok(accountService.register(request)); //todo , what if account INACTIVE, what should be the return
    }

    @PostMapping("/authenticate")
    public ResponseEntity<accountResponse> register(
            @RequestBody authenticationRequest request
    ){
        return ResponseEntity.ok(accountService.authenticate(request)); //todo , what if account INACTIVE, what should be the return

    }
    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable Integer id){
        accountService.deleteAccount(id);
        }

    @PutMapping("/password-reset/{id}/{oldPassword}/{newPassword}")
    public ResponseEntity<String> updatePassword(@PathVariable Integer id, @PathVariable String oldPassword, @PathVariable String newPassword){
        if(accountService.updatePassword(id, oldPassword, newPassword) == 1)
            return ResponseEntity.ok("Password updated successfully");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Old password incorrect");
    }


    // todo password recovery with securityQuestions
    // todo update person infos, security infos, account infos :  an endpoint for each of them !?
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        accountService.refreshToken(request, response);
    }

}
