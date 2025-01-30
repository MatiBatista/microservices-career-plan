package com.eldar.products_service.services.contracts;


import com.eldar.products_service.dtos.requests.CategoryRequestDTO;
import com.eldar.products_service.dtos.responses.CategoryResponseDTO;
import com.eldar.products_service.models.Category;
import jakarta.validation.Valid;

import java.util.List;

public interface CategoryService {

    Category getCategoryById(Long id);

    CategoryResponseDTO getById(Long id);

    List<CategoryResponseDTO> getAll();

    void add(@Valid CategoryRequestDTO categoryRequestDTO);

    void update(Long id, CategoryRequestDTO categoryRequestDTO);

    void delete(Long id);
}
