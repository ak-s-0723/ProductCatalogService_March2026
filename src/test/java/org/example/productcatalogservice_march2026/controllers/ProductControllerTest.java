package org.example.productcatalogservice_march2026.controllers;

import org.example.productcatalogservice_march2026.dtos.ProductDto;
import org.example.productcatalogservice_march2026.models.Product;
import org.example.productcatalogservice_march2026.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Test
    public void TestGetProductById_WithValidProductId_ReturnsProductSuccessfully() {
        //Arrange
        Long id = 2L;
        Product product = new Product();
        product.setId(id);
        product.setTitle("Iphone");
        when(productService.getProductById(id)).thenReturn(product);

        //Act
        ResponseEntity<ProductDto> productDtoResponseEntity =
                productController.getProductById(id);

        //Assert
        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody());
        assertEquals(HttpStatus.OK,productDtoResponseEntity.getStatusCode());
        assertEquals(id,productDtoResponseEntity.getBody().getId());
        assertEquals("Iphone",productDtoResponseEntity.getBody().getTitle());
    }

    @Test
    public void TestGetProductById_WithNegativeProductId_ResultsInIllegalArgumentException() {
        //Assert
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> productController.getProductById(-1L));

        assertEquals("INVALID INPUT",exception.getMessage());
    }

    @Test
    public void TestGetProductById_ThrowsRuntimeException_WhenProductServiceThrowsRuntimeException() {
        //Arrange
        Long id = 5L;
        when(productService.getProductById(id)).thenThrow(new RuntimeException());

        //Act and Assert
        assertThrows(RuntimeException.class,
                () -> productController.getProductById(id));
    }
}