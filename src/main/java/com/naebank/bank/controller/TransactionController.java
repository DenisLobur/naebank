package com.naebank.bank.controller;

import com.naebank.bank.controller.dto.TransactionDto;
import com.naebank.bank.mapper.TransactionMapper;
import com.naebank.bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<TransactionDto> transactions = TransactionMapper.INSTANCE.toDtoList(transactionService.getAllTransactions());
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
