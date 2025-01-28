package com.eldar.products_service.services.contracts;


import com.eldar.products_service.models.Category;

public interface CategoryService {

    Category getById(Long id);
}
