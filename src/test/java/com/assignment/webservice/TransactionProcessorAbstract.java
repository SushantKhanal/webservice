package com.assignment.webservice;

import com.assignment.webservice.dto.TransactionRequestDto;
import com.assignment.webservice.model.Transaction;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Suraj Gautam.
 */
public class TransactionProcessorAbstract {


    protected InputStream asStream(String s) {
        return new ByteArrayInputStream(s.getBytes());
    }

    protected TransactionRequestDto getTransactionRequestDto(InputStream inputStream) {
        TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
        try {
            transactionRequestDto.setTransactionData(getByteArrayFromInputStream(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionRequestDto;
    }

    protected byte[] getByteArrayFromInputStream(InputStream inputStream) throws IOException {
        return StreamUtils.copyToByteArray(inputStream);
    }

    protected String getFullUrl(String resource, int port) {
        return "http://localhost:" + port + resource;
    }

    protected List<Transaction> getTransactionsDummy() {
        return Arrays.asList(
                new Transaction("C", new BigDecimal(1000), "salary"),
                new Transaction("D", new BigDecimal(200), "rent"),
                new Transaction("D", new BigDecimal(800), "other")
        );
    }

    protected InputStream getValidCsvTransactions() {
        InputStream is = asStream("C,1000,salary\nD,200,rent\nD,800,other");
        return is;
    }

    protected InputStream getNullCsvTransactions() {
        return null;
    }


    protected InputStream getValidXmlTransactions() {
        InputStream is = asStream("<TransactionList>\n" +
                "    <Transaction type=\"C\" amount=\"1000\" narration=\"salary\" />\n" +
                "    <Transaction type=\"D\" amount=\"200\" narration=\"rent\" />\n" +
                "    <Transaction type=\"D\" amount=\"800\" narration=\"other\" />\n" +
                "</TransactionList>");
        return is;
    }

    protected InputStream getNullXmlTransactions() {
        return null;
    }

}
