package com.assj5.thien.assj5.service;

import com.assj5.thien.assj5.model.BillDetail;
import com.assj5.thien.assj5.model.Product;

import java.util.List;

public interface ProductSevice {
    List<Product> findAll();

    Product findByID(Long Id);

    void save(Product product);

    void update(Product product);

    void delete(Long Id);
}
