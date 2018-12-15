package com.assignment.webservice.factory;

import com.assignment.webservice.enums.FileTypeEnum;
import com.assignment.webservice.model.TransactionProcessor;
import com.assignment.webservice.processors.CsvTransactionProcessor;
import com.assignment.webservice.processors.XmlTransactionProcesser;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Suraj Gautam.
 */
public class TransactionProcessorFactoryTest {

    @Test
    public void getInstance_whenFileTypeEnumXml_returnsXmlTransactionProcessorInstanceTrue() {
        TransactionProcessor transactionProcessor = TransactionProcessorFactory.getInstance(FileTypeEnum.XML);
        assertThat(transactionProcessor).isInstanceOf(XmlTransactionProcesser.class);
    }

    @Test
    public void getInstance_whenFileTypeEnumXml_returnsNotCsvTransactionProcessorInstanceTrue() {
        TransactionProcessor transactionProcessor = TransactionProcessorFactory.getInstance(FileTypeEnum.XML);
        assertThat(transactionProcessor).isNotInstanceOf(CsvTransactionProcessor.class);
    }

    @Test
    public void getInstance_whenFileTypeEnumXml_returnsCsvTransactionProcessorInstanceTrue() {
        TransactionProcessor transactionProcessor = TransactionProcessorFactory.getInstance(FileTypeEnum.CSV);
        assertThat(transactionProcessor).isInstanceOf(CsvTransactionProcessor.class);
    }

    @Test
    public void getInstance_whenFileTypeEnumXml_returnsNotXmlProcessorInstanceTrue() {
        TransactionProcessor transactionProcessor = TransactionProcessorFactory.getInstance(FileTypeEnum.CSV);
        assertThat(transactionProcessor).isNotInstanceOf(XmlTransactionProcesser.class);
    }


}
