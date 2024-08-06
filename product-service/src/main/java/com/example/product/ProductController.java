package com.example.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<ProductEntity> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ProductEntity create(@RequestBody ProductEntity entity) {
        return service.save(entity);
    }
}
