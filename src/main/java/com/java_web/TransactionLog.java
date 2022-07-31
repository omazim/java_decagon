package com.java_web;

import java.time.LocalDateTime;

public class TransactionLog {

  public TransactionLog () {}

  public TransactionLog (String accountNumber, double amount, double balance) {
  
    this.accountNumber = accountNumber;
    this.amount = amount;
    this.runningBalance = balance;
    this.createdAt = LocalDateTime.now();
  }

  String accountNumber;
  double amount;
  LocalDateTime createdAt;
  double runningBalance;
}
