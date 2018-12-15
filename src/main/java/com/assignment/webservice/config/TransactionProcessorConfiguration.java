package com.assignment.webservice.config;

import com.assignment.webservice.enums.FileTypeEnum;
import com.assignment.webservice.factory.TransactionProcessorFactory;
import com.assignment.webservice.model.TransactionProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Suraj Gautam.
 */
@Configuration
public class TransactionProcessorConfiguration {


    @Bean
    @Qualifier("xml")
    public TransactionProcessor getTransactionProcessorWhenXml(){
        return TransactionProcessorFactory.getInstance(FileTypeEnum.XML);
    }

    @Bean
    @Qualifier("csv")
    public TransactionProcessor getTransactionProcessorWhenCsv(){
        return TransactionProcessorFactory.getInstance(FileTypeEnum.CSV);
    }


}
