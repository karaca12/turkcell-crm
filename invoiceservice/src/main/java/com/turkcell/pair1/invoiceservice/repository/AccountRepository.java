package com.turkcell.pair1.invoiceservice.repository;

import com.turkcell.pair1.invoiceservice.entity.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Modifying
    @Query("UPDATE Account ac SET ac.updatedAt = current timestamp WHERE ac.id=:id and ac.isDeleted= false")
    void updateAccountById(@Param("id") Integer id);

    Optional<Account> findByIsDeletedFalseAndAccountNumber(String accountNumber);

    List<Account> findByCustomerId(String id, Pageable pageable);

    Optional<Account> findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);

    List<Account> findByCustomerId(String customerId);
}