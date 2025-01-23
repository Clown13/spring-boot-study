package org.example.springstudy.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
// Spring managed bean responsible for data access. This is to simulate storing and retrieving transaction history. Data access layer component.
@Repository
public class BankRepo {
    private final List<String> transactionHistory = new ArrayList<>();

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
        System.out.println("Transaction Saved" + transaction);
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}
//We will inject BankRepository data into BankService using Dependency Injection.