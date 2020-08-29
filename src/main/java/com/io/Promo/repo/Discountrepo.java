package com.io.Promo.repo;

import com.io.Promo.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Discountrepo  extends JpaRepository<Discount,Long> {
    Discount findByName(String name);

}
