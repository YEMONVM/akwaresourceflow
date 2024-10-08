package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillRepo extends JpaRepository<Bill, Long> {
    @Query("SELECT b FROM Bill b WHERE b.orderDate BETWEEN :startDate AND :endDate")
    List<Bill> findBillsBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT SUM(b.totalAmount) FROM Bill b WHERE b.orderDate BETWEEN :startDate AND :endDate")
    Double findTotalRevenueBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
