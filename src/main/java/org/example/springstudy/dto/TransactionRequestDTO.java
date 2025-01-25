package org.example.springstudy.dto;
//DTO represents teh structure of data transferred between the client and the server.

/**
 * Data Transfer Object for handling transaction requests.
 * Represents the structure of the data being received or sent between the client and the server.
 * This ensures that incoming JSON data is mapped into a structured java object that the application can use.
 */
public class TransactionRequestDTO {
    private double amount; // Amount for deposit or withdrawal.
    private String type; //Transaction type Deposit or Withdrawal.

    //Getters and Setters.
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

}

//DTO will not be treated as Spring managed bean because its purely data carrier that holds incoming request data.
//Spring bean is singleton by default, meaning one instance of the bean is shared across application, DTO is meant to be unique for each request.