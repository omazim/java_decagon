package com.java_web.transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionLog {

  public TransactionLog () {}

  public TransactionLog (String accountNumber, double amount, double balance) {
  
    this.accountNumber = accountNumber;
    this.amount = amount;
    this.runningBalance = balance;
    
    final LocalDateTime createdAt = LocalDateTime.now();
    // this.createdAt = LocalDateTime.now();
    final String datePattern = "dd-MMM-yyyy HH:mm:ss";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

    final String formattedDate = createdAt.format(formatter);
    
    this.formattedDate = formattedDate;
  }

  public String accountNumber;
  public double amount;  
  public String formattedDate;
  public double runningBalance;
}
