package com.assignment.webservice.factory;

import com.assignment.webservice.enums.FileTypeEnum;
import com.assignment.webservice.model.TransactionProcessor;
import com.assignment.webservice.processors.CsvTransactionProcessor;
import com.assignment.webservice.processors.XmlTransactionProcesser;

/**
 * Created by Suraj Gautam.
 */
public class TransactionProcessorFactory {

    public static TransactionProcessor getInstance(FileTypeEnum fileTypeEnum) {

        return (fileTypeEnum == FileTypeEnum.XML) ?
                new XmlTransactionProcesser() :
                new CsvTransactionProcessor();
    }

}
