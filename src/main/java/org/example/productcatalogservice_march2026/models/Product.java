package org.example.productcatalogservice_march2026.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product extends BaseModel {
    private String title;
    private String imageUrl;
    private String description;
    private Double price;
    @ManyToOne(cascade = CascadeType.ALL)                  //M:1
    private Category category;
    //private String sellerName;
    private Boolean isSaleSpecific;  //Special Field
}


//1                 1
//Product        Category
//M                 1
//
//
//M : 1