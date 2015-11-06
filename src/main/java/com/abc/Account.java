package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

public class Account {

    //If types of accounts become numerous, probably best to create an abstract Acount class
    //Also can use enums
    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    private double balance;
    private List<Transaction> transactions;

    //Constructor
    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
        this.balance = 0;
    }

    //Get the current balance
    public double getBalance() {
        return balance;
    }
    
    //Get the list of transactions
    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    //A deposit transaction and update the balance
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
            balance += amount;
        }
    }

    //A withdraw transaction and update the balance
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(-amount));
            balance -= amount;
        }
    }

    //Method used to calculate interest per year for the various types of accounts
    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case CHECKING:
                return amount * 0.001;
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
            /*
            case SUPER_SAVINGS:
                if (amount <= 4000)
                    return 20;
            */
            case MAXI_SAVINGS:
              
                Date latestTrans = null;
                
                //Find the latest withdrawal
                for (int i = 0; i < transactions.size(); i++) {
                    if (transactions.get(i).getAmount() < 0)
                      latestTrans = transactions.get(i).getTransDate();
                }
                Date curDate = DateProvider.getInstance().now();
                
                if (latestTrans == null) {
                  //if no withdrawals, return 0
                  return amount * 0.05;
                }
                else {
                  //Compare the dates
                  //If days are in order, calc the difference
                  if (curDate.getDate() > latestTrans.getDate() && curDate.getDate() - latestTrans.getDate() > 10) {
                    return amount * 0.05;
                  }
                  //If days are not in order count the last transaction date to the end of month and add the remaining days of the following month
                  else if (curDate.getDate() < latestTrans.getDate()) {
                    
                    Calendar calendar = Calendar.getInstance();  
                    calendar.setTime(latestTrans); 
                    
                    //Use calendar to obtain number of total day in that month
                    int days = (calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - latestTrans.getDate() )+ curDate.getDate();
                    
                    if (days > 10)
                      return amount * 0.05;
                    else
                      return amount * 0.001;
                  }
                  else
                    return amount * 0.001;
                }
            default:
                return amount * 0.001;
        }
    }
    
    /*
     * Unnecessary
    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }
    */
    
    //Returns the sum of all transactions
    public double sumTransactions() {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.getAmount();
        return amount;
    }

    //returns the account type
    public int getAccountType() {
        return accountType;
    }

    //Accrue daily interest
    //Perhaps some daily function calls this one to accrue interest
    public void accrueDailyInterest() {
        balance += interestEarned() / 365 ;
    }
    
    //Used to find interest earned 
    public double currentInterestEarned() {
        double sumTrans = sumTransactions();
        return (sumTrans > 0 ? balance - sumTrans : 0.0);
    }
}
