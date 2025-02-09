package net.me.ebanking.entitie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.me.ebanking.enums.AccountStatus;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length =4 )
@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String accountNumber;
    private String currency;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "bankAccount")
    private List<AccountOperation> accountOperations;
}
