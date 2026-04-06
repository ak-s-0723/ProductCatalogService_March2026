package org.example.productcatalogservice_march2026.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDto {
    private Long id;
    private String title;
    private String description;
}
