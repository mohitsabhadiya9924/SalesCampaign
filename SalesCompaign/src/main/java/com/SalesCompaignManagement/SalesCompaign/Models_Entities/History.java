package com.SalesCompaignManagement.SalesCompaign.Models_Entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "tbl_history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int historyId;
    @ManyToOne
    @JoinColumn(name = "p_id")
    private Product product;
    private long oldPrice;

    private Date date;

    private double discount;

    public History() {
    }

    public History(int historyId, Product product, long oldPrice, Date date,double discount) {
        this.historyId = historyId;
        this.product = product;
        this.oldPrice = oldPrice;
        this.date = date;
        this.discount = discount;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(long oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
