package net.achraf.ebankingbackend.repositories;

import net.achraf.ebankingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepo extends JpaRepository<BankAccount,String> {
}
