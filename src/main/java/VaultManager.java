import java.util.ArrayList;
import java.util.HashMap;

public class VaultManager {

  public VaultManager () {
  
  }

  public VaultManager (String accountNumber) {
    this.accountNumber = accountNumber;
  }

  protected String accountNumber;

  public double updateBalance (String accountNumber, double amount) {
    
    final double insituBalance = balanceMap.putIfAbsent(accountNumber, 0.00);
    final double oldBalance = (double) insituBalance;
    final double newBalance = amount + oldBalance;

    balanceMap.put(accountNumber, newBalance);

    this._updateLedger(accountNumber, amount);

    return newBalance;
  }

  private void _updateLedger (String accountNumber, double amount) {
    
    final HashMap<String, Double> entry = new HashMap<String, Double>();
    
    entry.put(accountNumber, amount);
    
    ledger.add(entry);
  }

  public static HashMap<String, Double> balanceMap = new HashMap<String, Double>();
  public static ArrayList<HashMap<String, Double>> ledger = new ArrayList<HashMap<String, Double>>();
}
