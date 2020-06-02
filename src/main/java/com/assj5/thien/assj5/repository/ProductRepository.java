package com.assj5.thien.assj5.repository;

import com.assj5.thien.assj5.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
