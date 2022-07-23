import java.util.Date;

public interface TransactionLog {
  String accountNumber = "";
  double amount = 0.00;
  Date createdAt = new Date();
  boolean isCredit = false;
}
