package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {
  
    private List<Customer> customers;

    //Constructor
    public Bank() {
        customers = new ArrayList<Customer>();
    }

    //Adds customer to the bank
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    //Prints a formatted string of bank's customer detail
    public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : customers)
            summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    //returns the total interest paid by the bank to all its customers
    public double totalInterestPaid() {
        double total = 0.0;
        for(Customer c: customers)
            total += c.totalInterestEarned();
        return total;
    }

    //obtain the first customer
    public String getFirstCustomer() {
        if (customers.size() < 1) {
          return "Error";
        }
        else {
          return customers.get(0).getName();
        }
        /*
        try {
            customers = null;   //Remove this if wanting to keep original functionality to make it work
            return customers.get(0).getName();
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
        */
    }
}
