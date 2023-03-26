package com.naebank.bank.service;

import com.naebank.bank.repository.CardRepository;
import com.naebank.bank.repository.TransactionRepository;
import com.naebank.bank.repository.UserRepository;
import com.naebank.bank.repository.entity.CardEntity;
import com.naebank.bank.repository.entity.TransactionEntity;
import com.naebank.bank.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public void addNewTransaction(Long cardId, Integer amount, String type, String status) {
        TransactionEntity newTransaction = new TransactionEntity();
        newTransaction.setAmount(amount);
        newTransaction.setType(type);
        newTransaction.setStatus(status);

        Optional<CardEntity> card = cardRepository.findById(cardId);
        card.ifPresent(newTransaction::setCard);
        card.ifPresent(crd -> newTransaction.setCardName(crd.getType()));

        Optional<UserEntity> currentUser = userRepository.findById(getCurrentUserId());
        currentUser.ifPresent(newTransaction::setUser);

        newTransaction.setDate(System.currentTimeMillis());

        transactionRepository.save(newTransaction);
    }

    public List<TransactionEntity> getAllTransactions(Long userId, Optional<Long> cardId) {
        if (cardId.isPresent()) {
            return transactionRepository.findByCardId(cardId.get());
        }

        return transactionRepository.findByUserId(userId);
    }

    private long getCurrentUserId() {
        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userRepository.findByEmail(email).get().getId();
    }
}
