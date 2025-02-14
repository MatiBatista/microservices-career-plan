package com.eldar.sales_service.rest_service.dtos.requests;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryRequestDTO {

    private String name;

    private Long parentCategoryId;
}
