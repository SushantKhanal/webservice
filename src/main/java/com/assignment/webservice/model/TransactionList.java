package com.assignment.webservice.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suraj Gautam.
 */
@XmlRootElement(name = "TransactionList")
public class TransactionList {

    private List<Transaction> transaction = new ArrayList<>();

    public List<Transaction> getTransaction() {
        return transaction;
    }

    @XmlElement(name = "Transaction")
    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }
}
