package com.bsfdv.transaction.controller;

import com.bsfdv.transaction.controller.dto.CreateTransactionDto;
import com.bsfdv.transaction.controller.response.TransactionResponseDtoV1;
import com.bsfdv.transaction.service.TransactionService;
import com.bsfdv.transaction.util.error.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
     * @param createTransactionDto The transaction to persist
     * @return The persisted transaction
     */
    @Operation(summary = "Create a new Transaction",
    description = "Create a new Transaction, either (PAY|DEPOSIT|WITHDRAW) If the transaction is (PAY) you have to provide receiverId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction created Successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponseDtoV1.class)) }),
            @ApiResponse(responseCode = "409", description = "Invalid Transaction due to Not Enough Balance.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TransactionResponseDtoV1> createOne(@Valid @RequestBody CreateTransactionDto createTransactionDto) throws Exception {
        TransactionResponseDtoV1 responseDtoV1 = TransactionResponseDtoV1.toDto(transactionService.createOne(createTransactionDto));
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
    @Operation(summary = "Get a specific transaction's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Transaction",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponseDtoV1.class)) }),
            @ApiResponse(responseCode = "404", description = "Transaction not found",
                    content = @Content) })
    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TransactionResponseDtoV1> getOne(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(TransactionResponseDtoV1.toDto(transactionService.getOne(id)));
    }

}