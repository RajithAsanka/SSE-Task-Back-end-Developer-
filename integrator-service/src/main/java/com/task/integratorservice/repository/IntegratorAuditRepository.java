package com.task.integratorservice.repository;


import com.task.integratorservice.model.IntegratorServiceAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Developed by P G R Asanka - 901833109V
 */

@Repository
public interface IntegratorAuditRepository extends JpaRepository<IntegratorServiceAudit, Long> {


}
