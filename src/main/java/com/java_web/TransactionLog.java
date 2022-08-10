package com.java_web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionLog {

  public TransactionLog () {}

  public TransactionLog (String accountNumber, double amount, double balance) {
  
    this.accountNumber = accountNumber;
    this.amount = amount;
    this.runningBalance = balance;
    this.createdAt = LocalDateTime.now();

    final String datePattern = "dd-MMM-yyyy HH:mm:ss";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

    String formattedDate = this.createdAt.format(formatter);
    this.formattedDate = formattedDate;
  }

  public String accountNumber;
  public double amount;
  private LocalDateTime createdAt;
  public String formattedDate;
  public double runningBalance;
}
