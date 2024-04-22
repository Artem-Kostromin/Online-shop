package ru.kostromin.onlineshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kostromin.onlineshop.dto.ProductRequest;
import ru.kostromin.onlineshop.dto.ProductResponse;
import ru.kostromin.onlineshop.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public String healthCheck() {
        return "Products page";
    }

    @PostMapping
    public void saveProduct(@RequestBody ProductRequest productRequest) {
        productService.saveProduct(productRequest);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/remove")
    public String removeProductByName(@RequestParam String name) {
        return productService.removeProductByName(name);
    }

}
