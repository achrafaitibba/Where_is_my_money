package com.onexshield.wmm.controller;

import com.onexshield.wmm.model.operation;
import com.onexshield.wmm.service.operationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation")
public class operationController {
    @Autowired
    operationService operationService;

    @PostMapping("/add")
    public operation addOperation(@RequestBody operation operation){
        return operationService.createOperation(operation);
    }

    @GetMapping("/all/{id}")
    public List<operation> getAll(@PathVariable  Integer id){
        return operationService.getAllOperationsByAccount(id);
    }
}
