package org.example.productcatalogservice_march2026.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Category extends BaseModel {
    private String title;
    private String description;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)  //1:M
    // @BatchSize(size = 4)
    private List<Product> products = new ArrayList<>();
}

//5 = 2  +  2  +  1
//3 queries + 1 = 4

//1 : M
//M : 1
//
//M : M