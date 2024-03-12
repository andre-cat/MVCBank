# BANK ACCOUNT PROJECT

Let's create an application using all the resources learned, with special emphasis on maintaining SOLID principles and the use of SpringBoot. The objective of this application is to allow us to manage our bank account, that allows us to have a global balance and a series of actions that we can choose from:

- [X] Deposit 
  - [X] **From branch:** the deposit from branch does not have any cost on the transaction.
  - [X] **From ATM:** the ATM deposit has a cost of 2 USD on the transaction.
  - [X] **From another account:** the deposit from another account has a fee of 1.5 USD on the transaction.
  > **Note**: Each of these deposits will affect the total balance of the account minus the transaction fee
- [X] Purchase
  - [X] **With the card in a physical store:** there is no cost for the purchase in a physical store.
  - [X] **With the card on a web page:** a purchase with the card on the web has a theft insurance cost of 5 USD.
- [X] Withdrawal
  - [X] **On ATM:** ATM withdrawal has a cost of 1 USD.

All these actions affect the overall balance and also reduce the cost of the transaction.