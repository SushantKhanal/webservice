package com.assignment.webservice.service.impl;

import com.assignment.webservice.dto.TransactionRequestDto;
import com.assignment.webservice.exception.InvalidTransactionException;
import com.assignment.webservice.model.Transaction;
import com.assignment.webservice.model.TransactionProcessor;
import com.assignment.webservice.service.TransactionProcessorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.awt.dnd.InvalidDnDOperationException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * Created by Suraj Gautam.
 */
@Service
public class TransactionProcessorServiceImpl implements TransactionProcessorService {


    private final TransactionProcessor transactionProcessorCsv;
    private final TransactionProcessor transactionProcessorXml;

    public TransactionProcessorServiceImpl(@Qualifier("csv") TransactionProcessor transactionProcessorCsv,
                                           @Qualifier("xml") TransactionProcessor transactionProcessorXml) {
        this.transactionProcessorCsv = transactionProcessorCsv;
        this.transactionProcessorXml = transactionProcessorXml;
    }

    @Override
    public List<Transaction> getTransactionsForCsv(TransactionRequestDto transactionRequestDto) {
        List<Transaction> transactions = getTransactions(transactionRequestDto, transactionProcessorCsv);
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsForXml(TransactionRequestDto transactionRequestDto) {
        List<Transaction> transactions = getTransactions(transactionRequestDto, transactionProcessorXml);
        return transactions;
    }

    public List<Transaction> getTransactions(TransactionRequestDto transactionRequestDto,
                                              TransactionProcessor transactionProcessor) {
        validateTransactionRequestDto(transactionRequestDto);
        transactionProcessor.importTransactions(convertByteArrayToInputStream(transactionRequestDto.getTransactionData()));
        return transactionProcessor.getImportedTransactions();
    }

    public void validateTransactionRequestDto(TransactionRequestDto transactionRequestDto) {
        final int BYTE_ARRAY_ZERO_LENGTH = 0;
        Optional.ofNullable(transactionRequestDto.getTransactionData())
                .orElseThrow(() -> new InvalidTransactionException("Bad request."));
        if (transactionRequestDto.getTransactionData().length == BYTE_ARRAY_ZERO_LENGTH) {
            throw new InvalidTransactionException("Transaction Cannot be Empty");
        }
    }

    public InputStream convertByteArrayToInputStream(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }
}
