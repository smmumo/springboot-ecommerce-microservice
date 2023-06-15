package com.smumo.inventoryservice.service;

import com.smumo.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    public boolean isInInventory(String productCode){
       return inventoryRepository.findByProductCode(productCode).isPresent();
    }
}
