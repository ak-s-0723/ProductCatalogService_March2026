package org.example.productcatalogservice_march2026.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatalogservice_march2026.dtos.ProductDto;
import org.example.productcatalogservice_march2026.models.Product;
import org.example.productcatalogservice_march2026.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Test_GetAllProducts_RunSuccessfully() throws Exception {

      //    GET "/products"
        //Arrange

        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Iphone");
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        //productList.add(product2);
        when(productService.getAllProducts()).thenReturn(productList);

        //For Testing
        String expectedResponse1 =
                objectMapper.writeValueAsString(productList);

        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("Iphone");
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto);
        //productDtos.add(productDto2);

        String expectedResponse =
                objectMapper.writeValueAsString(productDtos);

        System.out.println(expectedResponse);

      mockMvc.perform(get("/products"))     //Act
              .andExpect(status().isOk())             //Assert for status code
              .andExpect(content().string(expectedResponse)); //Assert for body


    }


}
