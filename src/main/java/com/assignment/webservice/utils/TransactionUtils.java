package com.assignment.webservice.utils;

import com.assignment.webservice.enums.FileTypeEnum;
import com.assignment.webservice.model.Transaction;
import com.assignment.webservice.model.TransactionList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.assignment.webservice.enums.FileTypeEnum.XML;

/**
 * Created by Suraj Gautam.
 */
public class TransactionUtils {

    private static final int TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int NARRATION_INDEX = 2;
    private static final String CSV_SEPARATOR = ",";

    private TransactionUtils() {
        throw new RuntimeException("Cannot instantiate Utils class");
    }

    public static List<Transaction> convertToTransaction(InputStream inputStream,
                                                         FileTypeEnum fileTypeEnum) {
        return
                fileTypeEnum == XML ? convertToTransactionWhenXML(inputStream) :
                        convertToTransactionWhenCSV(inputStream);
    }

    /**
     * @param inputStream which contains CSV
     * @return List of @{@link Transaction}
     */
    private static List<Transaction> convertToTransactionWhenCSV(InputStream inputStream) {
        List<Transaction> transactions = new ArrayList<>();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            String[] properties = scanner.nextLine().split(CSV_SEPARATOR);
            transactions.add(getTransaction(properties));
        }
        scanner.close();
        return transactions;
    }

    private static Transaction getTransaction(String[] properties) {
        Transaction transaction = new Transaction();
        transaction.setType(properties[TYPE_INDEX]);
        try {
            transaction.setAmount(new BigDecimal(properties[AMOUNT_INDEX]));
        } catch (NumberFormatException ex) {
            transaction.setAmount(BigDecimal.ZERO);
        }
        transaction.setNarration(properties[NARRATION_INDEX]);
        return transaction;
    }

    private static List<Transaction> convertToTransactionWhenXML(InputStream inputStream) {
        return unmarshallToTransaction(inputStream);
    }

    private static List<Transaction> unmarshallToTransaction(InputStream inputStream) {
        TransactionList transactions = new TransactionList();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TransactionList.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            transactions = (TransactionList) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return transactions.getTransaction();
    }

}
