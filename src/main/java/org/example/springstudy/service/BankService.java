package org.example.springstudy.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.example.springstudy.exceptions.InsufficientFundsException;
import org.example.springstudy.repository.BankRepo;
import org.springframework.stereotype.Service;
import org.example.springstudy.dto.TransactionRequestDTO;
import org.example.springstudy.exceptions.InvalidTransactionTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//Logger Info: General Flow of the application, Logger WARN: Unexpected condition, but application can cont., Logger ERROR: severe issue, requires attention.
/***
 *Service contains the business logic of the application. It decides how tasks are performed.
 * It is independent of how request is initiated.
 * Service Layer can be reused across different controllers or even other components.
 */
//Marks this class as a Spring-managed service.
//This class will be used for banking operation like checking the balance and depositing money.
@Service
public class BankService {
    private static final Logger logger = LoggerFactory.getLogger(BankService.class);
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
        logger.info("BankService init started");
    }
    //Ran just before bean is destroyed by Spring container. For releasing resources, closing db connections, logging shutdown messages.
    @PreDestroy
    public void cleanUp() {
        logger.info("BankService clean up started");
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
    public String processTransaction(TransactionRequestDTO requestDTO) throws InvalidTransactionTypeException, InsufficientFundsException, IllegalArgumentException {
        double amount = requestDTO.getAmount();
        String type = requestDTO.getType();

        if (amount<= 0) {
            logger.warn("Invalid amount provided");
            throw new IllegalArgumentException("Amount must be greater than 0");

        }
        if (type.equals("DEPOSIT")) {
            balance += amount;
            bankRepo.addTransaction("Deposited: " + amount);
            logger.info("Deposited: " + amount);
            return "Transaction Successful - Deposited New balance: " + balance;
        }
        else if (type.equals("WITHDRAW")) {
            if (amount > balance) {
                logger.warn("Transaction Failed- Invalid amount provided");
                throw new InsufficientFundsException("Insufficient funds, available balance: " + balance);
            }
            balance -= amount;
            bankRepo.addTransaction("Withdrawn: " + amount);
            logger.info("Transaction Successful - Withdrawn: " + amount);
            return "Withdraw successful. New balance: " + balance;

        }
        else {
            logger.error("Invalid type provided");
            throw new InvalidTransactionTypeException("Transaction type not supported: " + type);
        }
    }

    public String getTransactionHistory() {
        logger.info("BankService getTransactionHistory started");
        return String.join(", ", bankRepo.getTransactionHistory());
    }

}
