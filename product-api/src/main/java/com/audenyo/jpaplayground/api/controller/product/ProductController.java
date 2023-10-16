package com.audenyo.jpaplayground.api.controller.product;

import com.audenyo.jpaplayground.api.domain.product.*;
import com.audenyo.jpaplayground.product.domain.Product;
import com.audenyo.jpaplayground.product.service.CreateProductService;
import com.audenyo.jpaplayground.product.service.GetProductService;
import com.audenyo.jpaplayground.product.service.UpdateProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final GetProductService getProductService;
    private final CreateProductService createProductService;
    private final UpdateProductService updateProductService;

    public ProductController(GetProductService getProductService, CreateProductService createProductService, UpdateProductService updateProductService) {
        this.getProductService = getProductService;
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
    }


    @GetMapping
    public List<GetProductDto> getAllProducts() {
        List<Product> products = getProductService.getAllProducts();
        return products.stream().map(p -> new GetProductDto(p.getId(),
                p.getDescription(),
                p.getPrice(),
                p.getProductAttributes().stream()
                        .map(attr -> new ProductAttributeDto(attr.getId(), attr.getDescription(), attr.getValue()))
                        .toList(), p.getCategory() != null ? p.getCategory().getDescription() : null)).toList();
    }

    @GetMapping("/{productId}")
    public GetProductDto getProduct(@PathVariable String productId) {
        Product product = getProductService.getProduct(productId);
        return new GetProductDto(product.getId(),
                product.getDescription(),
                product.getPrice(),
                product.getProductAttributes().stream()
                        .map(attr -> new ProductAttributeDto(attr.getId(), attr.getDescription(), attr.getValue()))
                        .toList(), product.getCategory() != null ? product.getCategory().getDescription() : null);
    }

    @PutMapping
    public String createProduct(@RequestBody CreateProductDto createProductDto) {
        return createProductService.createProduct(createProductDto.toCommand());
    }

    @PutMapping("/category")
    public String createProductForCategory(
            @RequestBody CreateProductWithCategoryDto createProductDto) {
        return createProductService.createProductWithCategory(createProductDto.toCommand());
    }

    @PostMapping("/{productId}")
    public UpdateProductDto updateProductPrice(@PathVariable String productId, @RequestParam("price") double price) {
        Product product = updateProductService.updateProductPrice(productId, price);
        return new UpdateProductDto(
                product.getId(),
                product.getDescription(),
                product.getPrice(),
                product.getProductAttributes().stream()
                        .map(attr -> new ProductAttributeDto(attr.getId(), attr.getDescription(), attr.getValue()))
                        .toList());
    }

    @PostMapping("/{productId}/{attributeId}")
    public UpdateProductDto updateProductAttribute(
            @PathVariable String productId,
            @PathVariable String attributeId,
            @RequestParam String value) {
        Product product = updateProductService.updateProductAttributeValue(productId, attributeId, value);
        return new UpdateProductDto(product.getId(),
                product.getDescription(),
                product.getPrice(),
                product.getProductAttributes().stream()
                        .map(attr -> new ProductAttributeDto(attr.getId(), attr.getDescription(), attr.getValue()))
                        .toList());
    }

    @PutMapping("/{productId}/category")
    public ResponseEntity<Product> updateProductCategory(@PathVariable String productId, @RequestParam String categoryId) {
        Product updatedProduct = updateProductService.updateProductCategory(productId, categoryId);
        return ResponseEntity.ok(updatedProduct);
    }
}
