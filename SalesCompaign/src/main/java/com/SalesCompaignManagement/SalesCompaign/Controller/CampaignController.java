package com.SalesCompaignManagement.SalesCompaign.Controller;

import com.SalesCompaignManagement.SalesCompaign.Models_Entities.SaleCampaign;
import com.SalesCompaignManagement.SalesCompaign.Service.CampaignServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Campaign")
public class CampaignController {

    @Autowired
    private CampaignServices campaignServices;
    // Campaign Part :-

    // Add data in database
    @PostMapping("/addCampaign")
    public ResponseEntity<?> addCampaign(@RequestBody SaleCampaign saleCampaign) {
        return new ResponseEntity<>(campaignServices.addCampaign(saleCampaign), HttpStatus.ACCEPTED);
    }

    // Campaign Discount Apply :-

//    @PostMapping("/addCampaignDiscount")
//    public ResponseEntity<?> addCampaignDiscount(@RequestBody ) {
//        return new ResponseEntity<>("",HttpStatus.ACCEPTED);
//    }
}
