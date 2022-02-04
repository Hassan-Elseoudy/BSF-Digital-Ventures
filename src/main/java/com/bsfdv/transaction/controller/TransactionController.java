package com.bsfdv.transaction.controller;

import com.bsfdv.transaction.controller.dto.AddTransactionDto;
import com.bsfdv.transaction.controller.response.TransactionResponseDtoV1;
import com.bsfdv.transaction.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Persists the given transaction in the database.
     *
     * @param addTransactionDto The transaction to persist
     * @return The persisted transaction
     */
    @PostMapping
    public ResponseEntity<TransactionResponseDtoV1> createOne(@Valid @RequestBody AddTransactionDto addTransactionDto) {
        TransactionResponseDtoV1 responseDtoV1 = TransactionResponseDtoV1.toDto(transactionService.createOne(addTransactionDto));
        return ResponseEntity
                .created(URI.create(responseDtoV1.getId().toString()))
                .body(responseDtoV1);
    }

    /**
     * Get a specific transaction's information.
     *
     * @param id The id of the transaction
     * @return The persisted transaction.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<TransactionResponseDtoV1> getOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(TransactionResponseDtoV1.toDto(transactionService.getOne(id)));
    }

}