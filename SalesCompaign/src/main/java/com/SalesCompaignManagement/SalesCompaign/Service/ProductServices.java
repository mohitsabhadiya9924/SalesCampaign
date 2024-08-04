package com.SalesCompaignManagement.SalesCompaign.Service;

import com.SalesCompaignManagement.SalesCompaign.Models_Entities.CampaignDiscount;
import com.SalesCompaignManagement.SalesCompaign.Models_Entities.History;
import com.SalesCompaignManagement.SalesCompaign.Models_Entities.Product;
import com.SalesCompaignManagement.SalesCompaign.Repository.HistoryRepository;
import com.SalesCompaignManagement.SalesCompaign.Repository.ProductRepository;
import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private HistoryRepository historyRepository;

    // Add Data
    public ResponseEntity<?> addData(List<Product> products) {
        try {
            return new ResponseEntity<>(productRepository.saveAll(products), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Entry Failed", HttpStatus.BAD_REQUEST);
    }

    // Get All Data
    public ResponseEntity<?> getAllProducts(){
        try {
            return new ResponseEntity<>(productRepository.findAll(),HttpStatus.FOUND);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Find", HttpStatus.NOT_FOUND);
    }

    // Get Product By ID
    public ResponseEntity<?> getById(int id) {
        try {
            return new ResponseEntity<>(productRepository.findById(id),HttpStatus.FOUND);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }



    // Sorting
    public ResponseEntity<?> findByFieldSorting(String field) {
        try {
            return new ResponseEntity<>(productRepository.findAll(Sort.by(Sort.Direction.ASC,field)),HttpStatus.FOUND);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> findProductsWithPagination(int offset, int pageSize) {
        try {
            return new ResponseEntity<>(productRepository.findAll(PageRequest.of(offset,pageSize)),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Hello Dost");
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> findProductsWithPaginationAndSorting(int offset, int pageSize, String field) {
        try {
            return new ResponseEntity<>(productRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(Sort.Direction.ASC,field))),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }

// Update Product Price and add History Data
    public ResponseEntity<?> updateProductPrice(Product product, long newPrice){
        try {
            long old = product.getCurrentPrice();
            product.setCurrentPrice(newPrice);
            productRepository.save(product);

            History history = new History();
            history.setProduct(product);
            history.setOldPrice(old);
            history.setDate(Date.valueOf(LocalDate.now()));
            historyRepository.save(history);

            return new ResponseEntity<>("Updated",HttpStatus.ACCEPTED);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Updated",HttpStatus.BAD_REQUEST);
    }
}
