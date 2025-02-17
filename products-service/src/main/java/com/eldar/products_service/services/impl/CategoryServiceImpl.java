package com.eldar.products_service.services.impl;

import com.eldar.products_service.dtos.requests.CategoryRequestDTO;
import com.eldar.products_service.dtos.responses.CategoryResponseDTO;
import com.eldar.products_service.exceptions.customs.NotFoundException;
import com.eldar.products_service.mappers.CategoryMapper;
import com.eldar.products_service.models.Category;
import com.eldar.products_service.models.Product;
import com.eldar.products_service.repositories.CategoryRepository;
import com.eldar.products_service.services.contracts.CategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Category not found with id: " + id));
    }

    @Override
    public List<CategoryResponseDTO> getAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::toResponseDTO).toList();
    }

    @Override
    public CategoryResponseDTO getById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toResponseDTO)
                .orElseThrow(()->new NotFoundException("Category not found with id: " + id));
    }

    @Override
    @Transactional
    public void add(CategoryRequestDTO categoryRequestDTO) {
        categoryMapper.toEntity(categoryRequestDTO);
        categoryRepository.save(categoryMapper.toEntity(categoryRequestDTO));
    }

    @Override
    public void update(Long id, CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new NotFoundException("Category not found with id: " + id));
        categoryMapper.updateEntity(category, categoryRequestDTO);
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
