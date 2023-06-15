package com.smumo.inventoryservice.service;

import com.smumo.inventoryservice.dto.InventoryDTO;
import com.smumo.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    public List<InventoryDTO> isInInventory(List<String> productCode){
       return inventoryRepository.findByProductCodeIn(productCode).stream()
               .map(inventory -> InventoryDTO.builder()
                               .productCode(inventory.getProductCode())
                               .isInStock(inventory.getQuantity() > 0)
                               .build()
                       ).toList();
    }
}
