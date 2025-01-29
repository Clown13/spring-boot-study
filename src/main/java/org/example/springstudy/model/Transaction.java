package org.example.springstudy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 * JPA Entity Class to represent the Transaction table.
 */
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    private double amount; // Transaction amount
    private String type;   // "DEPOSIT" or "WITHDRAW"
    private LocalDateTime timestamp; // Time of the transaction

    // Default Constructor (Required by JPA)
    public Transaction() {}

    // Parameterized Constructor
    public Transaction(double amount, String type) {
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now(); // Set the timestamp to the current time
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}