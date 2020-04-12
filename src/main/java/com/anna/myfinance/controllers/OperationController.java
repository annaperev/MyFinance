package com.anna.myfinance.controllers;

import com.anna.myfinance.dal.entities.Operation;
import com.anna.myfinance.dal.repos.OperationRepo;
import com.anna.myfinance.errors.ResourceNotFoundException;
import com.anna.myfinance.errors.ValidationException;
import com.anna.myfinance.services.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/operations")
public class OperationController {

    private final OperationRepo operationRepo;

    private final OperationService operationService;

    public OperationController(OperationRepo operationRepo, OperationService operationService) {
        this.operationRepo = operationRepo;
        this.operationService = operationService;
    }

    @GetMapping()
    public List<Operation> getAllOperations() {
        return operationRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operation> getOperationById(@PathVariable(value = "id") Long operationId)
            throws ResourceNotFoundException {
        Operation operation = operationRepo.findById(operationId)
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found for this id :: " + operationId));
        return ResponseEntity.ok().body(operation);
    }

    @PostMapping()
    public Operation createOperation(/*@Valid*/ @RequestBody Operation operation) throws ValidationException {//TODO
        return operationService.save(operation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operation> updateOperation(@PathVariable(value = "id") Long operationId,
                                                   /*@Valid*/ @RequestBody Operation operationDetails) throws ResourceNotFoundException {//TODO
        Operation operation = operationRepo.findById(operationId)
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found for this id :: " + operationId));

        operation.setAccount(operationDetails.getAccount());
        operation.setAmount(operationDetails.getAmount());
        operation.setDate(operationDetails.getDate());
        operation.setItem(operationDetails.getItem());
        operation.setComment(operationDetails.getComment());
        final Operation updatedOperation = operationRepo.save(operation);
        return ResponseEntity.ok(updatedOperation);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteOperation(@PathVariable(value = "id") Long operationId)
            throws ResourceNotFoundException {
        Operation operation = operationRepo.findById(operationId)
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found for this id :: " + operationId));

        operationRepo.delete(operation);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
