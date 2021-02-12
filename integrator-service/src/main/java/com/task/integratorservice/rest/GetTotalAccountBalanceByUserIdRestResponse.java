package com.task.integratorservice.rest;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Developed by P G R Asanka - 901833109V
 */

@Getter
@Setter
public class GetTotalAccountBalanceByUserIdRestResponse extends CommonRestResponse {

    private String userId;

    private String accountDetails;

    private BigDecimal totalBalanceForUser;

}
