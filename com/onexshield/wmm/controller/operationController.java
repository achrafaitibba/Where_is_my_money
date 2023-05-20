package com.onexshield.wmm.controller;


import com.onexshield.wmm.request.operationRequest;
import com.onexshield.wmm.response.operationReponse;
import com.onexshield.wmm.service.operationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation")
@RequiredArgsConstructor
public class operationController {
        //todo hardcoding pagination and sorting, or use graphQL ?
    private final operationService operationService;

    @PostMapping("/add")
    public operationReponse addOperation(@RequestBody operationRequest operation){
        return operationService.createOperation(operation);
    }

    @GetMapping("/all/{id}")
    public List<operationReponse> getAll(@PathVariable Integer id){
        return operationService.getAllOperationsByAccount(id);
    }

    @DeleteMapping("/delete/{id}") //todo , change the return type ?
    public void deleteOperation(@PathVariable Integer id){
        operationService.deleteOperation(id);
    }

    @PutMapping("/update/{id}") //todo test it if it have the same problem as accountUpdateInfos
    public operationReponse updateOperation(@RequestBody operationRequest operationRequest, @PathVariable Integer id){
        return operationService.updateOperation(operationRequest, id);
    }



}
