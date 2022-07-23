import java.util.HashMap;
// import java.net.http.*;

public class Main {
  public static void main(String[] args) {
    System.out.println("Welcome to Decagon Account Manager!");

    // _startSession();

    final HashMap<String, String> credentialsMap = new HashMap<String, String>();
    credentialsMap.put(AccountCredentialHashMapKeys.accountName.name(), "Buhari");
    credentialsMap.put(AccountCredentialHashMapKeys.accountType.name(), AccountTypes.SAVINGS.name());
    credentialsMap.put(AccountCredentialHashMapKeys.phoneNumber.name(), "01234567890");
    
    Account account = new Account(credentialsMap);
    
    if (account.isOpened) {
      System.out.println("account was opened: ");
    }
  }

  // private static void _startSession () {
  //   System.out.println();
  // }
}