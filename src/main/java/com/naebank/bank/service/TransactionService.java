package com.naebank.bank.service;

import com.naebank.bank.repository.CardRepository;
import com.naebank.bank.repository.TransactionRepository;
import com.naebank.bank.repository.entity.CardEntity;
import com.naebank.bank.repository.entity.TransactionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<TransactionEntity> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
