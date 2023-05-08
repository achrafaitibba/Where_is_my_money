package com.onexshield.wmm.controller;

import com.onexshield.wmm.requestDTO.accountRequestDTO;
import com.onexshield.wmm.responseDTO.accountResponseDTO;
import com.onexshield.wmm.service.accountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/account")
public class accountController {
    @Autowired
    accountService accountService;

    @PostMapping("/register")
    public accountResponseDTO addAccount(@RequestBody accountRequestDTO account){
        return accountService.addAccount(account);
    }

    @GetMapping("/find/{email}")
    public accountResponseDTO findByEmail(@PathVariable String email){
        return accountService.findByEmail(email);
    }

    @PutMapping("/update/{email}")
    public accountResponseDTO updateAccount(@RequestBody accountRequestDTO account, @PathVariable String email){
        return accountService.updateAccount(account, email);
    }

    @DeleteMapping("/delete/{email}")
    public void deleteAccount(@PathVariable String email){
        accountService.deleteAccount(email);}

    @PutMapping("/password-reset/{id}/{oldPassword}/{newPassword}")
    public ResponseEntity<String> updatePassword(@PathVariable UUID id, @PathVariable String oldPassword, @PathVariable String newPassword){
        if(accountService.updatePassword(id, oldPassword, newPassword) == 1)
            return ResponseEntity.ok("Password updated successfully");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Old password incorrect");
    }


}
