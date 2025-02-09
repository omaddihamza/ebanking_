package net.me.ebanking.entitie;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data @Builder
@DiscriminatorValue("SA")
public class SavingAccount extends BankAccount {
    private double interestRate;
}
