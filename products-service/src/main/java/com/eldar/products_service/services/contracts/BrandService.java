package com.eldar.products_service.services.contracts;

import com.eldar.products_service.models.Brand;
import com.eldar.products_service.models.Category;

public interface BrandService {

    Brand getById(Long id);
}
