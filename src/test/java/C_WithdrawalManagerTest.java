import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.Test;

import com.java_web.account.AccountManager;
import com.java_web.transaction.TransactionLog;
import com.java_web.transaction.WithdrawalManager;
import com.java_web.vault.VaultManager;

public class C_WithdrawalManagerTest {  
    
  @Test
  public void testWithdrawal () {
        
    final short loop = 1;
    final JSONObject json = new JSONObject();
    final double amount = 50.00;
    final String accountNumberKey = "accountNumber";
    final String amountKey = "amount";
    
    // We will use the first account created earier in 'CreateAccountTest.java'.
    final String accountNumber = AccountManager.accountsMap.keys().nextElement();

    System.out.println("first account number from accounts map: " + accountNumber);
    
    json.put(accountNumberKey, accountNumber);
    json.put(amountKey, amount);
    
    final WithdrawalManager withdrawalManager = new WithdrawalManager(accountNumber);  
    final ArrayList<TransactionLog> logs = withdrawalManager.runWithdrawal(json, loop);
    
    // Assert that amount withdrawm is the amount we posted.
    final double amountWithdrawn = logs.get(0).amount;
    assertEquals(amountWithdrawn, (amount * -1), 0);

    // Assert that the balance is 50 (because we deposited 100 but are withdrawing 50).
    final double depositedAmount = 100.00;
    final double expectedBalanceInAccount = depositedAmount - amount;
    final double actualBalanceInAccount = VaultManager.accountBalancesMap.get(accountNumber);
    
    assertEquals(expectedBalanceInAccount, actualBalanceInAccount, 0);
  }
}  
