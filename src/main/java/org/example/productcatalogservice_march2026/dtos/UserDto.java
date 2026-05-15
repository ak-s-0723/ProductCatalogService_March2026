package org.example.productcatalogservice_march2026.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String name;
    private String emailId;
   // private List<String> roles;
}
