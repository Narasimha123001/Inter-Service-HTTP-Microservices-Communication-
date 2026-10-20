package com.techtricks.ecom_order_servise.services;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import com.techtricks.ecom_order_servise.InventoryDTO;
import com.techtricks.ecom_order_servise.client.InventoryClient;

@Service
public class OrderService {

    private final RestTemplate restTemplate;

    private final RestClient restClient;

    private final InventoryClient inventoryClient;

    public OrderService(RestTemplate restTemplate, RestClient restClient, InventoryClient inventoryClient) {
        this.restTemplate = restTemplate;
        this.restClient = restClient;
        this.inventoryClient = inventoryClient;
    }

    public String placeOrder(Long productId) {

        /*
         * here i am using restTemplate
         */

        // String response = restTemplate.getForObject(
        // "http://localhost:8080/inventory/" + productId,
        // String.class);

        /*
         * 
         * Here i am using rest client to get the inventory of the product
         * 
         */

        // ResponseEntity<InventoryDTO> response = restClient.get()
        // .uri("http://localhost:8080/inventory/check/{productId}", productId)
        // .retrieve()
        // .onStatus(HttpStatusCode::is4xxClientError, (request, response1) -> {
        // throw new RuntimeException("Inventory not found");
        // })
        // .toEntity(InventoryDTO.class);

        // // TODO: now i want to reduce the inventory of the product

        // updateInventory(response.getBody());

        // return response != null && response.getBody().getQuantity() > 0 ? "order
        // Placed" : "Out Of Order";

        /*
         * now i am using Feign client to get the inventory of the product
         */

        InventoryDTO inventory = inventoryClient.getInventory(productId);

        int quantity = inventory.getQuantity();
        updateInventory(inventory);

        return quantity != 0 ? "Order placed" : "Out of Stock";

    }

    private void updateInventory(InventoryDTO inventory) {
        inventory.setQuantity(inventory.getQuantity() - 1);
        // restClient.post()
        // .uri("http://localhost:8080/inventory")
        // .body(inventory)
        // .retrieve()
        // .toBodilessEntity();

        inventoryClient.updateInventory(inventory);
    }
}
