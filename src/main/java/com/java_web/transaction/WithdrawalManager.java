package com.java_web.transaction;

import java.util.ArrayList;

import org.json.JSONObject;

public class WithdrawalManager extends TransactionManager {

  public WithdrawalManager (String accountNumber) {
    super(accountNumber);
  }

  public ArrayList<TransactionLog> runWithdrawal (JSONObject json, short count) {
    
    for (int i = 0; i < count; i++) {

      final int counter = i + 1;
      final String amountKey = TransactionDetailsKeys.amount.name();      
      final double amount = json.getDouble(amountKey);
      final double adjustedAmount = (double) amount * counter;
      
      withdraw(adjustedAmount);
    }    
    
    return this.withdrawalHistory();
  }
}