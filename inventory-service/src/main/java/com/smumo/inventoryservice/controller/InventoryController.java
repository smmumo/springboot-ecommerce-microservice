package com.smumo.inventoryservice.controller;

import com.smumo.inventoryservice.dto.InventoryDTO;
import com.smumo.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping()
    public List<InventoryDTO> isInStock(@RequestParam List<String> productCode){
        return inventoryService.isInInventory(productCode);
    }
}
