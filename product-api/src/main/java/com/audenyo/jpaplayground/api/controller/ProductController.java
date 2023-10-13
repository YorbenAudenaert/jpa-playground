package com.audenyo.jpaplayground.api.controller;

import com.audenyo.jpaplayground.api.domain.CreateProductDto;
import com.audenyo.jpaplayground.api.domain.GetProductDto;
import com.audenyo.jpaplayground.api.domain.ProductAttributeDto;
import com.audenyo.jpaplayground.api.domain.UpdateProductDto;
import com.audenyo.jpaplayground.product.domain.Product;
import com.audenyo.jpaplayground.product.service.CreateProductService;
import com.audenyo.jpaplayground.product.service.GetProductService;
import com.audenyo.jpaplayground.product.service.UpdateProductService;
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
                        .map(attr -> new ProductAttributeDto(attr.getId(), attr.getDescription(), attr.getProductAttributeValue().getValue()))
                        .toList())).toList();
    }

    @GetMapping("/{productId}")
    public GetProductDto getProduct(@PathVariable String productId) {
        Product product = getProductService.getProduct(productId);
        return new GetProductDto(product.getId(),
                product.getDescription(),
                product.getPrice(),
                product.getProductAttributes().stream()
                        .map(attr -> new ProductAttributeDto(attr.getId(), attr.getDescription(), attr.getProductAttributeValue().getValue()))
                        .toList());
    }

    @PutMapping
    public String createProduct(@RequestBody CreateProductDto createProductDto) {
        return createProductService.createProduct(createProductDto.toCommand());
    }

    @PostMapping("/{productId}")
    public UpdateProductDto updateProductPrice(@PathVariable String productId, @RequestParam("price") double price) {
        Product product = updateProductService.updateProductPrice(productId, price);
        return new UpdateProductDto(
                product.getId(),
                product.getDescription(),
                product.getPrice(),
                product.getProductAttributes().stream()
                        .map(attr -> new ProductAttributeDto(attr.getId(), attr.getDescription(), attr.getProductAttributeValue().getValue()))
                        .toList());
    }
}
