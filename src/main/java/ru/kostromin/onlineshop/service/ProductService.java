package ru.kostromin.onlineshop.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kostromin.onlineshop.dto.ProductRequest;
import ru.kostromin.onlineshop.dto.ProductResponse;
import ru.kostromin.onlineshop.model.Product;
import ru.kostromin.onlineshop.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void saveProduct(ProductRequest productRequest) {
        productRepository.save(
                Product.builder()
                        .name(productRequest.getName())
                        .description(productRequest.getDescription())
                        .price(productRequest.getPrice())
                        .build()
        );
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> allProducts = (List<Product>) productRepository.findAll();
        return allProducts.stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .build())
                .toList();
    }

    @Transactional
    public String removeProductByName(String name) {
        List<Product> productsByName = productRepository.findByName(name);
        List<Long> removedProductsId = productsByName.stream().map(Product::getId).toList();
        productRepository.removeAllByName(name);

        return String.format("Products with ids %s have been remove.", removedProductsId);
    }
}
