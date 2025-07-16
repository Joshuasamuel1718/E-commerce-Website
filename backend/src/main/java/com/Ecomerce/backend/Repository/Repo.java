package com.Ecomerce.backend.Repository;

import com.Ecomerce.backend.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Repo extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            " OR LOWER(p.brand)" +
            " LIKE LOWER(CONCAT('%', :keyword, '%')) OR" + " LOWER(p.category)" +
            " LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.description)" +
            " LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchByProduct(@Param("keyword") String keyword);


}


