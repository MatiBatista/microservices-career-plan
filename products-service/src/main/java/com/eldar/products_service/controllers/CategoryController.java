package com.eldar.products_service.controllers;


import com.eldar.products_service.dtos.requests.CategoryRequestDTO;
import com.eldar.products_service.dtos.requests.ProductRequestDTO;
import com.eldar.products_service.dtos.responses.CategoryResponseDTO;
import com.eldar.products_service.dtos.responses.ProductResponseDTO;
import com.eldar.products_service.services.contracts.CategoryService;
import com.eldar.products_service.services.contracts.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    @Operation(summary = "Get all categories", description = "Retrieve all categories")
    public ResponseEntity<List<CategoryResponseDTO>> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id", description = "Retrieve a category by its id")
    public ResponseEntity<CategoryResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Add category", description = "Add a new category")
    public ResponseEntity<Void> add(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO){
        categoryService.add(categoryRequestDTO);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update category", description = "Update an existing category")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CategoryRequestDTO categoryRequestDTO){
        categoryService.update(id,categoryRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category", description = "Delete a category by its id")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

}
