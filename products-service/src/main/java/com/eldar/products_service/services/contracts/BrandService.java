package com.eldar.products_service.services.contracts;

import com.eldar.products_service.dtos.requests.BrandRequestDTO;
import com.eldar.products_service.dtos.responses.BrandResponseDTO;
import com.eldar.products_service.models.Brand;
import com.eldar.products_service.models.Category;
import jakarta.validation.Valid;

import java.util.List;

public interface BrandService {

    Brand getBrandById(Long id);

    BrandResponseDTO getById(Long id);

    List<BrandResponseDTO> getAll();

    void delete(Long id);

    void update(Long id, BrandRequestDTO brandRequestDTO);

    void add(@Valid BrandRequestDTO brandRequestDTO);
}
