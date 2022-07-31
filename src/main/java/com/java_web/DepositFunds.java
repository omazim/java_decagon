package com.java_web;

import org.json.JSONObject;

public class DepositFunds {

  public static TransactionResult runDeposits (JSONObject json, short count) {

    final TransactionResult txnResult = new TransactionResult(); 
    final String accountNumberKey = TransactionDetailsKeys.accountNumber.name();
    final String accountNumber = (String) json.get(accountNumberKey);
      
    txnResult.accountNumber = accountNumber;
    
    for (int i = 0; i < count; i++) {

      final int counter = i + 1;
      final String amountKey = TransactionDetailsKeys.amount.name();
      
      final double amount = Double.parseDouble((String) json.get(amountKey));
      final double adjustedAmount = (double) amount * counter;
      // System.out.println("new amount => " + adjustedAmount);
      TransactionManager txnManager = new TransactionManager(accountNumber);
      
      if (txnManager.deposit(adjustedAmount)) {
        txnResult.amounts.add(adjustedAmount);
      };
    }
    // Assign the current total balance on the account from the vault manager.
    txnResult.balance = VaultManager.accountBalancesMap.get(accountNumber);
    
    return txnResult;
  }
}