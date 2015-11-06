package com.abc;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
  
    private final double amount;
    private Date transactionDate;

    //Constructor
    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }
    
    //Returns the transaction amount
    public double getAmount() {
        return amount;
    }
    
    //Returns the transaction date
    protected Date getTransDate() {
        return transactionDate;
    }

}
