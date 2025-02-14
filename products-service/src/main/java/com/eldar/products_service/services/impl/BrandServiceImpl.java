package com.eldar.products_service.services.impl;

import com.eldar.products_service.dtos.requests.BrandRequestDTO;
import com.eldar.products_service.dtos.responses.BrandResponseDTO;
import com.eldar.products_service.exceptions.customs.BadRequestException;
import com.eldar.products_service.exceptions.customs.NotFoundException;
import com.eldar.products_service.mappers.BrandMapper;
import com.eldar.products_service.models.Brand;
import com.eldar.products_service.models.BrandForCategory;
import com.eldar.products_service.models.Category;
import com.eldar.products_service.repositories.BrandForCategoryRepository;
import com.eldar.products_service.repositories.BrandRepository;
import com.eldar.products_service.services.contracts.BrandService;


import com.eldar.products_service.services.contracts.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    private final CategoryService categoryService;

    private final BrandMapper brandMapper;

    private final BrandForCategoryRepository brandForCategoryRepository;

    @Override
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Brand not found with id: " + id));
    }


    @Override
    public List<BrandResponseDTO> getAll() {
        return brandRepository.findAll().stream().map(brandMapper::toResponseDTO).toList();
    }

    @Override
    public BrandResponseDTO getById(Long id) {
        return brandRepository.findById(id)
                .map(brandMapper::toResponseDTO)
                .orElseThrow(()->new NotFoundException("Brand not found with id: " + id));
    }


    @Override
    @Transactional
    public void add(BrandRequestDTO brandRequestDTO) {
        if(brandRepository.existsByName(brandRequestDTO.getName())){
            throw new BadRequestException("Brand with name "+brandRequestDTO.getName()+" already exists");
        }
        Brand brandSaved=brandRepository.save(brandMapper.toEntity(brandRequestDTO));
        brandRequestDTO.getCategories_ids().forEach(
                categoryId -> {
                    BrandForCategory brandForCategory= BrandForCategory.builder()
                            .category(categoryService.getCategoryById(categoryId))
                            .brand(brandSaved)
                            .build();
                    brandForCategoryRepository.save(brandForCategory);
                }
        );
    }

    @Override
    public void update(Long id, BrandRequestDTO brandRequestDTO) {

    }
    @Override
    public void delete(Long id) {

    }


}
