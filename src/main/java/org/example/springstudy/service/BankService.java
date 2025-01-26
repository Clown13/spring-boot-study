package org.example.springstudy.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.example.springstudy.repository.BankRepo;
import org.springframework.stereotype.Service;
import org.example.springstudy.dto.TransactionRequestDTO;
import org.example.springstudy.exceptions.InvalidTransactionTypeException;

/***
 *Service contains the business logic of the application. It decides how tasks are performed.
 * It is independent of how request is initiated.
 * Service Layer can be reused across different controllers or even other components.
 */
//Marks this class as a Spring-managed service.
//This class will be used for banking operation like checking the balance and depositing money.
@Service
public class BankService {
    private final BankRepo bankRepo;
    //initiating original balance
    private double balance = 1000.00;

    public BankService(BankRepo bankRepo) {
        this.bankRepo = bankRepo;
    }

    // these annotations are part of lifecycle hooks for beans.
    //Often used for post-init tasks such as setting up resources/ logging/ validating configuration.
    @PostConstruct
    public void init() {
        System.out.println("Bank service started");
    }
    //Ran just before bean is destroyed by Spring container. For releasing resources, closing db connections, logging shutdown messages.
    @PreDestroy
    public void cleanUp() {
        System.out.println("Bank service stopped");

    }

    //Let's practice again for Encapsulation - Setters and Getters.

    public double getBalance() {
        return balance;
    }
//    public String deposit(double amount) {
//        if (amount <= 0 ) {
//            return "Invalid amount";
//        }
//        balance = balance + amount;
//        bankRepo.addTransaction("Deposited: " + amount);
//        return "Deposit successful. New Balance: " + balance;
//    }
    public String processTransaction(TransactionRequestDTO requestDTO) throws InvalidTransactionTypeException {
        double amount = requestDTO.getAmount();
        String type = requestDTO.getType();

        if (amount<= 0) {
            return "Invalid amount";

        }
        if (type.equals("DEPOSIT")) {
            balance += amount;
            bankRepo.addTransaction("Deposited: " + amount);
            return "Deposit successful. New balance: " + balance;
        }
        else if (type.equals("WITHDRAW")) {
            if (amount > balance) {
                return "Insufficient funds";
            }
            balance -= amount;
            bankRepo.addTransaction("Withdrawn: " + amount);
            return "Withdraw successful. New balance: " + balance;

        }
        else {
            throw new InvalidTransactionTypeException("Transaction type not supported: " + type);
        }
    }

    public String getTransactionHistory() {

        return String.join(", ", bankRepo.getTransactionHistory());
    }

}
