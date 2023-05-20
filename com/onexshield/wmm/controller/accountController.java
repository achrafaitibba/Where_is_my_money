package com.onexshield.wmm.controller;



import com.onexshield.wmm.request.*;
import com.onexshield.wmm.response.accountResponse;
import com.onexshield.wmm.service.accountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class accountController {
    private final accountService accountService;


    @PostMapping("/register")
    public ResponseEntity<accountResponse> register(
            @RequestBody registerRequest request
    ){
        return ResponseEntity.ok(accountService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<accountResponse> authenticate(
            @RequestBody authenticationRequest request
    ){
        return ResponseEntity.ok(accountService.authenticate(request));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteAccount(@PathVariable Integer id){

        return ResponseEntity.ok(accountService.deleteAccount(id));

    }

    @PutMapping("/password-reset/{id}/{oldPassword}/{newPassword}") // todo /return String ?? do a better return, think about something else
    public ResponseEntity<String> updatePassword(@PathVariable Integer id, @PathVariable String oldPassword, @PathVariable String newPassword){
        if(accountService.updatePassword(id, oldPassword, newPassword) == 1)
            return ResponseEntity.ok("Password updated successfully");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Old password incorrect");
    }

    @PutMapping("/password-recovery/{email}/{newPassword}")
    public ResponseEntity<Integer>  recoverPassword(@RequestBody List<securityAnswerRequest> request,
                               @PathVariable String email,
                               @PathVariable String newPassword){
        return ResponseEntity.ok(accountService.recoverPassword(request, email, newPassword));
    }

    @PutMapping("/update/user-infos/{id}")   // todo , check if address updated successfully , it should return 1;
    public ResponseEntity<accountResponse> updateUserInfos(@RequestBody userInfoRequest request, @PathVariable Integer id){
        return ResponseEntity.ok(accountService.updateUserInfos(request, id));
    }

    @PutMapping("/update/account-infos/{id}")
    public ResponseEntity<accountResponse> updateAccountInfos(@RequestBody accountInfoRequest request, @PathVariable Integer id){
        return ResponseEntity.ok(accountService.updateAccountInfos(request, id));
    }

    @PutMapping("update/security-infos/{id}")
    public ResponseEntity<accountResponse> updateSecurityInfos(@RequestBody List<securityAnswerRequest> request, @PathVariable Integer id){
        return ResponseEntity.ok(accountService.updateSecurityInfos(request, id));
    }

    @PostMapping("/refresh-token") // todo /keep using it or no ?
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    )throws Exception{
        accountService.refreshToken(request, response);
    }

}
