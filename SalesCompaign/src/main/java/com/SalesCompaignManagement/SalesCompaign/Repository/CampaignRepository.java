package com.SalesCompaignManagement.SalesCompaign.Repository;

import com.SalesCompaignManagement.SalesCompaign.Models_Entities.CampaignDiscount;
import com.SalesCompaignManagement.SalesCompaign.Models_Entities.History;
import com.SalesCompaignManagement.SalesCompaign.Models_Entities.SaleCampaign;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CampaignRepository extends JpaRepository<SaleCampaign, Integer> {
    @Query(value = "select * from tbl_campaign where start_date=current_date()",nativeQuery = true)
    List<Object[]> getAllCampaignDiscount();

    @Query(value = "select cid from tbl_campaign where start_date=current_date()",nativeQuery = true)
    List<Integer> getAllCampaignsStart();

    @Query(value = "select * from tbl_campaign where end_date=current_date()",nativeQuery = true)
    List<Object[]> getAllCampaignEnds();
}
