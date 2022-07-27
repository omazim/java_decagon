import java.util.HashMap;
// import java.io.*;  
// import java.net.*;

public class Main {
  public static void main(String[] args) {
    System.out.println("Welcome to Decagon Account Manager!");

    _startSession();

    final HashMap<String, String> credentialsMap = new HashMap<String, String>();
    credentialsMap.put(AccountCredentialHashMapKeys.accountName.name(), "Buhari");
    credentialsMap.put(AccountCredentialHashMapKeys.accountType.name(), AccountTypes.SAVINGS.name());
    credentialsMap.put(AccountCredentialHashMapKeys.phoneNumber.name(), "01234567890");
    
    Account account = new Account(credentialsMap);
    
    if (account.isOpened) {
      System.out.println("account was opened: ");
    }
  }

  private static void _startSession () {
    System.out.println();
  }

  // private static void _listen () {
    
  //   try {
  //     ServerSocket ss = new ServerSocket(6666);  
  //     Socket s = ss.accept();//establishes connection   
  //     DataInputStream dis = new DataInputStream(s.getInputStream());  
  //     String  str = (String) dis.readUTF();  
  //     System.out.println("message= " + str);

  //     ss.close();  
  //   } catch (IOException ex) {
  //     System.out.println(ex.getMessage());
  //   }
  // }
}

// public class MyServer {  
//   public static void main(String[] args) {
//     try{  
      
//       ServerSocket ss = new ServerSocket(6666);  
//       Socket s = ss.accept();//establishes connection   
//       DataInputStream dis = new DataInputStream(s.getInputStream());  
//       String  str = (String) dis.readUTF();  
//       System.out.println("message= " + str);

//       ss.close();  
//     } catch (Exception e) {
//       System.out.println(e);
//     }
//   }
// }