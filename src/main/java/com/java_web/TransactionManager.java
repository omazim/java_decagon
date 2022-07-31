package com.java_web;

import java.util.ArrayList;

public class TransactionManager extends VaultManager {
  
  public TransactionManager () {}

  public TransactionManager (String accountNumber) {
    
    super(accountNumber);
  }
  
  public boolean deposit (double amount) {

    boolean deposited = false;

    try {
      this.updateBalance(amount);

      deposited = true;
    } catch (Exception ex) {
      System.out.println();
    }
    return deposited;
  }

  public boolean withdraw (double amount) {

    boolean withdrawn = false;

    try {
      this.updateBalance((amount * -1));

      withdrawn = true;
    } catch (Exception ex) {
      System.out.println();
    }
    return withdrawn;
  }

  public ArrayList<TransactionLog> history () {

    System.out.println("getting history for " + accountNumber);
    if (this.accountNumber == null) {

      return this.allTransactions();
    } else {
      return this.transactionLogs();
    }    
  }

  private ArrayList<TransactionLog> allTransactions () {
    
    return new ArrayList<TransactionLog>();
  }

  private ArrayList<TransactionLog> transactionLogs () {
    
    final ArrayList<TransactionLog> txnLogs = new ArrayList<TransactionLog>();
    
    for (TransactionLog ledger: VaultManager.ledger) {
      // System.out.println(ledger);
      
      final boolean pass = ledger.accountNumber.equals(accountNumber);

      if (pass) txnLogs.add(ledger);      
    }

    return txnLogs;
  }
}
