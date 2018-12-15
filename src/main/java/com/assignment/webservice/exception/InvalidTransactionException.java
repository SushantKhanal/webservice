package com.assignment.webservice.exception;

/**
 * Created by Suraj Gautam.
 */

/**
 * Exception generated whenever there is no any transaction
 */
public class InvalidTransactionException extends RuntimeException {


    public InvalidTransactionException(String message) {
        super(message);
    }

}
