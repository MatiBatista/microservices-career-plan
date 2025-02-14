package com.eldar.products_service.repositories;

import com.eldar.products_service.models.BrandForCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandForCategoryRepository extends JpaRepository<BrandForCategory, Long> {

}
