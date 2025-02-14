package com.eldar.products_service.services.contracts;

import com.eldar.products_service.dtos.requests.ProductRequestDTO;
import com.eldar.products_service.dtos.responses.ProductResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> getAll();

    ProductResponseDTO getById(Long id);

    void add(@Valid ProductRequestDTO productRequestDTO);

    void update(Long id, ProductRequestDTO productRequestDTO);

    void delete(Long id);

   void sell(Long id, int quantity);

    void revertStock(Long id, int quantity);
}
