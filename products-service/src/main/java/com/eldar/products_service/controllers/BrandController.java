package com.eldar.products_service.controllers;


import com.eldar.products_service.dtos.requests.BrandRequestDTO;
import com.eldar.products_service.dtos.requests.CategoryRequestDTO;
import com.eldar.products_service.dtos.responses.BrandResponseDTO;
import com.eldar.products_service.dtos.responses.CategoryResponseDTO;
import com.eldar.products_service.services.contracts.BrandService;
import com.eldar.products_service.services.contracts.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping()
    @Operation(summary = "Get all brands", description = "Retrieve all brands")
    public ResponseEntity<List<BrandResponseDTO>> getAll(){
        return ResponseEntity.ok(brandService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get brand by id", description = "Retrieve a brand by its id")
    public ResponseEntity<BrandResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(brandService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Add brand", description = "Add a new brand")
    public ResponseEntity<Void> add(@Valid @RequestBody BrandRequestDTO brandRequestDTO){
        brandService.add(brandRequestDTO);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update brand", description = "Update an existing brand")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody BrandRequestDTO brandRequestDTO){
        brandService.update(id,brandRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete brand", description = "Delete a brand by its id")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        brandService.delete(id);
        return ResponseEntity.ok().build();
    }

}
