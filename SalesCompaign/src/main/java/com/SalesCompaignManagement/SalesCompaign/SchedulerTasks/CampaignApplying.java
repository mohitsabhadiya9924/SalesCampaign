package com.SalesCompaignManagement.SalesCompaign.SchedulerTasks;

import com.SalesCompaignManagement.SalesCompaign.Models_Entities.CampaignDiscount;
import com.SalesCompaignManagement.SalesCompaign.Models_Entities.History;
import com.SalesCompaignManagement.SalesCompaign.Models_Entities.Product;
import com.SalesCompaignManagement.SalesCompaign.Models_Entities.SaleCampaign;
import com.SalesCompaignManagement.SalesCompaign.Repository.CampaignRepository;
import com.SalesCompaignManagement.SalesCompaign.Repository.HistoryRepository;
import com.SalesCompaignManagement.SalesCompaign.Repository.ProductRepository;
import com.SalesCompaignManagement.SalesCompaign.Service.ServiceForCampaignApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class CampaignApplying {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CampaignRepository campaignRepository;
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    ServiceForCampaignApply serviceForCampaignApply;

    @Scheduled(cron = "0 37 15 * * *")
    public void startCampaign() {
        System.out.println("Campaign Start");
        System.out.println(new java.util.Date());

        // Code 1 :-

//        List<Object[]> list=campaignRepository.getAllCampaignDiscount();

//        for(Object[] objects : list){
//            SaleCampaign campaign=campaignRepository.findById((int)objects[0]).get();
//            for(CampaignDiscount campaignDiscount:campaign.getCampaignDiscounts()) {
//                Product product = productRepository.findById(campaignDiscount.getProductId().getpId()).get();
//
//                //make history of product
//                History   history = new History();
//                history.setDate(campaign.getStartDate());
//                history.setProduct(product);
//                history.setDiscount(product.getDiscount());
//                history.setOldPrice(product.getCurrentPrice());
//
//
//                //change current price pf product
//                double discount = campaignDiscount.getDiscount();
//                long currentPrice = product.getCurrentPrice();
//                long calculateCurrentPrice = (long) (currentPrice - ((discount * currentPrice) / 100));
//
//                product.setCurrentPrice(calculateCurrentPrice);
//                product.setDiscount(discount + product.getDiscount());
//
//                productRepository.save(product);
//                historyRepository.save(history);
//            }
//        }


        // Code 2 :-

        serviceForCampaignApply.applyCampaigns();
        System.out.println(new java.util.Date());
    }

    @Scheduled(cron = "59 59 23 05 08 *")
    public void closeCampaign() {
        System.out.println("Campaign Closed");
        List<Object[]> list = campaignRepository.getAllCampaignEnds();

        for (Object[] objects : list) {
            SaleCampaign campaign = campaignRepository.findById((int)objects[0]).get();
            System.out.println(campaign.getStatus());
            campaign.setStatus("Past Campaign");
            System.out.println(campaign.getStatus());


            for (CampaignDiscount discount : campaign.getCampaignDiscounts()){
                History history = historyRepository.findHistory(discount.getProductId().getpId(),campaign.getStartDate());
                Product product = productRepository.findById(discount.getProductId().getpId()).get();

                History newHistory = new History();
                newHistory.setProduct(product);
                newHistory.setOldPrice(product.getCurrentPrice());
                newHistory.setDate(campaign.getEndDate());
                newHistory.setDiscount(product.getDiscount());

//                product.setTitle(history.getProduct().getTitle());
//                product.setDescription(history.getProduct().getDescription());
//                product.setMrp(history.getProduct().getMrp());
                product.setCurrentPrice(history.getOldPrice());
//                product.set
//                product.setDiscount(history.getDiscount());
                product.setDiscount(history.getDiscount());

                productRepository.save(product);
                historyRepository.save(newHistory);
//                campaignRepository.save(campaign);
            }
        }
    }


//    @Scheduled(cron="59 59 23 * * *")
//    public void removeCampaignDiscount(){
//
//        List<Object[]> list=campaignRepo.getEndingCampaignDiscountList();
//
//        for(Object[] objects:list){
//            Campaign campaign=campaignRepo.findById((int)objects[0]).get();
//            for(CampaignDiscount campaignDiscount:campaign.getCampaignDiscountList()) {
//                History oldHistory=historyRepo.getHistory(campaign.getStartDate(),campaignDiscount.getProductId().getpId());
//
//                //find product
//                Product product=productRepo.findById(campaignDiscount.getProductId().getpId()).get();
//
//                History newHistory=new History();
//                newHistory.setProduct(product);
//                newHistory.setDiscount(product.getDiscount());
//                newHistory.setPrice(product.getCurrentPrice());
//                newHistory.setDate(new Date(System.currentTimeMillis()));
//
//                // make change in product to remove campaign
//
//                product.setCurrentPrice(oldHistory.getPrice());
//                product.setDiscount(oldHistory.getDiscount());
//
//                productRepo.save(product);
//                historyRepo.save(newHistory);
//
//            }
//        }
}
