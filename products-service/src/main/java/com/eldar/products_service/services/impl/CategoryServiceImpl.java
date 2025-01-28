package com.eldar.products_service.services.impl;

import com.eldar.products_service.exceptions.customs.NotFoundException;
import com.eldar.products_service.models.Category;
import com.eldar.products_service.repositories.CategoryRepository;
import com.eldar.products_service.services.contracts.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Product not found with id: " + id));
    }
}
