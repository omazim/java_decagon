package com.java_web.account;

// import java.util.Arrays;
import java.util.HashMap;

public class Account extends AccountManager {
  
  public Account () {}

  public Account (String accountNumber) {

    super(accountNumber);
  }
  
  public Account (HashMap<String, String> credentialsMap) {
    
    // Handle validation of arguments before opening an account.
    try {
      
      if (!credentialsMap.containsKey(AccountCredentialHashMapKeys.accountName.name())) {
        throw new AccountCredentialsException("Missing account name. Please provide an account name.");
      }
      
      // Default to savings account type..
      if (!credentialsMap.containsKey(AccountCredentialHashMapKeys.accountType.name())) {
        this.accountType = AccountTypes.SAVINGS.name();
      }
      
      if (!credentialsMap.containsKey(AccountCredentialHashMapKeys.phoneNumber.name())) {
        throw new AccountCredentialsException("Missing phone number. Please provide a phone number (11 digits).");
      }
      
      final String accountName = credentialsMap.get(AccountCredentialHashMapKeys.accountName.name());
      final String accountType = credentialsMap.get(AccountCredentialHashMapKeys.accountType.name());
      final String phoneNumber = credentialsMap.get(AccountCredentialHashMapKeys.phoneNumber.name());
      
      if (!this._accountNameIsValid(accountName)) {
        throw new AccountCredentialsException("Invalid account name. Please enter at least 5 characters.");
      }
      
      if (AccountTypes.valueOf(accountType) instanceof AccountTypes == false) {
        throw new AccountCredentialsException("Invalid account type. Please enter SAVINGS, CURRENT or INVESTMENT.");
      }
      
      if (!this._phoneNumberIsValid(phoneNumber)) {
        throw new AccountCredentialsException("Invalid phone number. Please enter 11 digits.");
      }
      
      this.accountName = credentialsMap.get(AccountCredentialHashMapKeys.accountName.name());
      this.phoneNumber = credentialsMap.get(AccountCredentialHashMapKeys.phoneNumber.name());
      this.accountType = credentialsMap.get(AccountCredentialHashMapKeys.accountType.name());
      
      this.generateAccountNumber();

      this.openAccount();

    } catch (AccountCredentialsException ex) {
      System.out.println("error 1: " + ex.getMessage());
    }
  } 
}