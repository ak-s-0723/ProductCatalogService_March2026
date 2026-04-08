package org.example.productcatalogservice_march2026.services;

import org.example.productcatalogservice_march2026.dtos.FakeStoreProductDto;
import org.example.productcatalogservice_march2026.models.Category;
import org.example.productcatalogservice_march2026.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder builder;

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = builder.build();
//        FakeStoreProductDto fakeStoreProductDto =
//                restTemplate.getForObject("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class,
//                        id);

//        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
//                restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class,
//                        id);

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
               requestForEntity(HttpMethod.GET,"https://fakestoreapi.com/products/{id}",
                       null,
                       FakeStoreProductDto.class, id);

        if (validateFakeStoreResponse(fakeStoreProductDtoResponseEntity)) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }

        return null;
    }

    //ToDo : By Students
    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    //ToDo : By Students
    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Product input, Long id) {
        FakeStoreProductDto inputFakeStoreProductDto = from(input);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                requestForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{id}",
                        inputFakeStoreProductDto, FakeStoreProductDto.class, id);

       if (validateFakeStoreResponse(fakeStoreProductDtoResponseEntity)) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }

        return null;
    }


    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = builder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private Boolean validateFakeStoreResponse(ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity) {
        if (fakeStoreProductDtoResponseEntity.hasBody() &&
                fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            return true;
        }

        return false;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return  product;
    }

    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getTitle());
        }
        return fakeStoreProductDto;
    }
}
