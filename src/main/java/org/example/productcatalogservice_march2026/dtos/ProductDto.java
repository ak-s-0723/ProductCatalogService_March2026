package org.example.productcatalogservice_march2026.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice_march2026.models.Category;

@Setter
@Getter
public class ProductDto {
    private Long id;
    private String title;
    private String imageUrl;
    private String description;
    private Double price;
    private CategoryDto category;

}
