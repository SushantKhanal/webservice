package com.assignment.webservice.controller;

import com.assignment.webservice.constants.WebResourceURIConstants;
import com.assignment.webservice.dto.TransactionRequestDto;
import com.assignment.webservice.exception.InvalidTransactionException;
import com.assignment.webservice.model.Transaction;
import com.assignment.webservice.service.TransactionProcessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Suraj Gautam.
 */

/**
 * TransactionProcessorController for CSV and XML
 */
@RestController
public class TransactionProcessorController {

    private final TransactionProcessorService transactionProcessorService;

    public TransactionProcessorController(TransactionProcessorService transactionProcessorService) {
        this.transactionProcessorService = transactionProcessorService;
    }


    /**
     * @param transactionRequestDto request dto with csv transaction data
     * @return List of {@link Transaction}
     * @throws InvalidTransactionException whenever the data is null or empty
     */
    @PostMapping(value = WebResourceURIConstants.CSV_TRANSACTION_PROCESSOR)
    public ResponseEntity<List<Transaction>> getCsvTransaction(@RequestBody TransactionRequestDto transactionRequestDto) {
        return ResponseEntity.ok(transactionProcessorService.getTransactionsForCsv(transactionRequestDto));
    }

    /**
     * @param transactionRequestDto request dto with xml transaction data
     * @return List of {@link Transaction}
     * @throws InvalidTransactionException whenever the data is null or empty
     */
    @PostMapping(value = WebResourceURIConstants.XML_TRANSACTION_PROCESSOR)
    public ResponseEntity<List<Transaction>> getXmlTransaction(@RequestBody TransactionRequestDto transactionRequestDto) {
        return ResponseEntity.ok(transactionProcessorService.getTransactionsForXml(transactionRequestDto));
    }

}
