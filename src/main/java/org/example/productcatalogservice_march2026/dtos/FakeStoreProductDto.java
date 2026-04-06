package org.example.productcatalogservice_march2026.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDto {
    Long id;
    String title;
    String description;
    String category;
    String image;
    Double price;
}
