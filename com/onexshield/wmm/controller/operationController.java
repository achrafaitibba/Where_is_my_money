package com.onexshield.wmm.controller;

import com.onexshield.wmm.requestDTO.operationRequestDTO;
import com.onexshield.wmm.responseDTO.operationReponseDTO;
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
    public operationReponseDTO addOperation(@RequestBody operationRequestDTO operation){
        return operationService.createOperation(operation);
    }

    @GetMapping("/all/{id}")
    public List<operationReponseDTO> getAll(@PathVariable UUID id){
        return operationService.getAllOperationsByAccount(id);
    }
}
