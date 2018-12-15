package com.assignment.webservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by Suraj Gautam.
 */
@Getter
@Setter
@ToString
public class TransactionRequestDto implements Serializable {
    private byte[] transactionData;
}
