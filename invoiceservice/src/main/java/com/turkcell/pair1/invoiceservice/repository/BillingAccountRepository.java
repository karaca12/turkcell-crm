package com.turkcell.pair1.invoiceservice.repository;

import com.turkcell.pair1.invoiceservice.entity.BillingAccount;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateBillingAccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface BillingAccountRepository extends JpaRepository<BillingAccount, Integer> {
    Optional<BillingAccount> findByAccount_IsDeletedFalseAndId(Integer id);
    @Modifying
    @Query("update BillingAccount ba set ba.name= :#{#request.name}," +
            " ba.description= :#{#request.description}, " +
            "ba.account.updatedAt= current timestamp where ba.id= :#{#id} and " +
            "ba.account.isDeleted= false ")
    void updateBillingAccountById(Integer id, UpdateBillingAccountRequest request);
}

