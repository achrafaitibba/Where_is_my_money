package com.onexshield.wmm.controller;


import com.onexshield.wmm.request.operationRequest;
import com.onexshield.wmm.response.operationReponse;
import com.onexshield.wmm.service.operationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/operation")
public class operationController { //todo ,review all the endpoints in this controller
    @Autowired
    operationService operationService;

    @PostMapping("/add")
    public operationReponse addOperation(@RequestBody operationRequest operation){
        return operationService.createOperation(operation);
    }

    @GetMapping("/all/{id}")
    public List<operationReponse> getAll(@PathVariable Integer id){
        return operationService.getAllOperationsByAccount(id);
    }
    @PutMapping("/update/{id}")
    public operationReponse updateOperation(@RequestBody operationRequest operationRequest, @PathVariable Integer id){
        return operationService.updateOperation(operationRequest, id);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteOperation(@PathVariable Integer id){
        operationService.deleteOperation(id);
    }
}
