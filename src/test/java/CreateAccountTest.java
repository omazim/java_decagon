import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.Test;
import java.util.ArrayList;
import com.java_web.CreateAccount;
import com.java_web.AccountInterface;

public class CreateAccountTest {
    
  @Test  
  public void testCreateAccount () {
    
    final short loop = 10;
    
    // Account name and phone number.
    final String accountName = (String) "Chijioke";
    final String accountNameKey = "accountName";
    final String phoneNumber = (String) "08033273347";
    final String phoneNumberKey = "phoneNumber";
    
    // String builder to make up the test json.
    // final StringBuilder stringBuilder = new StringBuilder();
    
    // stringBuilder.append("{");
    // stringBuilder.append(accountNameKey + ": " + accountName);
    // stringBuilder.append("," + phoneNumberKey + ": " + phoneNumber);
    // stringBuilder.append("}");

    // final JSONObject json = new JSONObject(stringBuilder.toString());
    final JSONObject json = new JSONObject();
    // json.append(accountNameKey, accountName);
    // json.append(phoneNumberKey, phoneNumber);
    json.put(accountNameKey, accountName);
    json.put(phoneNumberKey, phoneNumber);
    System.out.println(json.toString());
    final ArrayList<AccountInterface> accountsList = CreateAccount.createAccount(json, loop);
    final int size = accountsList.size();
    assertEquals("Requesting to create " + loop + " account(s)", loop, size);
  }    
}  
