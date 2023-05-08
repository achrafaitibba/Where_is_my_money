package com.onexshield.wmm.controller;

import com.onexshield.wmm.model.account;
import com.onexshield.wmm.requestDTO.accountRequestDTO;
import com.onexshield.wmm.responseDTO.accountResponseDTO;
import com.onexshield.wmm.service.accountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public account findByEmail(@PathVariable String email){
        return accountService.findByEmail(email);
    }

    @PutMapping("/update/{email}")
    public account updateAccount(@RequestBody account account, @PathVariable String email){
        return accountService.updateAccount(account, email);
    }

    @DeleteMapping("/delete/{email}")
    public void deleteAccount(@PathVariable String email){
        accountService.deleteAccount(email);}

}
