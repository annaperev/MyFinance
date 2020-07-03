package com.anna.myfinance.controllers;

import com.anna.myfinance.dal.entities.Currency;
import com.anna.myfinance.dal.repos.CurrencyRepo;
import com.anna.myfinance.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    @Autowired
    private CurrencyRepo currencyRepo;

    @GetMapping("/currencies")
    public List<Currency> getAllCurrencies() {
        return currencyRepo.findAll();
    }

    @GetMapping("/currencies/{id}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable(value = "id") Long currencyId)
            throws ResourceNotFoundException {
        Currency currency = currencyRepo.findById(currencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found for this id :: " + currencyId));
        return ResponseEntity.ok().body(currency);
    }

    @PostMapping("/currencies")
    public Currency createCurrency(/*@Valid*/ @RequestBody Currency currency) {//TODO
        return currencyRepo.save(currency);
    }

    @PutMapping("/currencies/{id}")
    public ResponseEntity<Currency> updateCurrency(@PathVariable(value = "id") Long currencyId,
                                                   /*@Valid*/ @RequestBody Currency currencyDetails) throws ResourceNotFoundException {//TODO
        Currency currency = currencyRepo.findById(currencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found for this id :: " + currencyId));

        currency.setShortName(currencyDetails.getShortName());
        currency.setLongName(currencyDetails.getLongName());
        final Currency updatedCurrency = currencyRepo.save(currency);
        return ResponseEntity.ok(updatedCurrency);
    }

    @DeleteMapping("/currencies/{id}")
    public Map<String, Boolean> deleteCurrency(@PathVariable(value = "id") Long currencyId)
            throws ResourceNotFoundException {
        Currency currency = currencyRepo.findById(currencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found for this id :: " + currencyId));

        currencyRepo.delete(currency);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
