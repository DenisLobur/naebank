package com.naebank.bank.repository;

import com.naebank.bank.repository.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    List<CardEntity> findByUserId(Long user_id);

}
