package com.assignment.webservice.service;

import com.assignment.webservice.TransactionProcessorAbstract;
import com.assignment.webservice.dto.TransactionRequestDto;
import com.assignment.webservice.enums.FileTypeEnum;
import com.assignment.webservice.exception.InvalidTransactionException;
import com.assignment.webservice.factory.TransactionProcessorFactory;
import com.assignment.webservice.model.Transaction;
import com.assignment.webservice.model.TransactionProcessor;
import com.assignment.webservice.service.impl.TransactionProcessorServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Suraj Gautam.
 */
public class TransactionProcessorServiceTest extends TransactionProcessorAbstract {

    private static TransactionProcessorServiceImpl transactionProcessorService;
    private static TransactionProcessor transactionProcessorCsv;
    private static TransactionProcessor transactionProcessorXml;

    @BeforeClass
    public static void setUp() {
        transactionProcessorCsv = TransactionProcessorFactory.getInstance(FileTypeEnum.CSV);
        transactionProcessorXml = TransactionProcessorFactory.getInstance(FileTypeEnum.XML);
        transactionProcessorService = new TransactionProcessorServiceImpl(transactionProcessorCsv, transactionProcessorXml);
    }

    @Test
    public void getTransactionsTest_whenValidCsvTransactions_returnsTrueForNotEmpty() {
        List<Transaction> transactions = transactionProcessorService
                .getTransactions(getTransactionRequestDto(getValidCsvTransactions())
                        , transactionProcessorCsv);
        assertThat(transactions).isNotEmpty();
    }

    @Test
    public void getTransactionsTest_whenValidCsvTransactions_returnsTrueForSize() {
        List<Transaction> transactions = transactionProcessorService
                .getTransactions(getTransactionRequestDto(getValidCsvTransactions())
                        , transactionProcessorCsv);
        assertThat(transactions).hasSameSizeAs(getTransactionsDummy());
    }

    @Test
    public void getTransactionsTest_whenValidCsvTransactions_returnsTrueForElements() {
        List<Transaction> transactions = transactionProcessorService
                .getTransactions(getTransactionRequestDto(getValidCsvTransactions())
                        , transactionProcessorCsv);
        assertThat(transactions.get(0).getType()).isEqualTo(getTransactionsDummy().get(0).getType());
        assertThat(transactions.get(1).getNarration()).isEqualTo(getTransactionsDummy().get(1).getNarration());
        assertThat(transactions.get(2).getAmount()).isEqualTo(getTransactionsDummy().get(2).getAmount());
    }

    @Test(timeout = 1000)
    public void getTransactionsTest_whenValidCsvTransactions_returnsTrueForLessThanASecond() {
        transactionProcessorService
                .getTransactions(getTransactionRequestDto(getValidCsvTransactions())
                        , transactionProcessorCsv);
    }

    @Test(expected = InvalidTransactionException.class)
    public void getTransactionsTest_whenEmptyCsvTransactionData_returnsTrueForInvalidTransactionException() {
        transactionProcessorService
                .getTransactions(getTransactionRequestDto(getNullCsvTransactions())
                        , transactionProcessorCsv);
    }

    @Test
    public void getTransactionsTest_whenNullCsvTransactionData_returnsTrueForErrorMessage() {
        final String expectedErrorMessage = "Bad request.";
        String actualErrorMessage = null;
        try {
            transactionProcessorService
                    .getTransactions(new TransactionRequestDto()
                            , transactionProcessorCsv);
        } catch (InvalidTransactionException e) {
            actualErrorMessage = e.getLocalizedMessage();
        }
        assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage);
    }

    @Test(expected = InvalidTransactionException.class)
    public void validateTransactionRequestDto_whenFieldIsNull_returnsInvalidTransactionException() {
        transactionProcessorService.validateTransactionRequestDto(new TransactionRequestDto());
    }

    @Test
    public void validateTransactionRequestDto_whenValidCsvTransactionData_returnsTrue() {
        transactionProcessorService.validateTransactionRequestDto(getTransactionRequestDto(getValidCsvTransactions()));
    }

    @Test(expected = InvalidTransactionException.class)
    public void validateTransactionRequestDto_whenTransactionDataNull_throwsInvalidTransactionException() {
        transactionProcessorService.validateTransactionRequestDto(new TransactionRequestDto());
    }

    @Test
    public void validateTransactionRequestDto_whenTransactionDataEmpty_returnsTransactionCannotBeEmptyMessage() {
        try {
            transactionProcessorService.validateTransactionRequestDto(getTransactionRequestDto(getNullCsvTransactions()));
        } catch (InvalidTransactionException e) {
            assertThat(e.getLocalizedMessage()).isEqualTo("Transaction Cannot be Empty");
        }

    }

    @Test
    public void convertByteArrayToInputStream_whenValidBytes_returnsTrue() {
        byte[] bytes = {1, 3, 4, 5};
        assertThat(transactionProcessorService.convertByteArrayToInputStream(bytes))
                .isNotNull();
    }

    @Test
    public void getTransactionsTest_whenValidXmlTransactions_returnsTrueForNotEmpty() {
        List<Transaction> transactions = transactionProcessorService
                .getTransactions(getTransactionRequestDto(getValidXmlTransactions())
                        , transactionProcessorXml);
        assertThat(transactions).isNotEmpty();
    }

    @Test
    public void getTransactionsTest_whenValidXmlTransactions_returnsTrueForSize() {
        List<Transaction> transactions = transactionProcessorService
                .getTransactions(getTransactionRequestDto(getValidXmlTransactions())
                        , transactionProcessorXml);
        assertThat(transactions).hasSameSizeAs(getTransactionsDummy());
    }

    @Test
    public void getTransactionsTest_whenValidXmlTransactions_returnsTrueForElements() {
        List<Transaction> transactions = transactionProcessorService
                .getTransactions(getTransactionRequestDto(getValidXmlTransactions())
                        , transactionProcessorXml);
        assertThat(transactions.get(0).getType()).isEqualTo(getTransactionsDummy().get(0).getType());
        assertThat(transactions.get(1).getNarration()).isEqualTo(getTransactionsDummy().get(1).getNarration());
        assertThat(transactions.get(2).getAmount()).isEqualTo(getTransactionsDummy().get(2).getAmount());
    }

    @Test(timeout = 1000)
    public void getTransactionsTest_whenValidXmlTransactions_returnsTrueForLessThanASecond() {
        transactionProcessorService
                .getTransactions(getTransactionRequestDto(getValidXmlTransactions())
                        , transactionProcessorXml);
    }

    @Test(expected = InvalidTransactionException.class)
    public void getTransactionsTest_whenEmptyXmlTransactionData_returnsTrueForInvalidTransactionException() {
        transactionProcessorService
                .getTransactions(getTransactionRequestDto(getNullXmlTransactions())
                        , transactionProcessorXml);
    }

    @Test
    public void getTransactionsTest_whenNullXmlTransactionData_returnsTrueForErrorMessage() {
        final String expectedErrorMessage = "Bad request.";
        String actualErrorMessage = null;
        try {
            transactionProcessorService
                    .getTransactions(new TransactionRequestDto()
                            , transactionProcessorXml);
        } catch (InvalidTransactionException e) {
            actualErrorMessage = e.getLocalizedMessage();
        }
        assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage);
    }


}
