package com.task.dcbs.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Developed by P G R Asanka - 901833109V
 */

@Entity(name = "account")
@Getter
@Setter
public class AccountEntity {

    @Id
    @GeneratedValue
    private Long accountId;

    private String userId;

    private String accountNo;

    private BigDecimal balance;


}


