package com.assignment.webservice.processors;

import com.assignment.webservice.enums.FileTypeEnum;
import com.assignment.webservice.model.Transaction;
import com.assignment.webservice.model.TransactionProcessor;
import com.assignment.webservice.utils.TransactionUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suraj Gautam.
 */
public class CsvTransactionProcessor implements TransactionProcessor {

    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public void importTransactions(InputStream inputStream) {
        transactions = TransactionUtils.convertToTransaction(inputStream, FileTypeEnum.CSV);
    }

    @Override
    public List<Transaction> getImportedTransactions() {
        return transactions;
    }
}
