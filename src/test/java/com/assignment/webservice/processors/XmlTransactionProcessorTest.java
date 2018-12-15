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
public class XmlTransactionProcessorTest extends TransactionProcessorAbstract {

    private static TransactionProcessor xmlTransactionProcessor;

    @BeforeClass
    public static void setUp() {
        xmlTransactionProcessor = TransactionProcessorFactory.getInstance(FileTypeEnum.XML);
    }

    @Test
    public void getImportedTransactions_whenValidXmlInputStream_returnsListSizeThreeTrue() {
        xmlTransactionProcessor.importTransactions(getValidXmlTransactions());
        assertThat(xmlTransactionProcessor.getImportedTransactions())
                .hasSize(3);
    }
}
