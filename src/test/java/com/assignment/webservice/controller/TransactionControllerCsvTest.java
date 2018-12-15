package com.assignment.webservice.controller;

import com.assignment.webservice.TransactionProcessorAbstract;
import com.assignment.webservice.constants.WebResourceURIConstants;
import com.assignment.webservice.dto.ErrorResponse;
import com.assignment.webservice.dto.TransactionRequestDto;
import com.assignment.webservice.model.Transaction;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Suraj Gautam.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerCsvTest extends TransactionProcessorAbstract {

    private static TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @BeforeClass
    public static void setup() {
        restTemplate = new TestRestTemplate();
    }

    @Test
    public void getCsvTransaction_givenValidCsvTransactionData_returnsStatusCode200() {
        final String URL = getFullUrl(WebResourceURIConstants.CSV_TRANSACTION_PROCESSOR, port);
        TransactionRequestDto transactionRequestDto = getTransactionRequestDto(getValidCsvTransactions());
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(URL,
                transactionRequestDto
                , Object.class);
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    public void getCsvTransaction_givenValidCsvTransactionData_returnsHttpStatusOk() {
        final String URL = getFullUrl(WebResourceURIConstants.CSV_TRANSACTION_PROCESSOR, port);
        TransactionRequestDto transactionRequestDto = getTransactionRequestDto(getValidCsvTransactions());
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(URL,
                transactionRequestDto
                , Object.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    public void getCsvTransaction_givenTransactionDataEmptyWhenInputStreamIsNull_returnsStatusCode400() {
        final String URL = getFullUrl(WebResourceURIConstants.CSV_TRANSACTION_PROCESSOR, port);
        TransactionRequestDto transactionRequestDto = getTransactionRequestDto(getNullCsvTransactions());
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(URL,
                transactionRequestDto
                , Object.class);
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(400);
    }

    @Test
    public void getCsvTransaction_givenTransactionDataEmptyWhenInputStreamIsNull_returnsHttpStatusBadRequest() {
        final String URL = getFullUrl(WebResourceURIConstants.CSV_TRANSACTION_PROCESSOR, port);
        TransactionRequestDto transactionRequestDto = getTransactionRequestDto(getNullCsvTransactions());
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(URL,
                transactionRequestDto
                , Object.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getCsvTransaction_givenTransactionDataEmptyWhenInputStreamIsNull_returnsTransactionCannotBeEmptyErrorMessage() {
        final String URL = getFullUrl(WebResourceURIConstants.CSV_TRANSACTION_PROCESSOR, port);
        final String EXPECTED_ERROR_MESSAGE = "Transaction Cannot be Empty";
        TransactionRequestDto transactionRequestDto = getTransactionRequestDto(getNullCsvTransactions());
        ResponseEntity<ErrorResponse> responseEntity = restTemplate.postForEntity(URL,
                transactionRequestDto
                , ErrorResponse.class);
        ErrorResponse errorResponse = responseEntity.getBody();
        assertThat(errorResponse.getErrorMessage()).isEqualTo(EXPECTED_ERROR_MESSAGE);
    }

    @Test
    public void getCsvTransaction_givenRequestBodyNull_returnsHttpStatus415() {
        final String URL = getFullUrl(WebResourceURIConstants.CSV_TRANSACTION_PROCESSOR, port);
        TransactionRequestDto transactionRequestDto = null;
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(URL,
                transactionRequestDto
                , Object.class);
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(415);
    }

    @Test
    public void getCsvTransaction_givenNullTransactionData_returnsBadRequestErrorMessage() {
        final String URL = getFullUrl(WebResourceURIConstants.CSV_TRANSACTION_PROCESSOR, port);
        final String EXPECTED_ERROR_MESSAGE = "Bad request.";
        TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
        ResponseEntity<ErrorResponse> responseEntity = restTemplate.postForEntity(URL,
                transactionRequestDto
                , ErrorResponse.class);
        ErrorResponse errorResponse = responseEntity.getBody();
        assertThat(errorResponse.getErrorMessage()).isEqualTo(EXPECTED_ERROR_MESSAGE);
    }

    @Test
    public void getCsvTransaction_givenValidCsvTransactionData_returnsSizeThree() {
        final String URL = getFullUrl(WebResourceURIConstants.CSV_TRANSACTION_PROCESSOR, port);
        TransactionRequestDto transactionRequestDto = getTransactionRequestDto(getValidCsvTransactions());
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(URL,
                transactionRequestDto
                , Object.class);
        List<Transaction> transactions = (List<Transaction>) responseEntity.getBody();
        assertThat(transactions.size()).isEqualTo(3);
    }

    @Test
    public void getCsvTransaction_givenValidCsvTransactionData_returnsExpectedList() {
        final String URL = getFullUrl(WebResourceURIConstants.CSV_TRANSACTION_PROCESSOR, port);
        TransactionRequestDto transactionRequestDto = getTransactionRequestDto(getValidCsvTransactions());
        ResponseEntity<Transaction[]> responseEntity = restTemplate.postForEntity(URL,
                transactionRequestDto
                , Transaction[].class);
        assertThat(responseEntity.getBody()[1].getAmount()).isEqualTo(getTransactionsDummy().get(1).getAmount());
        assertThat(responseEntity.getBody()[0].getNarration()).isEqualTo(getTransactionsDummy().get(0).getNarration());
        assertThat(responseEntity.getBody()[2].getType()).isEqualTo(getTransactionsDummy().get(2).getType());
    }



}
