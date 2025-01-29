package org.example.springstudy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

/**
 * JPA Entity Class to represent the Account table
 */
//Entity Annotation, marks the class as a JPA entity that maps to a database table.
//Table Annotation, Specifies the name of the database table
//@ID: Marks the id field as primary key
//@GeneratedValue: Specifies how the primary key should be generated (auto-increment).

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id; // This is the primary Key.
    private String name;
    private Double balance;

    //Default Constructor. Required by JPA to instantiate the entity.
    public Account() {}

    //Parameterized Constructor.
    public Account(String name, Double balance) {
        this.name = name;
        this.balance = balance;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }


}
