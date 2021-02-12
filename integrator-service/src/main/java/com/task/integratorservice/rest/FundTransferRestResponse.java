package com.task.integratorservice.rest;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Developed by P G R Asanka - 901833109V
 */

@Getter
@Setter
public class FundTransferRestResponse extends CommonRestResponse {


    private String debitUserId;

    private String debitAccountNo;

    private BigDecimal debitAccountBalance;

    private String creditUserId;

    private String creditAccountNo;

    private BigDecimal creditAccountBalance;

}
