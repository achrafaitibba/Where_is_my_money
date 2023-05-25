package com.onexshield.wmm.controller;



import com.onexshield.wmm.request.operationRequest;
import com.onexshield.wmm.request.operationStatsRequest;
import com.onexshield.wmm.response.operationReponse;
import com.onexshield.wmm.response.operationStatsResponse;
import com.onexshield.wmm.service.operationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/operation")
@RequiredArgsConstructor
@Tag(name = "Operation")
public class operationController {
    private final operationService operationService;

    @PostMapping("/add")
    public ResponseEntity<operationReponse> addOperation (@RequestBody operationRequest operation){
        return ResponseEntity.ok(operationService.createOperation(operation));
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<operationReponse>> getAll(@PathVariable Integer id){
        return ResponseEntity.ok(operationService.getAllOperationsByAccount(id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOperation(@PathVariable Integer id){
        operationService.deleteOperation(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<operationReponse> getOpereration(@PathVariable Integer id){
        return ResponseEntity.ok(operationService.getOperation(id));
    }

    @PutMapping("/update/{id}")
    public operationReponse updateOperation(@RequestBody operationRequest operationRequest, @PathVariable Integer id){
        return operationService.updateOperation(operationRequest, id);
    }


    @GetMapping("/all/stats/{id}") // todo / add limits? max result for each frame:31 for day/week/month, 10 for year?
    public ResponseEntity<List<operationStatsResponse>> getStats(@RequestBody operationStatsRequest request,
                                          @PathVariable Integer id) throws Exception{
        return ResponseEntity.ok(operationService.getStats(id, request));
    }

}
