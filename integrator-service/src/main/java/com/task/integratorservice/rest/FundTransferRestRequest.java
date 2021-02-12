package com.task.integratorservice.rest;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Developed by P G R Asanka - 901833109V
 */

@Getter
@Setter
public class FundTransferRestRequest {

    private String fundTransferType;

    private String userId;

    private String fromAccountNO;

    private String toAccountNO;

    private BigDecimal amount;

}
