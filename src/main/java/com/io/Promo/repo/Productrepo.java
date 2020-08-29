package com.io.Promo.repo;

import com.io.Promo.model.Order;
import com.io.Promo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Productrepo extends JpaRepository<Product,Long> {
    Product findByName(String name);
}
