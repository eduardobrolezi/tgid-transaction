package br.com.tgid.transaction.repository;

import br.com.tgid.transaction.model.Customer;
import br.com.tgid.transaction.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
