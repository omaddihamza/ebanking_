package net.me.ebanking.repository;

import net.me.ebanking.entitie.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
