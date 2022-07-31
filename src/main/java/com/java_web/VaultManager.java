package com.java_web;

import java.util.HashMap;
import java.util.Optional;
import java.util.Queue;
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

  public double updateBalance (String accountNumber, double amount) {
    
    final double insituBalance = accountBalancesMap.containsKey(accountNumber) ? accountBalancesMap.get(accountNumber): 0.00;
    final double prevBalance = (double) insituBalance;
    final double newBalance = amount + prevBalance;

    accountBalancesMap.put(accountNumber, newBalance);

    if (amount > 0.00) {
      this._updateLedger(accountNumber, amount, newBalance);
    }

    return newBalance;
  }

  private void _updateLedger (String accountNumber, double amount, double balance) {
    
    final HashMap<String, Object> ledgerEntry = new HashMap<String, Object>();
    
    ledgerEntry.put(LedgerDetailsKeys.accountNumber.name(), accountNumber);
    ledgerEntry.put(LedgerDetailsKeys.amount.name(), amount);
    ledgerEntry.put(LedgerDetailsKeys.balance.name(), amount);
    
    ledger.add(ledgerEntry);
  }

  public static ConcurrentHashMap<String, Double> accountBalancesMap = new ConcurrentHashMap<String, Double>();
  // public static ArrayList<HashMap<String, Double>> ledger = new ArrayList<HashMap<String, Double>>();
  public static Queue<HashMap<String, Object>> ledger = new ConcurrentLinkedQueue<HashMap<String, Object>>();
}