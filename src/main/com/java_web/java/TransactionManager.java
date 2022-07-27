import java.util.ArrayList;

public class TransactionManager extends AccountManager {
  
  public TransactionManager () {}

  public TransactionManager (String accountNumber) {
    
    super(accountNumber);
  }
  String accountNumber;

  public boolean deposit (double amount) {

    boolean deposited = false;

    try {
      this.updateBalance(this.accountNumber, amount);

      deposited = true;
    } catch (Exception ex) {
      System.out.println();
    }
    return deposited;
  }

  public boolean withdraw (double amount) {

    boolean withdrawn = false;

    try {
      this.updateBalance(this.accountNumber, (amount * -1));

      withdrawn = true;
    } catch (Exception ex) {
      System.out.println();
    }
    return withdrawn;
  }

  public ArrayList<TransactionLog> history () {

    if (this.accountNumber == null) {

      return this.allTransactions();
    } else {
      return this.transactionLog();
    }    
  }

  private ArrayList<TransactionLog> allTransactions () {
    return new ArrayList<TransactionLog>();
  }

  private ArrayList<TransactionLog> transactionLog () {
    return new ArrayList<TransactionLog>();
  }
}
