package com.eldar.sales_service.services.impl;

import com.eldar.sales_service.dtos.responses.DetailResponseDTO;
import com.eldar.sales_service.mappers.DetailMapper;
import com.eldar.sales_service.models.Detail;
import com.eldar.sales_service.models.Sale;
import com.eldar.sales_service.repositories.DetailRepository;
import com.eldar.sales_service.rest_service.ProductClient;
import com.eldar.sales_service.rest_service.dtos.responses.ProductResponseDTO;
import com.eldar.sales_service.services.contracts.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailService {

    private final DetailRepository detailRepository;

    private final DetailMapper detailMapper;

    private final ProductClient productClient;

    /**
     *
     *  buscar a productos,
     * */
    @Override
    public List<DetailResponseDTO> getDetailsBySale(Sale sale,String authHeader) {
        List<Detail> details = detailRepository.findDetailsBySaleId(sale.getId());
        return details.stream()
                .map(detail -> mapToDetailResponseDTO(detail, authHeader))
                .collect(Collectors.toList());
    }

    private DetailResponseDTO mapToDetailResponseDTO(Detail detail, String authHeader) {
        ProductResponseDTO product = productClient.getById(detail.getProduct_id(), authHeader);
        return DetailResponseDTO.builder()
                .productName(product.getName())
                .quantity(detail.getQuantity())
                .brand(product.getBrand().getName())
                .productPrice(product.getPrice())
                .subtotal(detail.getSubtotal())
                .totalAmount(detail.getTotalAmount())
                .discountPercentage(detail.getDiscountPercentage())
                .build();
    }


}
