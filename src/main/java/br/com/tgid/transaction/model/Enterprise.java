package br.com.tgid.transaction.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.hibernate.validator.constraints.br.CNPJ;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CNPJ
    private String cnpj;
    @OneToMany
    private List<Tax> taxes;
    private BigDecimal balance;


}
