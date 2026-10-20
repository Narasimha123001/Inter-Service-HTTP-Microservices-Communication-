package com.techtricks.ecom_order_servise.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.techtricks.ecom_order_servise.InventoryDTO;
import com.techtricks.ecom_order_servise.config.InventoryFeginClient;

@FeignClient(name = "inventory-service", url = "http://localhost:8080", configuration = InventoryFeginClient.class)
public interface InventoryClient {

    @GetMapping("/inventory/{productId}")
    public InventoryDTO getInventory(@PathVariable Long productId);

    @PostMapping("/inventory")
    public String updateInventory(@RequestBody InventoryDTO inventoryDTO);
}
