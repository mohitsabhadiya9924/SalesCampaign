package com.SalesCompaignManagement.SalesCompaign.Service;

import com.SalesCompaignManagement.SalesCompaign.Models_Entities.CampaignDiscount;
import com.SalesCompaignManagement.SalesCompaign.Models_Entities.SaleCampaign;
import com.SalesCompaignManagement.SalesCompaign.Repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//@EnableScheduling
//@Configuration
@Service
public class CampaignServices {
    @Autowired
    private CampaignRepository campaignRepository;


    // Add Campaign
    public ResponseEntity<?> addCampaign(SaleCampaign saleCampaigns) {
        try {
            List<CampaignDiscount> campaignDiscounts = saleCampaigns.getCampaignDiscounts();
            for (CampaignDiscount campaignDiscount : campaignDiscounts) {
                campaignDiscount.setSaleCampaign(saleCampaigns);
            }

            LocalDate startDate = saleCampaigns.getStartDate().toLocalDate();
            LocalDate endDate = saleCampaigns.getEndDate().toLocalDate();

            if (LocalDate.now().isEqual(startDate) || (LocalDate.now().isAfter(startDate) && LocalDate.now().isBefore(endDate))) {
                saleCampaigns.setStatus("Current Campaign");
            }
            else if (LocalDate.now().isBefore(startDate)) {
                saleCampaigns.setStatus("Upcoming Campaign");
            }
            else if (LocalDate.now().isAfter(endDate)) {
                saleCampaigns.setStatus("Past Campaign");
            }

            return new ResponseEntity<>(campaignRepository.save(saleCampaigns), HttpStatus.CREATED);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not added",HttpStatus.BAD_REQUEST);
    }


    // Get All Campaign
    public ResponseEntity<?> getAllCampaign(){
        try {
            return new ResponseEntity<>(campaignRepository.findAll(),HttpStatus.FOUND);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Find", HttpStatus.NOT_FOUND);
    }


    // Get Campaign By ID
    public ResponseEntity<?> getById(int id) {
        try {
            return new ResponseEntity<>(campaignRepository.findById(id),HttpStatus.FOUND);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }

}
