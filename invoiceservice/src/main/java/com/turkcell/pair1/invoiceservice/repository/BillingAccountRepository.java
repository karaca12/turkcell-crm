package com.turkcell.pair1.invoiceservice.repository;

import com.turkcell.pair1.invoiceservice.entity.BillingAccount;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateBillingAccountInfoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingAccountRepository extends JpaRepository<BillingAccount, Integer> {

    Optional<BillingAccount> findByAccount_IsDeletedFalseAndAccountNumber(String accountNumber);

    @Modifying
    @Query("update BillingAccount ba set ba.name= :#{#request.name}," +
            " ba.description= :#{#request.description} WHERE ba.id=:id ")
    void updateBillingAccountById(Integer id, @Param("request") UpdateBillingAccountInfoRequest request);

    Optional<BillingAccount> findByAccountNumber(String accountNumber);

//    @Modifying
//    @Query(value ="select com.turkcell.pair1.invoiceservice.service.dto.response."+
//            "GetBillingAccountInfoResponse(ba.name,ba.description) " +
//            "from BillingAccount ba " +
//            "where ba.accountNumber=:accountNumber " +
//            "and ba.account.isDeleted=false")
//    GetBillingAccountInfoResponse getBillingAccountInfoByAccountNumber(String accountNumber);


}

