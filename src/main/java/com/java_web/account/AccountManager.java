package com.java_web.account;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.java_web.vault.VaultManager;

import java.util.Random;

public class AccountManager extends VaultManager {
  
  public static ConcurrentHashMap<String, HashMap<String, Object>> accountsMap = new ConcurrentHashMap<String, HashMap<String, Object>>();

  public AccountManager () {}

  public AccountManager (String accountNumber) {

    super(accountNumber);
  }

  private void updateAccounts () {
    
    final HashMap<String, Object> details = new HashMap<String, Object>();
    try {
      if (accountsMap.size() == 0) accountsMap = new ConcurrentHashMap<String, HashMap<String, Object>>();
      
      details.put(AccountCredentialHashMapKeys.accountNumber.name(), this.accountNumber);
      details.put(AccountCredentialHashMapKeys.accountName.name(), this.accountName);
      details.put(AccountCredentialHashMapKeys.accountType.name(), this.accountType);
      details.put(AccountCredentialHashMapKeys.phoneNumber.name(), this.accountNumber);
      details.put(AccountCredentialHashMapKeys.balance.name(), this.balance);
      
      accountsMap.put(accountNumber, details);
      System.out.println("updated accounts!");
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  // private short accountNumberLength = 10;
  private short phoneNumberLength = 11;
  private short accountNameMinLength = 5;
  private short accountNameMaxLength = 20;
  
  protected void generateAccountNumber () {
    System.out.println("generating account number...");
        
    final Random random = new Random();
    final String digits = String.valueOf(Math.abs(random.nextLong())).substring(0, 9);

    final String prefixDigit = String.valueOf(Math.abs(random.nextInt(10)));
    this.accountNumber = prefixDigit + digits;
  }

  protected void openAccount () {
    
    final double openingAmount = 0.00; // Fancy...if you want to run a promo on account opening, we can change this amount.
    System.out.println("updating accounts...");
    this.updateAccounts();

    System.out.println("updating initial account balance...");
    // Update initial account opening balance.
    this.balance = this.transact(openingAmount);

    this.isOpened = true;
    System.out.println("account " + this.accountNumber + " opened successfully with balance => " + balance);
  }

  protected boolean _accountNameIsValid (String name) {

    return name.length() >= this.accountNameMinLength && name.length() <= this.accountNameMaxLength;
  }

  protected boolean _phoneNumberIsValid (String number) {

    return number.length() == this.phoneNumberLength && number.substring(0, 1).equals("0");
  }

  public boolean isOpened = false;  

  public boolean isValidAccount () {
    return accountsMap.containsKey(accountNumber);
  }
}
