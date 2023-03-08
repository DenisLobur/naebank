package com.naebank.bank.service;

import com.naebank.bank.repository.CardRepository;
import com.naebank.bank.repository.UserRepository;
import com.naebank.bank.repository.entity.CardEntity;
import com.naebank.bank.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
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
        newCard.setIsDefault(isDefault); //TODO: clear default flag from other cards

        //TODO: find a way of inserting userId here
        Optional<UserEntity> currentUser = userRepository.findById(1L);
        currentUser.ifPresent(newCard::setUser);

        cardRepository.save(newCard);
    }

    public List<CardEntity> getCardsByUserId(long userId) {
        return cardRepository.findByUserId(userId);
    }

    public List<CardEntity> getAllCards() {
        return cardRepository.findAll();
    }
}
