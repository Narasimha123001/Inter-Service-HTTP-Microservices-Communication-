package com.techtricks.ecom_inventory_servise.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.techtricks.ecom_inventory_servise.dao.InventoryRepository;
import com.techtricks.ecom_inventory_servise.model.Inventory;

@Service
public class InventoryServices {

    private final InventoryRepository inventoryRepository;

    public InventoryServices(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // Get all inventories
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    // Get inventory by product ID
    public Inventory getInventory(Long productId) {
        return inventoryRepository.findById(productId).orElse(null);
    }

    // Add / create new inventory
    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    // Update existing inventory
    public Inventory updateInventory(Long productId, Inventory inventoryDetails) {
        Inventory existing = inventoryRepository.findById(productId).orElse(null);
        if (existing != null) {
            existing.setQuantity(inventoryDetails.getQuantity());
            return inventoryRepository.save(existing);
        }
        inventoryDetails.setProductId(productId);
        return inventoryRepository.save(inventoryDetails);
    }

    // Check stock status
    public Inventory checkStock(Long productId) {
        Optional<Inventory> inv = inventoryRepository.findById(productId);
        return inv.get();

    }
}
