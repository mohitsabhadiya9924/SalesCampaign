package com.SalesCompaignManagement.SalesCompaign.Service;

import com.SalesCompaignManagement.SalesCompaign.Repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

//    public ResponseEntity<?> getPricingHistoryByProductId(int id) {
//        try {
//            return new ResponseEntity<>(historyRepository.findByProductId(id), HttpStatus.FOUND);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
//    }
}
