package net.me.ebanking.repository;

import net.me.ebanking.entitie.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {

    List<AccountOperation> findByBankAccount_Id(Long bankAccountId);
    Page<AccountOperation> findByBankAccount_Id(Long bankAccountId, Pageable pageable);
}
