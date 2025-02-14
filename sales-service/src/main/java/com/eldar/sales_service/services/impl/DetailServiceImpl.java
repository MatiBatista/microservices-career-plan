package com.eldar.sales_service.services.impl;

import com.eldar.sales_service.dtos.responses.DetailResponseDTO;
import com.eldar.sales_service.mappers.DetailMapper;
import com.eldar.sales_service.models.Detail;
import com.eldar.sales_service.models.Sale;
import com.eldar.sales_service.repositories.DetailRepository;
import com.eldar.sales_service.services.contracts.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailService {

    private final DetailRepository detailRepository;

    private final DetailMapper detailMapper;

    @Override
    public List<DetailResponseDTO> getDetailsBySale(Sale sale) {
        List<Detail> detailListBySale= detailRepository.findAllBySale(sale).stream().map();
        detailListBySale.forEach(detail ->
            {

            }
        );
        return detailRepository.findAllBySale(sale);
    }

}
