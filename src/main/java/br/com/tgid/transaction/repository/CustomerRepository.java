package br.com.tgid.transaction.repository;

import br.com.tgid.transaction.model.Customer;
import br.com.tgid.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    Optional<Customer> findById(Long id);
}
