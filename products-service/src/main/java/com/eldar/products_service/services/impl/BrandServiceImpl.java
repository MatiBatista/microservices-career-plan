package com.eldar.products_service.services.impl;

import com.eldar.products_service.dtos.requests.BrandRequestDTO;
import com.eldar.products_service.dtos.responses.BrandResponseDTO;
import com.eldar.products_service.exceptions.customs.NotFoundException;
import com.eldar.products_service.mappers.BrandMapper;
import com.eldar.products_service.models.Brand;
import com.eldar.products_service.repositories.BrandRepository;
import com.eldar.products_service.services.contracts.BrandService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    private final BrandMapper brandMapper;

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
    public void add(BrandRequestDTO brandRequestDTO) {
        brandRepository.save(brandMapper.toEntity(brandRequestDTO));
    }

    @Override
    public void update(Long id, BrandRequestDTO brandRequestDTO) {

    }
    @Override
    public void delete(Long id) {

    }


}
