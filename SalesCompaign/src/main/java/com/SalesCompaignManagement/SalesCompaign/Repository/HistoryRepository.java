package com.SalesCompaignManagement.SalesCompaign.Repository;

import com.SalesCompaignManagement.SalesCompaign.Models_Entities.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
    @Query(value = "select * from tbl_history where p_id=?1 and date=?2",nativeQuery = true)
    History findHistory(@Param("p_id") int id,@Param("date") Date start_date);
}
