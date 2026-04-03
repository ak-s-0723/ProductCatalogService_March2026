package org.example.productcatalogservice_march2026.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends BaseModel {
    private String title;
    private String imageUrl;
    private String description;
    private Double price;
    private Category category;
    //private String sellerName;
    private Boolean isSaleSpecific;  //Special Field
}
