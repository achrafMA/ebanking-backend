package net.achraf.ebankingbackend.repositories;

import net.achraf.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
    List<Customer> searchCustomer(String keyword);
}
