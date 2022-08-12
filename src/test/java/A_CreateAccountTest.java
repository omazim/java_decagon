import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;

import com.java_web.account.AccountInterface;
import com.java_web.account.AccountManager;
import com.java_web.account.CreateAccount;

import java.util.ArrayList;

import java.util.ArrayList;

public class A_CreateAccountTest {
    
  @Test
  public void testCreateAccount () {
    
    // We will create only 1 account. To increase the number of accounts created, increase this loop accordingly.
    final short loop = 1;
    
    // Account name and phone number.
    final String accountName = (String) "Chijioke";
    final String accountNameKey = "accountName";
    final String phoneNumber = (String) "08033273347";
    final String phoneNumberKey = "phoneNumber";    
    final JSONObject json = new JSONObject();

    json.put(accountNameKey, accountName);
    json.put(phoneNumberKey, phoneNumber);

    // List of accounts created.
    final ArrayList<AccountInterface> accountsList = CreateAccount.createAccount(json, loop);    
    final int size = accountsList.size();    

    // Assert that this (loop) many accounts were created.
    final String message1 = "Requesting to create " + loop + " account(s)";
    assertEquals(message1, loop, size);

    // Assert that the account number is exactly 10 digits.
    final String message2 = "Check that account number is exactly 10 digits long.";
    final String accountNumber = AccountManager.accountsMap.keys().nextElement();
    final int nubanLength = 10;

    assertEquals(message2, nubanLength, accountNumber.length());
  }    
}  
