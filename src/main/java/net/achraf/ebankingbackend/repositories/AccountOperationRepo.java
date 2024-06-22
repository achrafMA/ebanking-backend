package net.achraf.ebankingbackend.repositories;

import net.achraf.ebankingbackend.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepo extends JpaRepository<AccountOperation,Long> {
}
