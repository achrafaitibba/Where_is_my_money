package com.onexshield.wmm.controller;

import com.onexshield.wmm.model.account;
import com.onexshield.wmm.service.accountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class accountController {
    @Autowired
    accountService accountService;

    @PostMapping("/register")
    public account addAccount(@RequestBody account account){
        return accountService.addAccount(account);
    }
}
