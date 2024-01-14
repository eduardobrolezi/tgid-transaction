package br.com.tgid.transaction.model;

import br.com.tgid.transaction.model.dto.TransactionRequest;
import br.com.tgid.transaction.model.dto.TransactionResponse;
import br.com.tgid.transaction.model.enums.TransactionTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
public class Transaction {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "customer_id")
   private Customer customer;
   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "enterprise_id")
   private Enterprise enterprise;
   private BigDecimal value;
   @Enumerated(EnumType.STRING)
   @Column(name = "type")
   private TransactionTypeEnum typeEnum;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Customer getCustomer() {
      return customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   public Enterprise getEnterprise() {
      return enterprise;
   }

   public void setEnterprise(Enterprise enterprise) {
      this.enterprise = enterprise;
   }

   public BigDecimal getValue() {
      return value;
   }

   public void setValue(BigDecimal value) {
      this.value = value;
   }

   public TransactionTypeEnum getTypeEnum() {
      return typeEnum;
   }

   public void setTypeEnum(TransactionTypeEnum typeEnum) {
      this.typeEnum = typeEnum;
   }

   public static Transaction toModel(TransactionRequest transactionRequest, Customer customer, Enterprise enterprise) {
      Transaction transaction = new Transaction();
      transaction.setCustomer(customer);
      transaction.setEnterprise(enterprise);
      transaction.setValue(transactionRequest.value());
      transaction.setTypeEnum(transactionRequest.typeEnum());
      return transaction;

   }
   public TransactionResponse toDto(){
        return new TransactionResponse(this.value, this.typeEnum);
   }
}
