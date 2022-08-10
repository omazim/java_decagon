import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.java_web.TransactionManager;
import com.java_web.TransactionLog;
import com.java_web.AccountManager;

public class D_TransactionManagerTest {  
    
  @Test
  public void testTransactionHistory () {
            
    // We will use the first account created earier in 'CreateAccountTest.java'.
    final String accountNumber = AccountManager.accountsMap.keys().nextElement();

    System.out.println("first account number from accounts map: " + accountNumber);
        
    final TransactionManager txnManager = new TransactionManager(accountNumber);  
    final ArrayList<TransactionLog> logs = txnManager.history();
    
    // Assert that we have 2 transactions in the log.
    final int expectedNumberOfTransactions = 2; // 1 deposit, 1 withdrawal.
    final int actualNumberOfTransactions = logs.size();
    assertEquals(expectedNumberOfTransactions, actualNumberOfTransactions, 0);
  }
}  
