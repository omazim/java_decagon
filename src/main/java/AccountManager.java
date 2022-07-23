import java.util.HashMap;

public class AccountManager extends VaultManager implements AccountInterface {
  
  public static HashMap<String, HashMap<String, Object>> accountsMap;

  public AccountManager () {}

  public AccountManager (String accountNumber) {

    super(accountNumber);
  }

  public void updateAccounts () {
    
    final HashMap<String, Object> details = new HashMap<String, Object>();
    try {
      if (accountsMap.size() == 0) {

        System.out.println("first account to be opened.");
        accountsMap = new HashMap<String, HashMap<String, Object>>();    
      }
      
      details.put("accountNumber", this.accountNumber);
      details.put("accountName", this.accountName);
      details.put("accountType", this.accountType);
      details.put("phoneNumber", this.accountNumber);
      
      accountsMap.put(accountNumber, details);
      System.out.println("updated accounts!");
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
  }

  protected String accountNumber;
  protected String accountName;
  protected String phoneNumber;
  protected String accountType;

  // private short accountNumberLength = 10;
  private short phoneNumberLength = 11;
  private short accountNameMinLength = 5;
  private short accountNameMaxLength = 20;
   
  protected void _generateAccountNumber () {
    System.out.println("generating account number...");
    this.accountNumber = this.phoneNumber.substring(1);
  }

  public void _openAccount () {
    
    // System.out.println("updating accounts...");
    this.updateAccounts();

    // System.out.println("updating initial account balance...");
    // this.updateBalance(accountNumber, 0.00);

    this.isOpened = true;
    System.out.println("account " + this.accountNumber + " opened successfully.");
  }

  protected boolean _accountNameIsValid (String name) {

    return name.length() >= this.accountNameMinLength && name.length() <= this.accountNameMaxLength;
  }

  protected boolean _phoneNumberIsValid (String number) {

    return number.length() == this.phoneNumberLength && number.substring(0, 1).equals("0");
  }

  public boolean isOpened = false;  
}
