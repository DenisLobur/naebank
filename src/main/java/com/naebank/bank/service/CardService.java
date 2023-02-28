package com.naebank.bank.service;

import com.naebank.bank.repository.CardRepository;
import com.naebank.bank.repository.entity.CardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public List<CardEntity> getAllCards() {
        return cardRepository.findAll();
    }
}
