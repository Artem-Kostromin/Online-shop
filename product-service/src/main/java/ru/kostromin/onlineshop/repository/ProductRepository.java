package ru.kostromin.onlineshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kostromin.onlineshop.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByName(String name);

    void removeAllByName(String name);
}
