package com.onexshield.wmm.controller;



import com.onexshield.wmm.request.*;
import com.onexshield.wmm.response.accountResponse;
import com.onexshield.wmm.service.accountService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
@Tag(name = "Account")
// you can use the following instead of implementing
// it inside the apiConfiguration that will be applied for all the api
//@SecurityRequirement(name = "bearerAuth")
public class accountController {
    private final accountService accountService;


    @Operation(
            description = "To create a new account",
            summary = "This is the endpoint to create a new account", // todo  , check this
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )

            }
    )
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

    @PutMapping("/password-reset/{id}/{oldPassword}/{newPassword}")
    public ResponseEntity<Integer> updatePassword(@PathVariable Integer id,
                                                  @PathVariable String oldPassword,
                                                  @PathVariable String newPassword){
        return ResponseEntity.ok(accountService.updatePassword(id, oldPassword, newPassword));
    }

    @PutMapping("/password-recovery/{email}/{newPassword}")
    public ResponseEntity<Object> recoverPassword(@RequestBody List<securityAnswerRequest> request,
                                                   @PathVariable String email,
                                                   @PathVariable String newPassword){
        return ResponseEntity.ok(accountService.recoverPassword(request, email, newPassword));
    }

    @PutMapping("/update/user-infos/{id}")
    public ResponseEntity<accountResponse> updateUserInfos(@RequestBody userInfoRequest request,
                                                           @PathVariable Integer id){
        return ResponseEntity.ok(accountService.updateUserInfos(request, id));
    }

    @PutMapping("/update/account-infos/{id}")
    public ResponseEntity<accountResponse> updateAccountInfos(@RequestBody accountInfoRequest request,
                                                              @PathVariable Integer id){
        return ResponseEntity.ok(accountService.updateAccountInfos(request, id));
    }

    @PutMapping("update/security-infos/{id}")
    public ResponseEntity<accountResponse> updateSecurityInfos(@RequestBody List<securityAnswerRequest> request,
                                                               @PathVariable Integer id){
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
