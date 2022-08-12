package com.java_web.account;

// import jakarta.servlet.*;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
// import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.java_web.transaction.DepositManager;
import com.java_web.transaction.TransactionLog;
import com.java_web.transaction.TransactionManager;
import com.java_web.transaction.WithdrawalManager;

import java.util.ArrayList;
// import java.util.Collection;
import java.util.Collections;

public class AccountManagementServlet extends HttpServlet {
  
  private String getBody (HttpServletRequest request)  {

    String body = null;
    final StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = null;

    try {
      InputStream inputStream = request.getInputStream();
      if (inputStream != null) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        char[] charBuffer = new char[128];
        int bytesRead = -1;
        while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
          stringBuilder.append(charBuffer, 0, bytesRead);
        }
      } else {
          stringBuilder.append("");
      }
    } catch (IOException ex) {
        // throw ex;
        return "";
    } finally {
      if (bufferedReader != null) {
        try {
          bufferedReader.close();
        } catch (IOException ex) {

        }
      }
    }

    body = stringBuilder.toString();
    return body;
  }

  /**
   * Parse incoming JSON from the request body.
   */
  private JSONObject incomingJSON (HttpServletRequest req) {
    
    final String body = getBody(req);
    return new JSONObject(body);    
  }

  @Override
  public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    final JSONObject reqJSON = incomingJSON(req);   
    final String operationKey = "operation";
    final String operationParam = String.valueOf(reqJSON.get(operationKey));
    final GsonBuilder gsonBuilder = new GsonBuilder();		
		final Gson gson = gsonBuilder.create(); 
    
    String resJSON = "";
    final String loopKey = "loop";
    String loop;    
    short loopCount;
    
		PrintWriter out = res.getWriter();

    // There's a case for every operation listed in the requirements.
    switch (operationParam) {
      case "create_accounts":
        
        // Technical requirement: generate 10 accounts;
        loop = String.valueOf(reqJSON.get(loopKey));    
        loopCount = loop.isEmpty() ? 1: Short.valueOf(loop);

        try {
          final ArrayList<AccountInterface> accounts = CreateAccount.createAccount(reqJSON, loopCount);
          
          resJSON = gson.toJson(accounts);
          
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        break;
      
      case "deposit_funds":
        
        // Technical requirement: deposit 20 random amounts;
        loop = String.valueOf(reqJSON.get(loopKey));    
        loopCount = loop.isEmpty() ? 1: Short.valueOf(loop);
        try {
          final String accountNumber = (String) reqJSON.get(AccountCredentialHashMapKeys.accountNumber.name());
          final DepositManager depositManager = new DepositManager(accountNumber);          
          final ArrayList<TransactionLog> txns = depositManager.runDeposits(reqJSON, loopCount);
          
          resJSON = gson.toJson(txns);

          // System.out.println("funds deposited: " + resJSON.length());
        } catch (Exception ex) {
          System.out.println("error@deposit_funds case: " + ex.getMessage());
        }

        break;
      case "withdraw_funds":
        
        // Technical requirement: withdraw 20 random amounts;
        loop = String.valueOf(reqJSON.get(loopKey));    
        loopCount = loop.isEmpty() ? 1: Short.valueOf(loop);

        try {
          final String accountNumber = (String) reqJSON.get(AccountCredentialHashMapKeys.accountNumber.name());
          final WithdrawalManager withdrawalManager = new WithdrawalManager(accountNumber);          
          final ArrayList<TransactionLog> txns = withdrawalManager.runWithdrawal(reqJSON, loopCount);
          
          resJSON = gson.toJson(txns);

          // System.out.println("funds withdrawn: " + resJSON.length());
        } catch (Exception ex) {
          System.out.println("error@withdraw_funds case: " + ex.getMessage());
        }

        break;
      case "txn_history":
        
        // Technical requirement: display transaction for account or all transactions in system;
        ArrayList<TransactionLog> txns = new ArrayList<TransactionLog>();
        try {
          final String accountNumber = (String) reqJSON.get(AccountCredentialHashMapKeys.accountNumber.name());
          final TransactionManager txnManager = accountNumber.isEmpty() ? new TransactionManager(): new TransactionManager(accountNumber);          
          txns = txnManager.history();

          System.out.println("account number was empty? " + accountNumber.isEmpty());
          System.out.println("txn log count: " + txns.size());
        } catch (Exception ex) {
          System.out.println("error@txn_history case: " + ex.getMessage());
        } finally {

          Collections.reverse(txns);
          resJSON = gson.toJson(txns);
        }

        break;
      default:
        break;
    }
        
    res.setContentType("application/json");
    res.setCharacterEncoding("UTF-8");
    
    out.print(resJSON);
    out.flush();
  }
}