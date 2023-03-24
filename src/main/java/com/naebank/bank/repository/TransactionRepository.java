package com.naebank.bank.repository;

import com.naebank.bank.repository.entity.CardEntity;
import com.naebank.bank.repository.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByUserId(Long user_id);
}
