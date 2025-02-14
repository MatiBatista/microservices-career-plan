package com.eldar.sales_service.services.contracts;

import com.eldar.sales_service.dtos.requests.SaleRequestDTO;
import com.eldar.sales_service.dtos.responses.SaleResponseDTO;
import com.eldar.sales_service.dtos.responses.SaleResponseDTOAll;

import java.util.List;

public interface SaleService {

    void add(SaleRequestDTO saleRequestDTO, String authHeader);

    List<SaleResponseDTOAll> getAll(String authHeader);

    SaleResponseDTO getById(Long id);
}
