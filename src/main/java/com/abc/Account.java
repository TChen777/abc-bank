package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

public void withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
        transactions.add(new Transaction(-amount));
    }
}

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
              /*
                if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount-1000) * 0.05;
                return 70 + (amount-2000) * 0.1;
                */
              
                Date latestTrans = null;
                //Find the latest withdrawal
                for (int i = 0; i < transactions.size(); I++) {
                    if (transactions.get(i).amount < 0)
                      latestTrans = transactions.get(i).getTransDate();
                }
                Date curDate = DateProvider.getInstance().now();
                
                if (latestTrans == null) {
                  //if no withdrawals, return 0
                  return 0;
                }
                else {
                  //Compare the dates
                  //If days are in order, calc the difference
                  if (curDate.getDate() > latestTrans.getDate() && curDate.getDate() - latestTrans.getDate() > 10) {
                    return amount * 0.05;
                  }
                  //If days are not in order count till 30 and add the rest
                  else if (curDate.getDate() < latestTrans.getDate()) {
                    int days = 30 - latestTrans.getDate() + curDate.getDate();    //Can be more accurate by using a month-day map
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
    
    private double sumTransactions() {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }
    
    //Added way to change account type
    public void changeAccountType(int accType) {
      this.accountType = accType;
    }

    //Estimate of a daily interest eraned
    public double interestEarnedDaily() {
      return interestEarned() / (30 * 12) ;
    }
}
