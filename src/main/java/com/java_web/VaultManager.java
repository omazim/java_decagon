package com.java_web;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class VaultManager implements AccountInterface {

  public VaultManager () {
  
  }

  public VaultManager (String accountNumber) {
    this.accountNumber = accountNumber;
  }

  protected String accountNumber;
  protected String accountName;
  protected String phoneNumber;
  protected String accountType;
  protected double balance;

  public double updateBalance (double amount) {
        
    final double insituBalance = accountBalancesMap.containsKey(accountNumber) ? accountBalancesMap.get(accountNumber): 0.00;
    final double prevBalance = (double) insituBalance;
    final double newBalance = amount + prevBalance;

    System.out.println("update balance for " + accountNumber + " with new balance: " + newBalance);
    accountBalancesMap.put(accountNumber, newBalance);

    if (amount != 0.00) this._updateLedger(amount, newBalance);
    
    return newBalance;
  }

  private void _updateLedger (double amount, double balance) {
    
    TransactionLog ledgerEntry = new TransactionLog(accountNumber, amount, balance);

    ledger.add(ledgerEntry);
  }

  public static ConcurrentHashMap<String, Double> accountBalancesMap = new ConcurrentHashMap<String, Double>();
  
  public static ConcurrentLinkedQueue<TransactionLog> ledger = new ConcurrentLinkedQueue<TransactionLog>();
}