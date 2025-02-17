package net.me.ebanking.repository;

import net.me.ebanking.entitie.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT c FROM Customer c WHERE c.name LIKE %:keyword%")
    List<Customer> searchCustomer(@Param("keyword") String keyword);

}
