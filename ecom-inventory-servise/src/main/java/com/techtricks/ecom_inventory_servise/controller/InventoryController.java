package com.techtricks.ecom_inventory_servise.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techtricks.ecom_inventory_servise.model.Inventory;
import com.techtricks.ecom_inventory_servise.services.InventoryServices;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryServices inventoryServices;

    public InventoryController(InventoryServices inventoryServices) {
        this.inventoryServices = inventoryServices;
    }

    // POST: Add new inventory
    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryServices.addInventory(inventory);
    }

    // GET: Get all inventories
    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryServices.getAllInventory();
    }

    // GET: Get inventory by productId
    @GetMapping("/{productId}")
    public Inventory getInventory(@PathVariable Long productId) throws InterruptedException {
        // Thread.sleep(5000);
        System.out.println("Inventory request for product id:" + productId);
        return inventoryServices.getInventory(productId);
    }

    // PUT: Update inventory quantity by productId
    @PutMapping("/{productId}")
    public Inventory updateInventory(@PathVariable Long productId, @RequestBody Inventory inventory) {
        return inventoryServices.updateInventory(productId, inventory);
    }

    // GET: Check stock status ("InStock" / "Out of Stock")
    @GetMapping("/check/{productId}")
    public Inventory checkInventory(@PathVariable Long productId) {

        return inventoryServices.checkStock(productId);
    }
}
