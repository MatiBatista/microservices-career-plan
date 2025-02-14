package com.eldar.products_service.repositories;

import com.eldar.products_service.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    boolean existsByName(String name);
}
