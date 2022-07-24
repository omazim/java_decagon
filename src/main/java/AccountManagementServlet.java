// import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import java.io.*;  

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

  public static ConcurrentHashMap<Integer, String> chmap = new ConcurrentHashMap<Integer, String>();

  @Override
  public void doPost  (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    
    // Get path info so we can route request.
    // Identify session.
    // concurrently manage entries in vault manager.

    final String path = req.getPathInfo();
    final JSONObject reqJSON = incomingJSON(req); 
    String resJSON = "";
    PrintWriter out = res.getWriter();

    switch (path) {
      case "/create_account":
        
        resJSON = CreateAccount.createAccount(reqJSON);

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