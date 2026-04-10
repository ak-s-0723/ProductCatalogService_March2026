package org.example.productcatalogservice_march2026.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Category extends BaseModel {
    private String title;
    private String description;
    @OneToMany(mappedBy = "category")                   //1:M
    private List<Product> products;
}


//1 : M
//M : 1
//
//M : M