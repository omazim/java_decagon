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
                <input type="number" id="loop" name="loop" placeholder="Enter number of accounts to create">
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
      <tr>
        <td>Jill</td>
        <td>Smith</td>
        <td>50</td>
      </tr>
    </table>
    <hr>
    <!-- Deposit random funds. -->
    <h2>Deposits</h2>
    <p>Deposit random funds to account.</p>
    <div class="row">
      <div class="col-75">
        <div class="container">
          <form action="">
          
            <div class="row">
              <div class="col-50">              
                <label for="fname"><i class="fa fa-user"></i> Account Number</label>
                <input type="text" id="anumber" name="anumber" placeholder="12345678901">
                <label for="damount"><i class="fa fa-envelope"></i> Amount</label>
                <input type="number" id="damount" name="damount" placeholder="Enter amount">
                <label for="deposit_loop"><i class="fa fa-envelope"></i> Loop</label>
                <input type="number" id="deposit_loop" name="deposit_loop" placeholder="Enter loop number">
                <input hidden="true" id="create_operation" name="create_operation">
              </div>
            </div>
            
            <input type="submit" value="Make random deposits" class="button">
          </form>
        </div>
      </div>      
    </div>
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
            switch (formData.operation) {
              case "create_accounts":
                const table = document.getElementById("generated_accounts");
                const accounts = JSON.parse(this.responseText);
                
                accounts.forEach((account, i) => {
                  const row = table.insertRow(i);
                  const accountNameCell = row.insertCell(0);
                  const accountNumberCell = row.insertCell(1);
                  const balanceCell = row.insertCell(2);

                  // Add some text to the new cells:
                  accountNameCell.innerHTML = account.accountName;
                  accountNumberCell.innerHTML = account.accountNumber;
                  balanceCell.innerHTML = account.accountBalance;
                });
                break;
            }
          }
        };
        xhttp.open("POST", "/java_web_demo/account_management");
        xhttp.send(JSON.stringify(formData));        
      }
    </script>

  </body>
</html>
