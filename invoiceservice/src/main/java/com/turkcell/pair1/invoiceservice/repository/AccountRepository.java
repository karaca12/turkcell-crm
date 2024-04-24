package com.turkcell.pair1.invoiceservice.repository;

import com.turkcell.pair1.invoiceservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Modifying
    @Query("UPDATE Account ac SET ac.updatedAt = current timestamp WHERE ac.id=:id and ac.isDeleted= false")
    void updateAccountById(@Param("id") Integer id);

    Optional<Account> findByIsDeletedFalseAndId(Integer id);
}
