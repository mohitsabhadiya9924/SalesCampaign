package com.SalesCompaignManagement.SalesCompaign.Models_Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "tbl_campaign")
public class SaleCampaign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    private int campaignId;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "title")
    private String title;
    @OneToMany(mappedBy = "saleCampaign",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<CampaignDiscount> campaignDiscounts;
    @Column(name = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SaleCampaign() {
    }

    public SaleCampaign(int campaignId, Date startDate, Date endDate, String title, List<CampaignDiscount> campaignDiscounts,String status) {
        this.campaignId = campaignId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.campaignDiscounts = campaignDiscounts;
        this.status = status;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CampaignDiscount> getCampaignDiscounts() {
        return campaignDiscounts;
    }

    public void setCampaignDiscounts(List<CampaignDiscount> campaignDiscounts) {
        this.campaignDiscounts = campaignDiscounts;
    }
}
