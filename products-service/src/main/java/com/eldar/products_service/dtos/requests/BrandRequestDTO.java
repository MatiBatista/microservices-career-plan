package com.eldar.products_service.dtos.requests;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BrandRequestDTO {

    private String name;

    private List<Long> categories_ids;

}
