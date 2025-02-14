package com.eldar.sales_service.mappers;

import com.eldar.sales_service.dtos.responses.DetailResponseDTO;
import com.eldar.sales_service.dtos.responses.SaleResponseDTO;
import com.eldar.sales_service.models.Detail;
import com.eldar.sales_service.models.Sale;
import com.eldar.sales_service.rest_service.dtos.responses.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper(componentModel = "spring")
public abstract class DetailMapper {

    @Mapping(target = "productName", source = "productResponseDTO.name")
    @Mapping(target = "brand", source = "productResponseDTO.category.name", defaultExpression = "java(null)")
    @Mapping(target = "productPrice",source = "detail", qualifiedByName = "calculateProductPrice")
    public abstract DetailResponseDTO toResponseDTO(Detail detail, ProductResponseDTO productResponseDTO);


    @Named("calculateProductPrice")
    public BigDecimal calculateProductPrice(Detail detail){
        return detail.getSubtotal().divide(BigDecimal.valueOf(detail.getQuantity()),2, RoundingMode.HALF_UP);
    }
}
