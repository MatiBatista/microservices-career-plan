package com.eldar.sales_service.services.impl;

import com.eldar.sales_service.dtos.requests.DetailRequestDTO;
import com.eldar.sales_service.dtos.requests.SaleRequestDTO;

import com.eldar.sales_service.dtos.responses.DetailResponseDTO;
import com.eldar.sales_service.dtos.responses.SaleResponseDTO;
import com.eldar.sales_service.dtos.responses.SaleResponseDTOAll;
import com.eldar.sales_service.exceptions.customs.BadRequestException;
import com.eldar.sales_service.exceptions.customs.NotFoundException;
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
import org.springframework.http.ResponseEntity;
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

    /*
    * no mostrar detalle
    * **/
    @Override
    public List<SaleResponseDTOAll> getAll( String authHeader) {
        return  saleRepository.findAll().stream().map( s ->
                {
                    CustomerResponseDTO customer= personClient.getCustomerById(s.getCustomer_id(),authHeader);
                    return saleMapper.toResponseDTOAll(s,customer);
                }
        ).toList();
    }


    /**
     * meter detalle
     * */
    @Override
    public SaleResponseDTO getById(Long id,String authHeader) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new BadRequestException("Sale not found"));
        List<DetailResponseDTO> detailList = detailService.getDetailsBySale(sale, authHeader);
        CustomerResponseDTO customerResponseDTO = this.personClient.getCustomerById(sale.getCustomer_id(), authHeader);


        return SaleResponseDTO.builder()
                .details(detailList)
                .customer(CustomerResponseDtoToCustomResponse(customerResponseDTO))
                .id(sale.getId())
                .date(sale.getDate())
                .discountAmount(sale.getDiscountAmount())
                .discountPercentage(sale.getDiscountPercentage())
                .totalAmount(sale.getTotalAmount())
                .build();
    }

    /**
     * Eliminacion sin devolucion
     * */
    @Override
    @Transactional
    public void delete(Long id,String authHeader) {
        //verificamos la existencia de la venta
        if(!this.saleRepository.existsById(id))
            throw new NotFoundException("Sale not found with id: "+id);

        //verificamos la existencia de detalles, si existen las eliminamos
        if(this.detailRepository.existsDetailsBySaleId(id))
            this.detailRepository.deleteDetailsBySaleId(id);


        //Eliminacion de la venta
        this.saleRepository.deleteById(id);
    }

    /**Eliminacion con devolucion, preguntar eficiencia*/
    @Override
    @Transactional
    public void deleteWithRevert(Long id,String authHeader){
        if(!this.saleRepository.existsById(id))
            throw new NotFoundException("Sale not found with id: "+id);

        //verificamos la existencia de detalles, si existen, reponemos el stock y eliminamos la venta
        if(this.detailRepository.existsDetailsBySaleId(id)) {
            revert(detailRepository.findDetailsBySaleId(id),authHeader);
            this.detailRepository.deleteDetailsBySaleId(id);
        }

        //Eliminacion de la venta
        this.saleRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, SaleRequestDTO saleRequestDTO,String authHeader){
        if(!this.saleRepository.existsById(id))
            throw new NotFoundException("Sale not found with id: "+id);

        validateCustomerAndEmployee(saleRequestDTO, authHeader);
        List<Detail> details = null;
        try {
            this.detailRepository.deleteDetailsBySaleId(id);
            details = processDetails(saleRequestDTO.getDetails_products(), authHeader);
            Sale sale = createSale(saleRequestDTO, details);
            sale.setId(id);
            saveSaleAndDetails(sale, details);
        }catch (Exception e) {
            if (details != null) {
                revert(details, authHeader);
            }
            throw e;
        }


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

    private SaleResponseDTO.CustomerResponse CustomerResponseDtoToCustomResponse(CustomerResponseDTO customerResponseDTO){
        return SaleResponseDTO.CustomerResponse.builder()
                .firstname(customerResponseDTO.getFirstname())
                .lastname(customerResponseDTO.getLastname())
                .dni(customerResponseDTO.getDni())
                .email(customerResponseDTO.getEmail())
                .address(customerResponseDTO.getAddress()).build();
    }
}