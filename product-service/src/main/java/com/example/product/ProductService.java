package com.example.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductEntity> getAll() {
        return repository.findAll();
    }

    public ProductEntity save(ProductEntity entity) {
        return repository.save(entity);
    }
}
