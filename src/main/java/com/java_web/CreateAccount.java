package com.java_web;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

public class CreateAccount {

  public static ArrayList<AccountInterface> createAccount (JSONObject json, short count) {

    final ArrayList<AccountInterface> accountsList = new ArrayList<AccountInterface>();  
    final int currSize = AccountManager.accountsMap.size();
    System.out.println("size of accounts already created: " + String.valueOf(currSize));
    
    for(int i = 0; i < count; i++) {

      final int counter = i + 1 + currSize;
      // System.out.println("opening account on loop " + counter);
      final HashMap<String, String> credentialsMap = new HashMap<String, String>();
      final String accountNameKey = AccountCredentialHashMapKeys.accountName.name();      
      final String accountTypeKey = AccountCredentialHashMapKeys.accountType.name();
      final String phoneNumberKey = AccountCredentialHashMapKeys.phoneNumber.name();
      // System.out.println("getting account name arg....");
      // final Object accountNameO = json.get(accountNameKey);
      // System.out.println(accountNameO.getClass().getName());
      final String accountName = (String) json.get(accountNameKey);
      // accountNameO: new JSONArray(accountNameO.toString()).getString(0);
      // System.out.println("account name arg = " + accountName);
      final String accountType = AccountTypes.SAVINGS.name();//(String) json.get(accountTypeKey);
      final String phoneNumber = (String) json.getString(phoneNumberKey);
      // System.out.println("account name arg = " + phoneNumber);
      // System.out.println("account on loop 1 " + counter + " " + credentialsMap + " opening.");
      credentialsMap.put(accountNameKey, accountName + "_" + counter);
      credentialsMap.put(accountTypeKey, accountType);
      credentialsMap.put(phoneNumberKey, phoneNumber);
      // System.out.println("account on loop 2 " + counter + " " + credentialsMap + " opening.");
      Account account = new Account(credentialsMap);
      
      if (account.isOpened) accountsList.add(account);
      // System.out.println("account on loop " + counter + " opened.");
    }
    // System.out.println("accounts opened in request: " + accountsList);
    return accountsList;
  }
}