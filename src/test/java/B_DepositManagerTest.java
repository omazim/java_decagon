import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.Test;

import com.java_web.account.AccountManager;
import com.java_web.transaction.DepositManager;
import com.java_web.transaction.TransactionLog;
import com.java_web.vault.VaultManager;

public class B_DepositManagerTest {  
    
  @Test
  public void testDeposit () {
        
    final short loop = 1;
    final JSONObject json = new JSONObject();
    final double amount = 100.00;
    final String accountNumberKey = "accountNumber";
    final String amountKey = "amount";
    
    // We will use the first account created earier in 'CreateAccountTest.java'.
    final String accountNumber = AccountManager.accountsMap.keys().nextElement();

    System.out.println("first account number from accounts map: " + accountNumber);
    
    json.put(accountNumberKey, accountNumber);
    json.put(amountKey, amount);
    
    final DepositManager depositManager = new DepositManager(accountNumber);  
    final ArrayList<TransactionLog> logs = depositManager.runDeposits(json, loop);
    
    // Assert that amount deposited is the amount we posted.
    final double amountDeposited = logs.get(0).amount;
    assertEquals(amountDeposited, amount, 0);

    // Assert that the balance is 100 (because we dposited 100).
    final double expectedBalanceInAccount = amount;
    final double actualBalanceInAccount = VaultManager.accountBalancesMap.get(accountNumber);
    
    assertEquals(expectedBalanceInAccount, actualBalanceInAccount, 0);
  }
}  
