package com.turkcell.pair1.invoiceservice.repository;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Modifying
    @Query("UPDATE Account a SET a.updatedAt = current timestamp WHERE a.id = :id")
    void updateAccountById(@Param("id") Integer accountId);

    Optional<Account> findByIsDeletedFalseAndId(Integer id);
}
