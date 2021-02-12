package com.task.dcbs.repository;

import com.task.dcbs.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Developed by P G R Asanka - 901833109V
 */

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByAccountNo(String accountNo);

    List<AccountEntity> findByUserId(String userId);

}
