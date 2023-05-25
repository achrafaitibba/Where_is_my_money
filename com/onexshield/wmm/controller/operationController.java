package com.onexshield.wmm.controller;

import com.onexshield.wmm.request.operationRequest;
import com.onexshield.wmm.request.operationStatsRequest;
import com.onexshield.wmm.response.operationReponse;
import com.onexshield.wmm.response.operationStatsResponse;
import com.onexshield.wmm.service.operationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Operation creation",
            description = "To create a new operation you should send a request body similar to the one in the example below",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden or token expired",
                            responseCode = "403"
                    )
                    ,
                    @ApiResponse(
                            description = "Account doesn't exist",
                            responseCode = "404"
                    )
            }
    )
    @PostMapping("/add")
    public ResponseEntity<operationReponse> addOperation (@RequestBody operationRequest operation){
        return ResponseEntity.ok(operationService.createOperation(operation));
    }

    @Operation(
            summary = "All operations",
            description = "To get all the operations you should pass the account id via the url path",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden or token expired",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping("/all/{id}")
    public ResponseEntity<List<operationReponse>> getAll(@PathVariable Integer id){
        return ResponseEntity.ok(operationService.getAllOperationsByAccount(id));
    }

    @Operation(
            summary = "Operation deletion",
            description = "To delete an operation you should pass the operation id via the url path",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden or token expired",
                            responseCode = "403"
                    )
            }
    )
    @DeleteMapping("/delete/{id}")
    public void deleteOperation(@PathVariable Integer id){
        operationService.deleteOperation(id);
    }

    @Operation(
            summary = "Get operation details",
            description = "To get an operation you should pass the operation id via the url path",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden or token expired",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<operationReponse> getOpereration(@PathVariable Integer id){
        return ResponseEntity.ok(operationService.getOperation(id));
    }
    @Operation(
            summary = "Operation update",
            description = "To update an operation you should pass the operation id via the url path",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden or token expired",
                            responseCode = "403"
                    )
            }
    )
    @PutMapping("/update/{id}")
    public operationReponse updateOperation(@RequestBody operationRequest operationRequest, @PathVariable Integer id){
        return operationService.updateOperation(operationRequest, id);
    }

    @Operation(
            summary = "Operations statistics",
            description = "To get statistics you should pass the account id via the url path<br>" +
                    "Also the date format for the startDate property should be in the format bellow<br>" +
                    "('YYYY-MM-DD') <br>" +
                    "It returns stats based on the frame chosen by the client side, and the start date till the current date<br>" +
                    "No limit output, it returns a list of [0:+oo] objects of stats<br>" +
                    "The client is responsible for the return limit based on their chart size/type/dateFrame",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Forbidden or token expired",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping("/all/stats/{id}")
    public ResponseEntity<List<operationStatsResponse>> getStats(@RequestBody operationStatsRequest request,
                                          @PathVariable Integer id) throws Exception{
        return ResponseEntity.ok(operationService.getStats(id, request));
    }

}
