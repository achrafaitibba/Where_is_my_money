package com.onexshield.wmm.controller;

import com.onexshield.wmm.DTO.request.operationRequest;
import com.onexshield.wmm.DTO.response.operationReponse;
import com.onexshield.wmm.service.operationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/operation")
public class operationController {
    @Autowired
    operationService operationService;

    @PostMapping("/add")
    public operationReponse addOperation(@RequestBody operationRequest operation){
        return operationService.createOperation(operation);
    }

    @PutMapping("/update/{id}")
    public operationReponse updateOperation(@RequestBody operationRequest operationRequest, @PathVariable UUID id){
        return operationService.updateOperation(operationRequest, id);
    }
    @GetMapping("/all/{id}")
    public List<operationReponse> getAll(@PathVariable UUID id){
        return operationService.getAllOperationsByAccount(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOperation(@PathVariable UUID id){
        operationService.deleteOperation(id);
    }
}
