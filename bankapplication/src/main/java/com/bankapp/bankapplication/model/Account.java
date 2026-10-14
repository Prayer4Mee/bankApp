package com.bankapp.bankapplication.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="account")
@Data
@AllArgsConstructor 
@NoArgsConstructor 

public class Account {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    private String name;

    private BigDecimal balance;

    @CreationTimestamp 
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp 
    @Column(name="updated_at")
	private LocalDateTime updatedAt;
    
}
