package com.SalesCompaignManagement.SalesCompaign.Repository;

import com.SalesCompaignManagement.SalesCompaign.Models_Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
