package org.example.springstudy.controller;

import org.example.springstudy.exceptions.InvalidTransactionTypeException;
import org.example.springstudy.service.BankService;
import org.springframework.web.bind.annotation.*;
import org.example.springstudy.dto.TransactionRequestDTO;
/***
 * What is Controller?
 * Controller Handles user interactions (HTTP Requests).
 * It decides what needs to be done based on the requests.
 * Acts as a bridge between the user and the application logic.
 */
//Makes this class a REST API controller
// Rest Controller returns data in form of JSON or XML as HTTP Response.
@RestController
@RequestMapping("/bank")
public class BankController {
    //Holds reference to the BankService bean. Dependency Injection at work.
    private final BankService bankService;

    //Constructor Injection
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }
    // Handles GET requests to check the balance
    // Any client (postman/front-end application) that sends GET request to url path /balance will trigger this method.
    @GetMapping("/balance")
    public String getBalance() {
        return "Your current balance is: $" + bankService.getBalance();
    }
//    //Handles GET requests to deposit money.
//    //RequestParam: Extracts the amount parameter from the request URL. Request Param makes the parameter mandatory, can make it required=false.
//    @GetMapping("/deposit")
//    public String deposit(@RequestParam double amount) {
//        return bankService.deposit(amount);
//    }

    //The controller's throws declaration doesn't mean the controller throws the exception itself, it simply propagates exceptions from the service layer. Key part in Spring Application
    @PostMapping("/transaction")
    public String processTransaction(@RequestBody TransactionRequestDTO requestDTO) throws InvalidTransactionTypeException {
        return bankService.processTransaction(requestDTO);

    }




    @GetMapping("/history")
    public String getTransactions() {

        return bankService.getTransactionHistory();
    }
}
