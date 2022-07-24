import java.util.HashMap;

import org.json.JSONObject;

public class CreateAccount {

  public static String createAccount (JSONObject json) {

    final HashMap<String, String> credentialsMap = new HashMap<String, String>();
    final String accountNameKey = AccountCredentialHashMapKeys.accountName.name();
    final String accountTypeKey = AccountCredentialHashMapKeys.accountType.name();
    final String phoneNumberKey = AccountCredentialHashMapKeys.phoneNumber.name();

    final String accountName = (String) json.get(accountNameKey);
    final String accountType = AccountTypes.SAVINGS.name();//(String) json.get(accountTypeKey);
    final String phoneNumber = (String) json.get(phoneNumberKey);

    credentialsMap.put(accountNameKey, accountName);
    credentialsMap.put(accountTypeKey, accountType);
    credentialsMap.put(phoneNumberKey, phoneNumber);
    
    Account account = new Account(credentialsMap);
        
    if (account.isOpened) {
      return account.accountNumber; 
    }

    return "";
  }
}