// import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class VaultManager implements AccountInterface {

  public VaultManager () {
  
  }

  public VaultManager (String accountNumber) {
    this.accountNumber = accountNumber;
  }

  protected String accountNumber;
  protected String accountName;
  protected String phoneNumber;
  protected String accountType;

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

  public static ConcurrentHashMap<String, Double> balanceMap = new ConcurrentHashMap<String, Double>();
  // public static ArrayList<HashMap<String, Double>> ledger = new ArrayList<HashMap<String, Double>>();
  public static Queue<HashMap<String, Double>> ledger = new ConcurrentLinkedQueue<HashMap<String, Double>>();
}