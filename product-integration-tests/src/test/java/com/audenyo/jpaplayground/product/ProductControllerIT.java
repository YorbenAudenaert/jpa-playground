package com.audenyo.jpaplayground.product;

import com.audenyo.jpaplayground.ProductApplication;
import com.audenyo.jpaplayground.api.domain.product.CreateProductAttributeDto;
import com.audenyo.jpaplayground.api.domain.product.CreateProductDto;
import com.audenyo.jpaplayground.product.persistence.domain.ProductAttributeEntity;
import com.audenyo.jpaplayground.product.persistence.domain.ProductEntity;
import com.audenyo.jpaplayground.product.persistence.domain.category.CategoryEntity;
import com.audenyo.jpaplayground.product.persistence.repository.CategoryJpaRepository;
import com.audenyo.jpaplayground.product.persistence.repository.ProductJpaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ProductApplication.class)
@AutoConfigureMockMvc
class ProductControllerIT {

    private static final String PRODUCT_API_BASEURL = "/api/product";
    private static final String CATEGORY_API_BASEURL = "/api/category";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Autowired
    private CategoryJpaRepository categoryJpaRepository;


    @Test
    void getAllProducts() throws Exception {
        mockMvc.perform(get(PRODUCT_API_BASEURL))
                .andExpect(status().isOk());
    }

    @Test
    void createProductWithoutCategory() throws Exception {
        CreateProductDto createProductDto = new CreateProductDto(
                "TestProduct1",
                10.0,
                List.of(new CreateProductAttributeDto("color", "black")));

        mockMvc.perform(put(PRODUCT_API_BASEURL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeObjectAsString(createProductDto))
        ).andExpect(status().isOk());
    }

    @Test
    void updateProductPrice() throws Exception {
        double newPrice = 400.0;

        ProductEntity product = createProduct();
        ProductEntity save = productJpaRepository.save(product);

        mockMvc.perform(post(PRODUCT_API_BASEURL + "/" + save.getId() + "?price=" + newPrice))
                .andExpect(status().isOk());
    }

    @Test
    void updateAttributeValue() throws Exception {
        String newValue = "Yellow";
        ProductEntity product = createProduct();

        ProductEntity save = productJpaRepository.save(product);

        String attributeId = save.getProductAttributes().get(0).getId();

        mockMvc.perform(post(String.format("%s/%s/%s?value=%s", PRODUCT_API_BASEURL, save.getId(), attributeId, newValue)))
                .andExpect(status().isOk());
    }

    @Test
    void updateProductCategory() throws Exception {
        String newCategory = "Electronics";
        CategoryEntity category = new CategoryEntity();
        category.setDescription(newCategory);
        CategoryEntity savedCategory = categoryJpaRepository.save(category);
        ProductEntity product = createProduct();
        ProductEntity savedProduct = productJpaRepository.save(product);

        mockMvc.perform(put(String.format("%s/%s/category?categoryId=%s", PRODUCT_API_BASEURL, savedProduct.getId(), savedCategory.getId())))
                .andExpect(status().isOk());
    }


    @Test
    void updateProductCategoryAndUpdateCategoryDescription() throws Exception {
        String newCategory = "Electronics";
        CategoryEntity category = new CategoryEntity();
        category.setDescription(newCategory);
        CategoryEntity savedCategory = categoryJpaRepository.save(category);
        ProductEntity product = createProduct();
        ProductEntity savedProduct = productJpaRepository.save(product);

        mockMvc.perform(put(String.format("%s/%s/category?categoryId=%s", PRODUCT_API_BASEURL, savedProduct.getId(), savedCategory.getId())))
                .andExpect(status().isOk());


        mockMvc.perform(put(String.format("%s/%s?newDescription=%s", CATEGORY_API_BASEURL, savedCategory.getId(), "Hardware")));
    }


    private String writeObjectAsString(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(o);
    }


    private ProductEntity createProduct() {
        ProductAttributeEntity attribute = new ProductAttributeEntity();
        attribute.setDescription("Color");
        attribute.setValue("Black");
        ProductEntity product = new ProductEntity();
        product.setPrice(10.0);
        product.setCategory(null);
        product.setDescription("TestProduct");
        attribute.setProduct(product);
        List<ProductAttributeEntity> attributes = new ArrayList<>();
        attributes.add(attribute);
        product.setProductAttributes(attributes);
        return product;
    }


}