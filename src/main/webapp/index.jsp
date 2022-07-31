<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
      table {
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
        border: 1px solid #ddd;
      }

      th, td {
        text-align: left;
        padding: 16px;
      }

      tr:nth-child(even) {
        background-color: #f2f2f2;
      }

      .button {
        position: relative;
        background-color: #04AA6D;
        border: none;
        /* font-size: 28px; */
        color: #FFFFFF;
        padding: 10px;
        margin: 5px;
        width: 200px;
        text-align: center;
        -webkit-transition-duration: 0.4s; /* Safari */
        transition-duration: 0.4s;
        text-decoration: none;
        overflow: hidden;
        cursor: pointer;
      }

      .button:after {
        content: "";
        background: #90EE90;
        display: block;
        position: absolute;
        padding-top: 300%;
        padding-left: 350%;
        margin-left: -20px!important;
        margin-top: -120%;
        opacity: 0;
        transition: all 0.8s
      }

      .button:active:after {
        padding: 0;
        margin: 0;
        opacity: 1;
        transition: 0s
      }
    </style>
  </head>
  
  <body>

    <h2>Account Management Platform</h2>
    <p>Demo</p>
    
    <!-- <button class="button" onclick="submit('createAccountForm')">Create Account</button> -->

    <p>Created Account</p>
    <div class="row">
      <div class="col-75">
        <div class="container">
          <form id="createAccountForm" method="POST" onsubmit="return false">
            <div class="row">
              <div class="col-50">            
                <label for="accountName">Account Name</label>
                <input type="text" id="accountName" name="accountName" placeholder="Account owner's name">
                <label for="phoneNumber">Phone Number</label>
                <input type="number" id="phoneNumber" name="phoneNumber" placeholder="Enter phone number (start with zero)">
                <label for="loop">Number of accounts</label>
                <input type="number" id="loop" name="loop" value="10" placeholder="Enter number of accounts to create">
                <input type="hidden" id="operation" name="operation" value="create_accounts">
              </div>              
            </div>            
          </form>
          <button class="button" onclick="submit('createAccountForm')">Create Account</button>
        </div>
      </div>      
    </div>
    <table id="generated_accounts">
      <tr>
        <th>Account Name</th>
        <th>Account Number</th>
        <th>Balance</th>
      </tr>
    </table>
    <hr>
    <!-- Deposit random funds. -->
    <h2>Deposits</h2>
    <p>Deposit random funds to account.</p>
    <div class="row">
      <div class="col-75">
        <div class="container">
          <form id="depositFundsForm" method="POST" onsubmit="return false">
          
            <div class="row">
              <div class="col-50">              
                <label for="fname"><i class="fa fa-user"></i> Account Number</label>
                <input type="text" id="accountNumber" name="accountNumber" placeholder="Enter an account number">
                <label for="amount"><i class="fa fa-envelope"></i> Amount</label>
                <input type="number" id="amount" name="amount" placeholder="Enter amount">
                <label for="loop"><i class="fa fa-envelope"></i> Loop</label>
                <input type="number" id="loop" name="loop" value="20" placeholder="Enter loop number">
                <input hidden="true" id="operation" name="operation" value="deposit_funds">
              </div>
            </div>
            
            <!-- <input type="submit" value="Make random deposits" class="button"> -->
          </form>
          <button class="button" onclick="submit('depositFundsForm')">Create Account</button>
        </div>
      </div>      
    </div>
    <table id="deposited_funds">
      <tr>
        <th>Account Number</th>
        <th>Amount Depsoited</th>
        <th>Balance</th>
      </tr>
    </table>
    <hr>
    <!-- Withdraw random funds. -->
    <h2>Withdrawals</h2>
    <p>Withdraw random funds from account.</p>
    <div class="row">
      <div class="col-75">
        <div class="container">
          <form action="/action_page.php">
          
            <div class="row">
              <div class="col-50">                
                <label for="awnumber"><i class="fa fa-user"></i> Account Number</label>
                <input type="text" id="awnumber" name="awnumber" placeholder="12345678901">
                <label for="wamount"><i class="fa fa-envelope"></i> Amount</label>
                <input type="number" id="wamount" name="wamount" placeholder="Enter amount">
                
              </div>              
            </div>
            
            <input type="submit" value="Make random withdrawals" class="button">
          </form>
        </div>
      </div>      
    </div>
    <hr>
    <!-- Transaction logs. -->
    <h2>Transactions Log</h2>
    <p>All transaction logs or from specific account.</p>
    <div class="row">
      <div class="col-75">
        <div class="container">
          <form action="/action_page.php">
          
            <div class="row">
              <div class="col-50">                
                <label for="tnumber"><i class="fa fa-user"></i> Account Number</label>
                <input type="text" id="tnumber" name="tnumber" placeholder="12345678901">
              </div>              
            </div>
            
            <input type="submit" value="Transaction Logs" class="button">
          </form>
        </div>
      </div>      
    </div>

    <script>
      function submit (formId) {
        
        const xhttp = new XMLHttpRequest();
        const form = document.getElementById(formId);        
        const formData = {};
        Array.from(form.elements).forEach((element) => {
          const name = element.name;
          const value = element.value;
          formData[name] = value;
        });
        console.log("form data", formData);
        xhttp.onreadystatechange = function() {
          console.log(this.status, this.readyState);
          if (this.readyState == 4 && this.status == 200) {
            console.log("responseText", this.responseText);
            // Handle response based on intended operation.
            switch (formData.operation) {
              case "create_accounts":// Operation: Create Accounts.
                handleAccountCreationResponse(this.responseText);
                break;
              case "deposit_funds":// Operation: Depsoit Funds.
                handleDepositFundsResponse(this.responseText);
                break;
            }
          }
        };
        // Post to the same url...'operation' in the form data will identify the intended operation.
        xhttp.open("POST", "/java_web_demo/account_management");
        xhttp.send(JSON.stringify(formData));        
      }
    
      // AJAX response handlers.
      function handleAccountCreationResponse (responseText) {
        const table = document.getElementById("generated_accounts");
        const accounts = JSON.parse(responseText);
        
        // Insert new rows to display accounts created.        
        accounts.forEach((account, i) => {
          // We already have a table header, so we insert after it.
          const row = table.insertRow(-1);
          const accountNameCell = row.insertCell(0);
          const accountNumberCell = row.insertCell(1);
          const balanceCell = row.insertCell(2);

          // Account details.
          accountNameCell.innerHTML = account.accountName;
          accountNumberCell.innerHTML = account.accountNumber;
          balanceCell.innerHTML = account.balance;
        });
      }

      function handleDepositFundsResponse (responseText) {
        const table = document.getElementById("deposited_funds");
        const txns = JSON.parse(responseText);

        // Clear out table...
        const rows = table.getElementsByTagName("tr");
        while (rows.length > 1) {
          rows[1].parentNode.removeChild(rows[1]);
        }
        
        // Insert new rows to display accounts created.        
        txns.forEach((txn, i) => {
          // We already have a table header, so we insert after it.
          const row = table.insertRow(-1);
          const accountNumberCell = row.insertCell(0);
          const amountCell = row.insertCell(1);
          const balanceCell = row.insertCell(2);

          // Account details.
          accountNumberCell.innerHTML = txn.accountNumber;
          amountCell.innerHTML = txn.amount;
          balanceCell.innerHTML = txn.runningBalance;
        });
      }
    </script>

  </body>
</html>
