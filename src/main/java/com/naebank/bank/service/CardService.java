package com.naebank.bank.service;

import com.naebank.bank.controller.dto.CardDto;
import com.naebank.bank.mapper.CardMapper;
import com.naebank.bank.repository.CardRepository;
import com.naebank.bank.repository.UserRepository;
import com.naebank.bank.repository.entity.CardEntity;
import com.naebank.bank.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.NullServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public void addNewCard(String type, int cardMask, int expMonth, int expYear, boolean isDefault) {
        CardEntity newCard = new CardEntity();
        newCard.setType(type);
        newCard.setCardMask(cardMask);
        newCard.setExpMonth(expMonth);
        newCard.setExpYear(expYear);
        newCard.setIsDefault(isDefault);
        newCard.setAmount(0);

        Optional<UserEntity> currentUser = userRepository.findById(getCurrentUserId());
        currentUser.ifPresent(newCard::setUser);

        cardRepository.save(newCard);
    }

    public List<CardEntity> getCardsByUserId(long user_id) {
        return cardRepository.findByUserId(user_id);
    }

    public CardEntity getCardById(Long id) {
        return cardRepository.findById(id).get();
    }

    public CardEntity updateCardBalance(Long id, int amount) {
        CardEntity cardToUpdate = cardRepository.findById(id).get();
        int currentBalance = cardToUpdate.getAmount();
        cardToUpdate.setAmount(currentBalance + amount);
        return cardRepository.save(cardToUpdate);
    }

    public String deleteCardById(Long id) {
        cardRepository.deleteById(id);

        return "Card " + id + " was deleted";
    }

    private long getCurrentUserId() {
        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userRepository.findByEmail(email).get().getId();
    }
}
