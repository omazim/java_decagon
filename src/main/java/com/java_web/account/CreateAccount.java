package com.java_web.account;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

public class CreateAccount {

  public static ArrayList<AccountInterface> createAccount (JSONObject json, short count) {

    final ArrayList<AccountInterface> accountsList = new ArrayList<AccountInterface>();  
    final int currSize = AccountManager.accountsMap.size();
    
    // We loop here to handle the number of accounts to be created.
    for(int i = 0; i < count; i++) {

      final int counter = i + 1 + currSize;
      final HashMap<String, String> credentialsMap = new HashMap<String, String>();
      final String accountNameKey = AccountCredentialHashMapKeys.accountName.name();      
      final String accountTypeKey = AccountCredentialHashMapKeys.accountType.name();
      final String phoneNumberKey = AccountCredentialHashMapKeys.phoneNumber.name();
      final String accountName = (String) json.get(accountNameKey);
      final String accountType = AccountTypes.SAVINGS.name();//(String) json.get(accountTypeKey);
      final String phoneNumber = (String) json.getString(phoneNumberKey);
      
      credentialsMap.put(accountNameKey, accountName + "_" + counter);
      credentialsMap.put(accountTypeKey, accountType);
      credentialsMap.put(phoneNumberKey, phoneNumber);
      
      Account account = new Account(credentialsMap);
      
      if (account.isOpened) accountsList.add(account);      
    }
    
    return accountsList;
  }
}