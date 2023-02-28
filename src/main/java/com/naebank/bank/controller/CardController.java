package com.naebank.bank.controller;

import com.naebank.bank.controller.dto.CardDto;
import com.naebank.bank.mapper.CardMapper;
import com.naebank.bank.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<CardDto>> getAllCards() {
        List<CardDto> cards = CardMapper.INSTANCE.toDtoList(cardService.getAllCards());
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }
}
