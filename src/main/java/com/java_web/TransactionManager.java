package com.java_web;

import java.util.ArrayList;

public class TransactionManager extends AccountManager {
  
  public TransactionManager () {}

  public TransactionManager (String accountNumber) {
    
    super(accountNumber);
  }
  
  public boolean deposit (double amount) {

    boolean deposited = false;

    if (!this.isValidAccount()) return deposited;

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

    if (!this.isValidAccount()) return withdrawn;

    try {
      this.updateBalance((amount * -1));

      withdrawn = true;
    } catch (Exception ex) {
      System.out.println();
    }
    return withdrawn;
  }

  public ArrayList<TransactionLog> history () {

    // System.out.println("getting history for " + accountNumber);
    final boolean useAccountFilter = this.accountNumber == null;

    return this.transactionLogs(TransactionTypesEnum.ALL, useAccountFilter);
  }

  public ArrayList<TransactionLog> depositHistory () {

    return this.transactionLogs(TransactionTypesEnum.DEPOSIT, true); 
  }

  public ArrayList<TransactionLog> withdrawalHistory () {

    return this.transactionLogs(TransactionTypesEnum.WITHDRAWAL, true); 
  }

  private ArrayList<TransactionLog> transactionLogs (TransactionTypesEnum type, boolean useAccountFilter) {
    
    final ArrayList<TransactionLog> txnLogs = new ArrayList<TransactionLog>();
    
    for (TransactionLog ledger: VaultManager.ledger) {
      
      // Determine filtering for a specific account.
      final boolean passFilter = useAccountFilter ? ledger.accountNumber.equals(accountNumber): !useAccountFilter;
      
      if (!passFilter) continue;

      switch (type) {
        case DEPOSIT: 
          if (ledger.amount > 0.00) txnLogs.add(ledger);  
          break;
        case WITHDRAWAL: 
          if (ledger.amount < 0.00) txnLogs.add(ledger);  
          break;
        default:
          txnLogs.add(ledger);  
      }
    }

    return txnLogs;
  }
}
