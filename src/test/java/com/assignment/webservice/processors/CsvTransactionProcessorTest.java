package com.assignment.webservice.processors;

import com.assignment.webservice.TransactionProcessorAbstract;
import com.assignment.webservice.enums.FileTypeEnum;
import com.assignment.webservice.factory.TransactionProcessorFactory;
import com.assignment.webservice.model.TransactionProcessor;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Suraj Gautam.
 */
public class CsvTransactionProcessorTest extends TransactionProcessorAbstract {

    private static TransactionProcessor csvTransactionProcessor;

    @BeforeClass
    public static void setUp() {
        csvTransactionProcessor = TransactionProcessorFactory.getInstance(FileTypeEnum.CSV);
    }

    @Test
    public void getImportedTransactions_whenValidCsvInputStream_returnsListSizeThreeTrue() {
        csvTransactionProcessor.importTransactions(getValidCsvTransactions());
        assertThat(csvTransactionProcessor.getImportedTransactions())
                .hasSize(3);
    }

    @Test(expected = NullPointerException.class)
    public void getImportedTransactions_whenNullCsvInputStream_throwsNullPointerException() {
        csvTransactionProcessor.importTransactions(null);
        csvTransactionProcessor.getImportedTransactions();
    }

}
