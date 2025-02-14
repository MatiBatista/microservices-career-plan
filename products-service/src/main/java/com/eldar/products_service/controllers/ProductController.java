package com.eldar.products_service.controllers;

import com.eldar.products_service.dtos.requests.ProductRequestDTO;
import com.eldar.products_service.dtos.responses.ProductResponseDTO;
import com.eldar.products_service.services.contracts.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    @Operation(summary = "Get all products", description = "Retrieve all products")
    public ResponseEntity<List<ProductResponseDTO>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by id", description = "Retrieve a product by its id")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Add product", description = "Add a new product")
    public ResponseEntity<Void> add(@Valid @RequestBody ProductRequestDTO productRequestDTO){
        productService.add(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update product", description = "Update an existing product")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO){
        productService.update(id,productRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/sell/{id}")
    @Operation(summary = "Sell product", description = "Sell an existing product")
    public ResponseEntity<Void> sell(@PathVariable Long id, @RequestParam int quantity ){
        productService.sell(id,quantity);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/revertStock/{id}")
    @Operation(summary = "Revert stock product", description = "Revert stock for product")
    public ResponseEntity<Void> revertStock(@PathVariable Long id, @RequestParam int quantity){
        productService.revertStock(id,quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product", description = "Delete a product by its id")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.ok().build();
    }


}
