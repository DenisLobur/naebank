package com.naebank.bank.controller;

import com.naebank.bank.controller.dto.AuthDto;
import com.naebank.bank.controller.dto.TransactionDto;
import com.naebank.bank.mapper.TransactionMapper;
import com.naebank.bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/{cardId}")
    public ResponseEntity<AuthDto> addNewTransaction(@PathVariable("cardId") Long cardId, @RequestBody TransactionDto transactionDto) {

        transactionService.addNewTransaction(
                cardId,
                transactionDto.getAmount(),
                transactionDto.getType(),
                transactionDto.getStatus()
        );

        AuthDto authDto = new AuthDto(
                "Transaction added",
                null
        );

        return new ResponseEntity<>(
                authDto,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions(@Param("user_id") Long user_id) {
        List<TransactionDto> transactions = TransactionMapper.INSTANCE.toDtoList(transactionService.getAllTransactions(user_id));
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
