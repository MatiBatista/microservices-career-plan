package com.eldar.sales_service.repositories;

import com.eldar.sales_service.models.Detail;
import com.eldar.sales_service.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {

    public List<Detail> findDetailsBySaleId(Long saleId);
}
