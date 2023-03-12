package com.naebank.bank.controller;

import com.naebank.bank.controller.dto.AuthDto;
import com.naebank.bank.controller.dto.CardDto;
import com.naebank.bank.mapper.CardMapper;
import com.naebank.bank.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<AuthDto> addNewCard(@RequestBody CardDto cardDto) {
        cardService.addNewCard(
                cardDto.getType(),
                cardDto.getCardMask(),
                cardDto.getExpMonth(),
                cardDto.getExpYear(),
                cardDto.getIsDefault()
        );

        AuthDto authDto = new AuthDto(
                "Card " + cardDto.getType() + "[" + cardDto.getCardMask().toString() + "]" + " was created!",
                null
        );

        return new ResponseEntity<>(
                authDto,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CardDto>> getCardsByUSerId(@PathVariable Long id) {
        List<CardDto> userCards = CardMapper.INSTANCE.toDtoList(cardService.getCardsByUserId(id));

        return new ResponseEntity<>(userCards, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CardDto>> getAllCards() {
        List<CardDto> cards = CardMapper.INSTANCE.toDtoList(cardService.getAllCards());

        return new ResponseEntity<>(cards, HttpStatus.OK);
    }
}
