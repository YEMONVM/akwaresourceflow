package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillRepo extends JpaRepository<Bill,Long> {
    @Query("SELECT b FROM Bill b WHERE b.orderdate BETWEEN :startDate AND :endDate")
    List<Bill> findBillsBetweenDates(Date startDate, Date endDate);

    @Query("SELECT SUM(b.totalamount) FROM Bill b WHERE b.orderdate BETWEEN :startDate AND :endDate")
    Double findTotalRevenueBetweenDates(Date startDate, Date endDate);
}
