package com.SalesCompaignManagement.SalesCompaign.Models_Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_campaign_discount")
public class CampaignDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_dis_id")
    private int campaignDiscountId;

    @Column(name = "c_dis_p_discount")
    private double discount;

    @ManyToOne
    @JoinColumn(name = "p_id")
    private Product productId;

   @ManyToOne
   @JoinColumn(name = "c_id")
   @JsonBackReference
   private SaleCampaign saleCampaign;

    public CampaignDiscount() {

    }

    public CampaignDiscount(int campaignDiscountId, Product productId, double discount, SaleCampaign saleCampaigns) {
        this.campaignDiscountId = campaignDiscountId;
        this.productId = productId;
        this.discount = discount;
        this.saleCampaign = saleCampaigns;
    }

    public int getCampaignDiscountId() {
        return campaignDiscountId;
    }

    public void setCampaignDiscountId(int campaignDiscountId) {
        this.campaignDiscountId = campaignDiscountId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public SaleCampaign getSaleCampaign() {
        return saleCampaign;
    }

    public void setSaleCampaign(SaleCampaign saleCampaign) {
        this.saleCampaign = saleCampaign;
    }
}
