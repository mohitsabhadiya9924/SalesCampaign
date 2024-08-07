package com.SalesCompaignManagement.SalesCompaign.Service;

import com.SalesCompaignManagement.SalesCompaign.Models_Entities.CampaignDiscount;
import com.SalesCompaignManagement.SalesCompaign.Models_Entities.History;
import com.SalesCompaignManagement.SalesCompaign.Models_Entities.Product;
import com.SalesCompaignManagement.SalesCompaign.Models_Entities.SaleCampaign;
import com.SalesCompaignManagement.SalesCompaign.Repository.CampaignRepository;
import com.SalesCompaignManagement.SalesCompaign.Repository.HistoryRepository;
import com.SalesCompaignManagement.SalesCompaign.Repository.ProductRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceForCampaignApply {

    private final ProductRepository productRepository;

    private final CampaignRepository campaignRepository;

    private final HistoryRepository historyRepository;

    public ServiceForCampaignApply(ProductRepository productRepository, CampaignRepository campaignRepository, HistoryRepository historyRepository) {
        this.productRepository = productRepository;
        this.campaignRepository = campaignRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional
    public void applyCampaigns() {
        List<Object[]> campaignsDiscount = campaignRepository.getAllCampaignDiscount();
//        List<Integer> cidList = campaignRepository.getAllCampaignsStart();

        List<SaleCampaign> campaigns = campaignsDiscount
                .stream().map(objects -> campaignRepository.findById((int)objects[0]).orElse(null))
                .filter(saleCampaign -> saleCampaign!=null)
                .toList();

//        List<SaleCampaign> campaigns = cidList
//                .stream().map(objects -> campaignRepository.findById(objects).orElse(null))
//                .filter(saleCampaign -> saleCampaign!=null)
//                .toList();
        campaigns.forEach(this::applyCampaign);
    }

    @Transactional
    @Async
    private void applyCampaign(SaleCampaign saleCampaign) {
        saleCampaign.setStatus("Current Campaign");
        List<CampaignDiscount> campaignDiscounts = saleCampaign.getCampaignDiscounts();
        List<Product> products = campaignDiscounts.stream()
                .map(campaignDiscount -> productRepository.findById(campaignDiscount.getProductId().getpId()).orElse(null))
                .filter(product -> product!=null)
                .collect(Collectors.toList());

        List<History> histories = products.stream()
                .map(product -> createHistory(saleCampaign, product))
                .collect(Collectors.toList());

        for (Product product : products) {
            applyDiscountOnProduct(product,saleCampaign);
        }

        productRepository.saveAll(products);
        historyRepository.saveAll(histories);
    }

    private History createHistory(SaleCampaign saleCampaign, Product product) {
        History history = new History();
        history.setDate(saleCampaign.getStartDate());
        history.setProduct(product);
        history.setOldPrice(product.getCurrentPrice());
        history.setDiscount(product.getDiscount());
        return history;
    }

    private void applyDiscountOnProduct(Product product, SaleCampaign saleCampaign) {
        double discount = saleCampaign.getCampaignDiscounts().stream()
                .filter(campaignDiscount -> campaignDiscount.getProductId().getpId() == product.getpId())
                .findFirst()
                .map(CampaignDiscount::getDiscount)
                .orElse(0.0);

        long currentPrice = product.getCurrentPrice();
        long newPrice = (long) (currentPrice - (currentPrice*discount)/100);

        product.setCurrentPrice(newPrice);
        product.setDiscount(discount + product.getDiscount());
    }
}
