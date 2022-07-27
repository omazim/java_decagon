import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
// import com.google.gson.JsonElement;

import java.io.*;
// import java.util.ArrayList;  
import java.util.ArrayList;

public class AccountManagementServlet extends HttpServlet {
  
  private String getBody (HttpServletRequest request)  {

    String body = null;
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = null;

    try {
      InputStream inputStream = request.getInputStream();
      if (inputStream != null) {
          bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
          char[] charBuffer = new char[128];
          int bytesRead = -1;
          while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
              stringBuilder.append(charBuffer, 0, bytesRead);
          }
      } else {
          stringBuilder.append("");
      }
    } catch (IOException ex) {
        // throw ex;
        return "";
    } finally {
      if (bufferedReader != null) {
        try {
          bufferedReader.close();
        } catch (IOException ex) {

        }
      }
    }

    body = stringBuilder.toString();
    return body;
  }

  private JSONObject incomingJSON (HttpServletRequest req) {
    // Parse incoming JSON from the body.
    String body = getBody(req);
    return new JSONObject(body);

    // Iterator<String> it = jObj.keys();
    // String html = "";
    // while(it.hasNext())
    // {
    //   String key = it.next(); // get key
    //   Object o = jObj.get(key); // get value
    //   System.out.println(key + " : " +  o); // print the key and value
    //   html += key + " : " +  o;
    // }
    // jObj.
    
  }

  @Override
  public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    // Get path info so we can route request.
    // Identify session.
    // concurrently manage entries in vault manager.

    final JSONObject reqJSON = incomingJSON(req);   
    final String operationKey = "operation";
    final String operationParam = String.valueOf(reqJSON.get(operationKey));
    final GsonBuilder gsonBuilder = new GsonBuilder();		
		final Gson gson = gsonBuilder.create(); 
    
    String resJSON = "";
    String loopKey = "loop";
    String loop = String.valueOf(reqJSON.get(loopKey));
    // System.out.println("operation: " + operationParam + ", loop" + loop);
    short loopCount = loop.isEmpty() ? 1: Short.valueOf(loop);

		PrintWriter out = res.getWriter();

    switch (operationParam) {
      case "create_accounts":
        
        // Technical requirement: generate 10 accounts;
        try {
          final ArrayList<AccountInterface> accounts = CreateAccount.createAccount(reqJSON, loopCount);
          System.out.println("accounts created: " + accounts.size());
          resJSON = gson.toJson(accounts);
          System.out.println("accounts created: " + resJSON);
        } catch (Exception ex) {
          System.out.println(ex.getMessage());
        }
        break;
      
      case "deposit_amounts":
        
        // Technical requirement: generate 10 accounts;
        // count = 20;
        // final ArrayList<String> accountNumbers = CreateAccount.createAccount(reqJSON, count);
        // resJSON = gson.toJson(accountNumbers);

        break;
      default:
        break;
    }
        
    res.setContentType("application/json");
    res.setCharacterEncoding("UTF-8");
    
    out.print(resJSON);
    out.flush();
  }
}