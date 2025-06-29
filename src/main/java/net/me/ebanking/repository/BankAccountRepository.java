package net.me.ebanking.repository;

import net.me.ebanking.entitie.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount getBankAccountsByAccountNumber(String accountNumber);
}
