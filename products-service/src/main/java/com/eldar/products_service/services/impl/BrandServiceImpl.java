package com.eldar.products_service.services.impl;

import com.eldar.products_service.exceptions.customs.NotFoundException;
import com.eldar.products_service.models.Brand;
import com.eldar.products_service.repositories.BrandRepository;
import com.eldar.products_service.services.contracts.BrandService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public Brand getById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Brand not found with id: " + id));
    }


}
