package com.assignment.webservice.service;

import com.assignment.webservice.dto.TransactionRequestDto;
import com.assignment.webservice.model.Transaction;

import java.util.List;

/**
 * Created by Suraj Gautam.
 */
public interface TransactionProcessorService {
    List<Transaction> getTransactionsForCsv(TransactionRequestDto transactionRequestDto);
    List<Transaction> getTransactionsForXml(TransactionRequestDto transactionRequestDto);
}
