# java_decagon
A test for competency in Java.

# Problem Statement
- Build an account management application that supports the creation of a new account, deposit, transaction log, and withdrawal.

# Operations
- ## Account creation
  This operation accepts an account name and a phone number and returns a 10-digit account number which is guaranteed to be unique because it is two-part randomized.  

  > In a larger real life project, uniqueness will be further guaranteed by attempting to retrieve am account record with the generated account number. If no such record exists, then uniqueness is further guaranteed.  

- ## Deposit
  This operation accepts an account number and an amount to deposit. The test confirms the balance in the account and the amount deposited.  

  Additionally, a `loop` argument can be used to increase the number of deposit transactions to the same account in one call. The client page provides an input field to accept a loop integer.  
  > However, in the test, `loop` is set to 1, which makes only one deposit transaction.  
  The balance in the account is tracked concurrently by a VaultManager class, which holds a map of accounts and their balances.

- ## Withdrawal
  This operation accepts an account number and an amount to withdraw. The test confirms the balance in the account and the amount withdrawn.  

  As in deposits, a `loop` argument can be used to increase the number of withdrawal transactions to the same account in one call. The client page provides an input field to accept a `loop` integer.  
  > However, in the test, `loop` is set to 1, which makes only one withdrawal transaction.  
  The balance in the account is tracked concurrently by a VaultManager class, which holds a map of accounts and their balances.

- ## Transaction History
  This operation accepts an account number. The test confirms the number of transactions on the account.  
  From the client, if no account is given, the operation will return all the transactions recorded by the VaultManager, sorted by transaction time.  

# Design
- ## VaultManager
  This class exposes the following:
  - A static account balances map as a ConcurrentHashMap. This map is keyed by the account number and holds the balance on that account.
  - A static ledger as a ConcurrentLinkedQueue. This queue holds every transaction done on the system.
- ## TransactionManager
  This class extends VaultsManager and exposes methods to:
  - deposit: to make a deposit, 
  - withdraw: to make a withdrawal,
  - depositHistory: to retrieve deposit history of an account,
  - withdrawalHistory: to retrieve withdrawal history on an account,
  - history: to retrieve all history on an account or all accounts (deposits and withdrawals).  

  > The methods for retrieving transaction history accepts arguments to determine what type of transaction history to retrieve and whether to use to filter for a target account or not.
- ## AccountManager
  This class extends VaultsManager as well and exposes methods to:
  - generateAccountNumber: to generate a random account number, 
  - openAccount: to register a new account in the accounts map and update its initial balance,
  > The class also has protected or private methods to validate account name and phone number before opening an account.
- ## Account
  > This class extends AccountsManager and calls its parent method to create an account.
- ## CreateAccount
  This class is used only from the servlet to handle the request for opening an account.
  > The number of accounts opened depends on the loop integer passed to it in the http request. It simply calls the Account class.
- ## DepositManager
  This class extends the TransactionManager and is used only from the servlet to handle deposit requests.  
  It exposes a `runDeposit` method that accepts the amount to deposit and the number of times to perform the deposit.  
  > If the `loop` argument is greater than 1, then successive deposit amounts will be simple multiples of the original amount.
- ## WithdrawalManager
  This class extends the TransactionManager and is used only from the servlet to handle withdrawal requests.  
  It exposes a `runWithdrawal` method that accepts the amount to withdraw and the number of times to perform the withdrawal.  
  > If the `loop` argument is greater than 1, then successive withdrawal amounts will be simple multiples of the original amount.
- ## AccountManagementServlet
  This class is the main servlet that handles all the requests from the client.  
  > It uses a _switch_ expression to handle each of the operations required.
- ## TransactionTypesEnum, LedgerDetailsKeys, AccountTypesAccountInterface, AccountCredentialHashMapKeys & AccountCredentialsException, TransactionResult
  These are additional classes representing enums, interfaces and exception that are necessary.  
- ## Main
  There's a Main class that is unused.  

# Writing
- ## What is the role of a tech lead?
  The tech lead will guide initiatives from team members, uphold the culture, mentor team members on their tech journey and assign tasks appropriately.  
  It is also the tech lead's responsibility to lead architectural design conversations, lead by code to fix problems and implement features.  
  In some situations, such a role may require one to manage the team's velocity, backlog and timelines as well.  

- ## What processes would you have in place to run your daily operation?
  - Scheduled time-boxed meets (schedule depends on whether the team is remote, onsite or hybrid).  
  - Backlog discussions and priorities.
  - Communication and feedback catchups.
- ## Do you have experience in any enterprise software architecture?
  - Serverless architecture:  
    Since 2019, I have led my team twice to migrate from shared hosting to Google Cloud Platform. Our backend now runs on cloud functions, pub sub routines and callable http functions.  
  - Microservices:  
    My team's backend code base is split into contextual categories. Even though they reside on the same project today, they can still run out of diverse locations or projects and deliver the same result.
- ## Do you have any experience with code review? If your answer is yes, kindly provide what you look for in your code review sessions.
  I perform code review every working day. In my reviews, I look out for the following:  
  - Structure  
    Whether the code body in review adheres to the structures agreed to during the design conversation. 
  - Convention  
    Whether the code body in review imbibes the conventions in the project. These may include indentation, naming, spacing.  
  - Clarity  
    Whether the code body in review is easy to read through, has no repetitions (boilerplate).  
  - Responsibility & Reusability  
    Whether functions, classes and methods have been written in such a way that they can be reused in different contexts, have singular responsibility and extendable.
  - Testability  
    Whether the code body in review can be tested easily.


- ## How do you intend to track the performance of your team?
  To track team performance, I would:
  - Observe the Issues open and close rates  
  - Observe the team's velocity (sprints and iterations)  
  - Observe the time it takes the team from idea/design conversation sessions to delivery.


