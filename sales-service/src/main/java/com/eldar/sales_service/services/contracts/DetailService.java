package com.eldar.sales_service.services.contracts;

import com.eldar.sales_service.dtos.responses.DetailResponseDTO;
import com.eldar.sales_service.models.Detail;
import com.eldar.sales_service.models.Sale;

import java.util.List;

public interface DetailService {

    List<DetailResponseDTO> getDetailsBySale(Sale sale);
}
