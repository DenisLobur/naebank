package com.naebank.bank.controller;

import com.naebank.bank.controller.dto.AuthDto;
import com.naebank.bank.controller.dto.CardDto;
import com.naebank.bank.mapper.CardMapper;
import com.naebank.bank.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @GetMapping
    public ResponseEntity<List<CardDto>> getCardsByUserId(@Param("user_id") Long user_id) {
        List<CardDto> userCards = CardMapper.INSTANCE.toDtoList(cardService.getCardsByUserId(user_id));

        return new ResponseEntity<>(userCards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDto> getCardById(@PathVariable("id") Long id) {
        CardDto card = CardMapper.INSTANCE.toDto(cardService.getCardById(id));

        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<CardDto> updateCardBalance(@RequestBody CardDto cardDto) {
        CardDto card = CardMapper.INSTANCE.toDto(cardService.updateCardBalance(cardDto.getId(), cardDto.getAmount()));

        return new ResponseEntity<>(card, HttpStatus.ACCEPTED);
    }

//    @GetMapping
//    public ResponseEntity<List<CardDto>> getAllCards() {
//        List<CardDto> cards = CardMapper.INSTANCE.toDtoList(cardService.getAllCards());
//
//        return new ResponseEntity<>(cards, HttpStatus.OK);
//    }
}
