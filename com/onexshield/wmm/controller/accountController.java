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
            summary = "Registration",
            description = "To create a new account you need to provide the infos below int the Example Value|Schema.<br>" +
                    "The accepted values for gender('MALE','FEMALE') must be UPPER_CASE<br>" +
                    "The accepted values for currency('MAD','EUR','USD') must be UPPER_CASE<br> " +
                    "The accepted format for birth date ('YYYY-MM-DD')" +
                    "The security questions must have a list of 3 security questions/answers",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "The account you are trying to reach has been deleted",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Account already exist",
                            responseCode = "409"
                    )

            }
    )
    @PostMapping("/register")
    public ResponseEntity<accountResponse> register(
            @RequestBody registerRequest request
    ){
        return ResponseEntity.ok(accountService.register(request));
    }

    @Operation(
            summary = "Authentication",
            description = "To authenticate you need to enter the username/email and the password corresponding the the email used",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "The account you are trying to reach has been deleted",
                            responseCode = "400"
                    ),
                    @ApiResponse(
                            description = "Account doesn't exist",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "The password you entered is incorrect",
                            responseCode = "409"
                    )

            }
    )
    @PostMapping("/authenticate")
    public ResponseEntity<accountResponse> authenticate(
            @RequestBody authenticationRequest request
    ){
        return ResponseEntity.ok(accountService.authenticate(request));

    }
    @Operation(
            summary = "Account deletion",
            description = "To delete an account you need to pass the account id via url params",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Account doesn't exist",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteAccount(@PathVariable Integer id){

        return ResponseEntity.ok(accountService.deleteAccount(id));

    }

    @Operation(
            summary = "Password reset",
            description = "To reset the password you need to pass the account id, the old password and the new password via url params",
            responses = {
                    @ApiResponse(
                            description = "Success, response body = 1",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "The password you entered is incorrect",
                            responseCode = "409"
                    ),
                    @ApiResponse(
                            description = "Anything else, it returns Forbidden 403",
                            responseCode = "403"
                    )
            }
    )
    @PutMapping("/password-reset/{id}/{oldPassword}/{newPassword}") //todo you stopped the documentaion here hh
    public ResponseEntity<Integer> updatePassword(@PathVariable Integer id,
                                                  @PathVariable String oldPassword,
                                                  @PathVariable String newPassword){
        return ResponseEntity.ok(accountService.updatePassword(id, oldPassword, newPassword));
    }

    @Operation(
            summary = "Password recovery",
            description = "To recover the password you need to pass the email and the new password via url params <br>" +
                    "And you should insert the security questions chosen while the account creation, with the corresponding answer" +
                    " inserted by the user while creating their account, all the security answer should be inside the request body",
            responses = {
                    @ApiResponse(
                            description = "Success, response body = 1",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "The EMAIL/username you entered doesn't belong to anyone",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Security Questions OR Answers are incorrect",
                            responseCode = "403"
                    )
            }
    )
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
