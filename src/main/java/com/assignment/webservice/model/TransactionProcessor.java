package com.assignment.webservice.model;

import com.assignment.webservice.exception.InvalidTransactionException;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Ahmad Y. Saleh on 7/24/17.
 */
public interface TransactionProcessor {

    void importTransactions(InputStream is);

    List<Transaction> getImportedTransactions();
}
