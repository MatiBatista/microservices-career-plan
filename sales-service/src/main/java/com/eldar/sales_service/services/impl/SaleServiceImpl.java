package com.eldar.sales_service.services.impl;

import com.eldar.sales_service.dtos.requests.DetailRequestDTO;
import com.eldar.sales_service.dtos.requests.SaleRequestDTO;

import com.eldar.sales_service.dtos.responses.DetailResponseDTO;
import com.eldar.sales_service.dtos.responses.SaleResponseDTO;
import com.eldar.sales_service.dtos.responses.SaleResponseDTOAll;
import com.eldar.sales_service.exceptions.customs.BadRequestException;
import com.eldar.sales_service.mappers.SaleMapper;
import com.eldar.sales_service.models.Detail;
import com.eldar.sales_service.models.Sale;
import com.eldar.sales_service.repositories.DetailRepository;
import com.eldar.sales_service.repositories.SaleRepository;
import com.eldar.sales_service.rest_service.PersonClient;
import com.eldar.sales_service.rest_service.ProductClient;
import com.eldar.sales_service.rest_service.dtos.responses.CustomerResponseDTO;
import com.eldar.sales_service.rest_service.dtos.responses.ProductResponseDTO;
import com.eldar.sales_service.services.contracts.DetailService;
import com.eldar.sales_service.services.contracts.SaleService;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final ProductClient productClient;
    private final PersonClient personClient;
    private final DetailRepository detailRepository;
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final DetailService detailService;


    @Override
    @Transactional
    public void add(SaleRequestDTO saleRequestDTO, String authHeader) {
        validateCustomerAndEmployee(saleRequestDTO, authHeader);
        List<Detail> details = null;
        try {
            details = processDetails(saleRequestDTO.getDetails_products(), authHeader);
            Sale sale = createSale(saleRequestDTO, details);
            saveSaleAndDetails(sale, details);
        }catch (Exception e) {
            if(details != null) {
                revert(details, authHeader);
            }
            throw e;
        }
    }

    @Override
    public List<SaleResponseDTOAll> getAll( String authHeader) {
        return  saleRepository.findAll().stream().map( s ->
                {
                    CustomerResponseDTO customer= personClient.getCustomerById(s.getCustomer_id(),authHeader);
                    return saleMapper.toResponseDTOAll(s,customer);
                }
        ).toList();
    }


    @Override
    public SaleResponseDTO getById(Long id) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new BadRequestException("Sale not found"));
        List<DetailResponseDTO> detailList=detailService.getDetailsBySale(sale);

        return null;
    }

    private void revert(List<Detail> details, String authHeader) {
        details.forEach(detail -> productClient.revertStock(detail.getProduct_id(),detail.getQuantity(),authHeader));
    }

    private void validateCustomerAndEmployee(SaleRequestDTO saleRequestDTO, String authHeader) {
        personClient.getCustomerById(saleRequestDTO.getCustomer_id(), authHeader);
        personClient.getEmployeeById(saleRequestDTO.getEmployee_id(), authHeader);
    }


    private List<Detail> processDetails(List<DetailRequestDTO> detailRequestDTOList, String authHeader) {
        List<Detail> details = new ArrayList<>();
        try{
            detailRequestDTOList.forEach(d -> {
                ProductResponseDTO productResponseDTO=productClient.getById(d.getProduct_id(),authHeader);
                productClient.sell(d.getProduct_id(), d.getQuantity(),authHeader);
                details.add(createDetail(d, productResponseDTO.getPrice()));
            });
        }catch (FeignException e) {
            revert(details,authHeader);
            throw e;
        }
        return details;
    }

    private Detail createDetail(DetailRequestDTO d, BigDecimal price) {
        BigDecimal subtotal = BigDecimal.valueOf(d.getQuantity()).multiply(price);
        BigDecimal discountAmount = subtotal.multiply(BigDecimal.valueOf(d.getDiscountPercentage()).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
        BigDecimal totalAmount = subtotal.subtract(discountAmount);
        return Detail.builder()
                .subtotal(subtotal)
                .quantity(d.getQuantity())
                .discountPercentage(d.getDiscountPercentage())
                .totalAmount(totalAmount)
                .product_id(d.getProduct_id())
                .build();
    }

    private Sale createSale(SaleRequestDTO saleRequestDTO, List<Detail> details) {
        BigDecimal totalDetails = details.stream().map(Detail::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal discountAmount = totalDetails.multiply(BigDecimal.valueOf(saleRequestDTO.getDiscountPercentage()).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
        BigDecimal totalAmount = totalDetails.subtract(discountAmount);
        return Sale.builder()
                .date(saleRequestDTO.getDate())
                .customer_id(saleRequestDTO.getCustomer_id())
                .employee_id(saleRequestDTO.getEmployee_id())
                .discountPercentage(saleRequestDTO.getDiscountPercentage())
                .subtotal(totalDetails)
                .discountAmount(discountAmount)
                .totalAmount(totalAmount)
                .build();
    }

    private void saveSaleAndDetails(Sale sale, List<Detail> details) {
        saleRepository.save(sale);
        details.forEach(d -> {
            d.setSale(sale);
            detailRepository.save(d);
        });
    }
}