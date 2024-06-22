package net.achraf.ebankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("CA")
@Data @NoArgsConstructor @AllArgsConstructor

public class CurrentAccount extends BankAccount{
    private double overDraft;
}
