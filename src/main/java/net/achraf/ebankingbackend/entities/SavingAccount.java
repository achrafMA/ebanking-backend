package net.achraf.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("SA")
@Data @NoArgsConstructor @AllArgsConstructor

public class SavingAccount extends BankAccount{
    private double interestRate;
}
