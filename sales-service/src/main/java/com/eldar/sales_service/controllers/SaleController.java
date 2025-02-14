package com.eldar.sales_service.controllers;

import com.eldar.sales_service.dtos.requests.SaleRequestDTO;
import com.eldar.sales_service.dtos.responses.SaleResponseDTO;
import com.eldar.sales_service.dtos.responses.SaleResponseDTOAll;
import com.eldar.sales_service.services.contracts.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    @Operation(summary = "Add sale", description = "Add a new sale")
    public ResponseEntity<Void> add(@Valid @RequestBody SaleRequestDTO saleRequestDTO, HttpServletRequest httpServletRequest){
        String authHeader=httpServletRequest.getHeader("Authorization");
        saleService.add(saleRequestDTO,authHeader);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Get all sales",description = "Get all sales")
    public ResponseEntity<List<SaleResponseDTOAll>> getAll(HttpServletRequest httpServletRequest){
        String authHeader=httpServletRequest.getHeader("Authorization");
        return ResponseEntity.ok(saleService.getAll(authHeader));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get sell by id",description = "Get sale by id")
    public ResponseEntity<SaleResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(saleService.getById(id));
    }



    /*
    @GetMapping()
    @Operation(summary = "Get all sales", description = "Retrieve all sales")
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
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update product", description = "Update an existing customer")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO){
        productService.update(id,productRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product", description = "Delete a product by its id")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.ok().build();
    } */

}
