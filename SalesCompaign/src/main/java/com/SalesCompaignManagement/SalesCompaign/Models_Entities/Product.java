package com.SalesCompaignManagement.SalesCompaign.Models_Entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_product")
public class Product {
    @Id
    @Column(name = "p_id")
    private int pId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "mrp")
    private long mrp;
    @Column(name = "curr_price")
    private long currentPrice;
    @Column(name = "discount")
    private double discount;
    @Column(name = "inventory_count")
    private int inventoryCount;

    @OneToMany(mappedBy = "productId",cascade = CascadeType.ALL)
    private List<CampaignDiscount> campaignDiscounts;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<History> histories = new ArrayList<>();
    public Product() {
    }

    public Product(int pId) {
        this.pId = pId;
    }

    public Product(int pId, String title, String description, long mrp, long currentPrice, double discount, int inventoryCount) {
        this.pId = pId;
        this.title = title;
        this.description = description;
        this.mrp = mrp;
        this.currentPrice = currentPrice;
        this.discount = discount;
        this.inventoryCount = inventoryCount;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getMrp() {
        return mrp;
    }

    public void setMrp(long mrp) {
        this.mrp = mrp;
    }

    public long getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(long currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    public List<CampaignDiscount> getCampaignDiscounts() {
        return campaignDiscounts;
    }

    public void setCampaignDiscounts(List<CampaignDiscount> campaignDiscounts) {
        this.campaignDiscounts = campaignDiscounts;
    }

    public List<History> getHistories(){
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }
}
