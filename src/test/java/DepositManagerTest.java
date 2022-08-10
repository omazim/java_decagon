import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;

import com.java_web.DepositManager;
import com.java_web.TransactionLog;
import com.java_web.AccountManager;

public class DepositManagerTest {  
    
  @Test  
  public void testDeposit () {
        
    final short loop = 1;
    final JSONObject json = new JSONObject();
    final double amount = 100.00;
    
    json.append("accountNumber", "Chijioke");
    json.append("amount", amount);
    Map.Entry<String, HashMap<String,Object>> entry = AccountManager.accountsMap.entrySet().iterator().next();
    String accountNumber = entry.getKey();
    DepositManager obj = new DepositManager(accountNumber);  
    ArrayList<TransactionLog> logs = obj.runDeposits(json, loop);    
    double depositAmount = logs.get(0).amount;
    
    assertEquals("Deposit amount to account.", amount, depositAmount);
  }
}  
