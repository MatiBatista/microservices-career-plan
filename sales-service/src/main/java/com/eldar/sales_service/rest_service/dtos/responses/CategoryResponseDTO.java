package com.eldar.sales_service.rest_service.dtos.responses;


import lombok.Data;

@Data
public class CategoryResponseDTO {

    private Long id;

    private String name;

   private String parentCategory;
}
