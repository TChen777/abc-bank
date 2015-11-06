package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
  
    private String name;
    private List<Account> accounts;

    //Constructor
    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    //Get the name of the customer
    public String getName() {
        return name;
    }

    //Adds a account to the customer
    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    //Returns number of accounts present
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    //Retruns total interest earned for all accounts
    public double totalInterestEarned() {
        double total = 0.0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    //Prints a formatted detail of the customer's statement
    public String getStatement() {
        //Can use a string builder to use less memory and improve performance
      
        StringBuilder statement = new StringBuilder();
        statement.append("Statement for " + name + "\n");
        double total = 0.0;
        for (Account a : accounts) {
            statement.append("\n" + statementForAccount(a) + "\n");
            total += a.sumTransactions();
        }
        statement.append( "\nTotal In All Accounts " + (total < 0.0 ? "-" : "") + toDollars(total) );     //Adds a negative sign if amount is less than 0
        return statement.toString();
        
    }

    //Returns a formatted statement for a specific account
    private String statementForAccount(Account a) {
      
      StringBuilder accState = new StringBuilder();

       //Translate to pretty account type
        switch(a.getAccountType()){
            case Account.CHECKING:
                accState.append("Checking Account\n");
                break;
            case Account.SAVINGS:
                accState.append("Savings Account\n");
                break;
            case Account.MAXI_SAVINGS:
                accState.append("Maxi Savings Account\n");
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.getTransactions()) {
            accState.append("  " + (t.getAmount() < 0.0 ? "withdrawal" : "deposit") + " " + toDollars(t.getAmount()) + "\n");
            total += t.getAmount();
        }
        accState.append("Total " + (total < 0.0 ? "-" : "") + toDollars(total));        //Adds a negative sign if amount is less than 0
        return accState.toString();
    }

    //Returns a formatted string of absolute currency
    private String toDollars(double d){
      return String.format("$%,.2f", abs(d));
    }
    
    //Transfers funds from one account to another
    public boolean accTransfer(Account fromAcc, Account toAcc, double amount) {
        //Check both accounts are in fact with the customer
        if (amount > 0.0 && accounts.contains(fromAcc) && accounts.contains(fromAcc)) {
            //Check if customer has enough to transfer
            if (fromAcc.getBalance() > amount) {
                fromAcc.withdraw(amount);
                toAcc.deposit(amount);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
