package com.eldar.products_service.services.impl;

import com.eldar.products_service.dtos.requests.ProductRequestDTO;
import com.eldar.products_service.dtos.responses.ProductResponseDTO;
import com.eldar.products_service.exceptions.customs.BadRequestException;
import com.eldar.products_service.exceptions.customs.NotFoundException;
import com.eldar.products_service.mappers.ProductMapper;
import com.eldar.products_service.models.Product;
import com.eldar.products_service.repositories.ProductRepository;
import com.eldar.products_service.services.contracts.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public List<ProductResponseDTO> getAll() {
        System.out.println(productRepository.findAll());
        return productRepository.findAll().stream().map(
                productMapper::toResponseDTO
        ).collect(Collectors.toList());
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

    @Override
    public void sell(Long id, int quantity) {
        Product product = productRepository.findById(id).orElseThrow(()->new NotFoundException("Product not found with id: " + id));
        if(quantity > product.getStock()) {
            throw new BadRequestException("The quantity of the product " + product.getName() + " is not sufficient");
        }
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    @Override
    public void revertStock(Long id, int quantity) {
        Product product = productRepository.findById(id).orElseThrow(()->new NotFoundException("Product not found with id: " + id));
        product.setStock(product.getStock() + quantity);
        productRepository.save(product);
    }

}
