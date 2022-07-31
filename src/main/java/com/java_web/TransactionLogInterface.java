package com.java_web;

import java.util.Date;

public interface TransactionLogInterface {
  // String accountNumber = "";
  double amount = 0.00;
  Date createdAt = new Date();
  boolean isCredit = false;
}
