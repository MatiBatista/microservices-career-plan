package com.eldar.products_service.services.impl;

import com.eldar.products_service.dtos.requests.ProductRequestDTO;
import com.eldar.products_service.dtos.responses.ProductResponseDTO;
import com.eldar.products_service.exceptions.customs.NotFoundException;
import com.eldar.products_service.mappers.ProductMapper;
import com.eldar.products_service.models.Category;
import com.eldar.products_service.models.Product;
import com.eldar.products_service.repositories.CategoryRepository;
import com.eldar.products_service.repositories.ProductRepository;
import com.eldar.products_service.services.contracts.BrandService;
import com.eldar.products_service.services.contracts.CategoryService;
import com.eldar.products_service.services.contracts.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll().stream().map(productMapper::toResponseDTO).toList();
    }

    @Override
    public ProductResponseDTO getById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toResponseDTO)
                .orElseThrow(()->new NotFoundException("Product not found with id: " + id));
    }

    @Override
    @Transactional
    public void add(ProductRequestDTO productRequestDTO) {
        productRepository.save(productMapper.toEntity(productRequestDTO));
    }

    @Override
    public void update(Long id, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(id).orElseThrow(()->new NotFoundException("Product not found with id: " + id));
        productMapper.updateEntity(product, productRequestDTO);
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
