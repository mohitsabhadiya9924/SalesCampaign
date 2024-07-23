package com.SalesCompaignManagement.SalesCompaign.Controller;

import com.SalesCompaignManagement.SalesCompaign.Models_Entities.Product;
import com.SalesCompaignManagement.SalesCompaign.Models_Entities.SaleCampaign;
import com.SalesCompaignManagement.SalesCompaign.Service.CampaignServices;
import com.SalesCompaignManagement.SalesCompaign.Service.HistoryService;
import com.SalesCompaignManagement.SalesCompaign.Service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Product")
public class ProductController {
    @Autowired
    private ProductServices productServices;
    @Autowired
    private HistoryService historyService;


    // Product Part :-

    // Add data in database
    @PostMapping("/save")
    public ResponseEntity<?> addData(@RequestBody List<Product> products) {
        try {
            return new ResponseEntity<>(productServices.addData(products), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Entry Failed", HttpStatus.BAD_REQUEST);
    }


    // Sort the data by field
    @GetMapping("/getBySorting/{field}")
    public ResponseEntity<?> findBySorting(@PathVariable String field) {
        try {
            return new ResponseEntity<>(productServices.findByFieldSorting(field),HttpStatus.FOUND);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }


    // Only Pagination
    @GetMapping("/findByPagination/{offset}/{pageSize}")
    public ResponseEntity<?> getProductsWithPagination(@PathVariable int offset,@PathVariable int pageSize){
        try {
            return new ResponseEntity<>(productServices.findProductsWithPagination(offset,pageSize),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }


    // Pagination applying sorting with any field
    @GetMapping("/findByPaginationAndSorting/{offset}/{pageSize}/{field}")
    public ResponseEntity<?> getProductsWithPaginationAndSorting(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String field){
        try {
            return new ResponseEntity<>(productServices.findProductsWithPaginationAndSorting(offset,pageSize,field),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }


//     Product Price Updated
    @PutMapping("/priceUpdated")
    public ResponseEntity<?> updatePrice(@RequestBody Product product,@RequestParam long newPrice) {
        try {
            return new ResponseEntity<>(productServices.updateProductPrice(product,newPrice),HttpStatus.ACCEPTED);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

//    // History :-
//
//    // Get Products By Product ID from History
//    @GetMapping("/findByProductIdFromHistory")
//    public ResponseEntity<?> findByProductId(@RequestParam int p_id) {
//        try {
//            return new ResponseEntity<>(historyService.getPricingHistoryByProductId(p_id),HttpStatus.FOUND);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
//    }
}
