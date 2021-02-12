package com.task.integratorservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Developed by P G R Asanka - 901833109V
 */

@Entity
@Table(name = "integrator_service_audit")
@Getter
@Setter
public class IntegratorServiceAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String requestType;

    private String requestMethod;

    private String balanceInquiredAccountNo;

    private String fundTransferType;

    private String userId;

    private String fromAccountNO;

    private String toAccountNO;

    private BigDecimal amount;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant requestedDate;


}
