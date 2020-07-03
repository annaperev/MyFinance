package com.anna.myfinance.controllers;

import com.anna.myfinance.dal.entities.Item;
import com.anna.myfinance.dal.repos.ItemRepo;
import com.anna.myfinance.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemRepo itemRepo;

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable(value = "id") Long ItemId)
            throws ResourceNotFoundException {
        Item item = itemRepo.findById(ItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + ItemId));
        return ResponseEntity.ok().body(item);
    }

    @PostMapping("/items")
    public Item createItem(/*@Valid*/ @RequestBody Item item) {//TODO
        return itemRepo.save(item);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable(value = "id") Long ItemId,
                                                   /*@Valid*/ @RequestBody Item itemDetails) throws ResourceNotFoundException {//TODO
        Item item = itemRepo.findById(ItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + ItemId));

        item.setName(itemDetails.getName());
        item.setParentItem(itemDetails.getParentItem());
        item.setType(itemDetails.getType());
        final Item updatedItem = itemRepo.save(item);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/items/{id}")
    public Map<String, Boolean> deleteItem(@PathVariable(value = "id") Long itemId)
            throws ResourceNotFoundException {
        Item item = itemRepo.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemId));

        itemRepo.delete(item);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
