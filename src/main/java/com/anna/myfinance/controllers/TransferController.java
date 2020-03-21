package com.anna.myfinance.controllers;

import com.anna.myfinance.dal.entities.Transfer;
import com.anna.myfinance.dal.repos.TransferRepo;
import com.anna.myfinance.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransferController {

    @Autowired
    private TransferRepo transferRepo;

    @GetMapping("/transfers")
    public List<Transfer> getAllTransfers() {
        return transferRepo.findAll();
    }

    @GetMapping("/transfers/{id}")
    public ResponseEntity<Transfer> getTransferById(@PathVariable(value = "id") Long transferId)
            throws ResourceNotFoundException {
        Transfer transfer = transferRepo.findById(transferId)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found for this id :: " + transferId));
        return ResponseEntity.ok().body(transfer);
    }

    @PostMapping("/transfers")
    public Transfer createTransfer(/*@Valid*/ @RequestBody Transfer transfer) {//TODO
        return transferRepo.save(transfer);
    }

    @PutMapping("/transfers/{id}")
    public ResponseEntity<Transfer> updateTransfer(@PathVariable(value = "id") Long transferId,
                                                   /*@Valid*/ @RequestBody Transfer transferDetails) throws ResourceNotFoundException {//TODO
        Transfer transfer = transferRepo.findById(transferId)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found for this id :: " + transferId));

        transfer.setDate(transferDetails.getDate());
        transfer.setAccountFrom(transferDetails.getAccountFrom());
        transfer.setAccountTo(transferDetails.getAccountTo());
        transfer.setAmountFrom(transferDetails.getAmountFrom());
        transfer.setAmountTo(transferDetails.getAmountTo());
        final Transfer updatedTransfer = transferRepo.save(transfer);
        return ResponseEntity.ok(updatedTransfer);
    }

    @DeleteMapping("/transfers/{id}")
    public Map<String, Boolean> deleteTransfer(@PathVariable(value = "id") Long transferId)
            throws ResourceNotFoundException {
        Transfer transfer = transferRepo.findById(transferId)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found for this id :: " + transferId));

        transferRepo.delete(transfer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
