package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class AccountTest {
  
    private static final double DOUBLE_DELTA = 1e-15;
  
    @Test
    public void testMaxiAccount() {
        Account maxiAccount = new Account(Account.MAXI_SAVINGS);
        assertEquals(maxiAccount.getAccountType(), Account.MAXI_SAVINGS);
    }
    
    @Test
    public void testSavingsAccount() {
        Account savingsAccount = new Account(Account.SAVINGS);
        assertEquals(savingsAccount.getAccountType(), Account.SAVINGS);
    }
    
    @Test
    public void testCheckingsAccount() {
        Account checkingAccount = new Account(Account.CHECKING);
        assertEquals(checkingAccount.getAccountType(), Account.CHECKING);
    }
    
    @Test
    public void testBalance() {
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        checkingAccount.deposit(4000.0);
        assertEquals(4000.0, checkingAccount.getBalance(), DOUBLE_DELTA);
    }
    
    @Test
    public void testDeposit() {
        Account checkingAccount = new Account(Account.CHECKING);
        checkingAccount.deposit(4000.0);
        assertEquals(4000.0, checkingAccount.getBalance(), DOUBLE_DELTA);
    }
    
    @Test
    public void testWithdraw() {
        Account checkingAccount = new Account(Account.CHECKING);
        checkingAccount.deposit(4000.0);
        checkingAccount.withdraw(400.0);
        assertEquals(3600.0, checkingAccount.getBalance(), DOUBLE_DELTA);
    }
    
    @Test
    public void testsumTransactions() {
        Account checkingAccount = new Account(Account.CHECKING);
        checkingAccount.deposit(4000.0);
        checkingAccount.withdraw(1500.0);
        assertEquals(2500.0, checkingAccount.sumTransactions(), DOUBLE_DELTA);
    }
    
    @Test
    public void testMaxiAccountinterestEarned() {
        Account maxiAccount = new Account(Account.MAXI_SAVINGS);
        maxiAccount.deposit(4000.0);
        assertEquals(4000.0 * 0.05, maxiAccount.interestEarned(), DOUBLE_DELTA);
    }
    
    @Test
    public void testMaxiAccount2interestEarned() {
        Account maxiAccount = new Account(Account.MAXI_SAVINGS);
        maxiAccount.deposit(4000.0);
        maxiAccount.withdraw(1000.0);
        assertEquals(3000.0 * 0.001, maxiAccount.interestEarned(), DOUBLE_DELTA);
    }
    
    @Test
    public void testSavingsAccountinterestEarned() {
        Account savingsAccount = new Account(Account.SAVINGS);
        savingsAccount.deposit(4000.0);
        assertEquals(1 + (3000) * 0.002, savingsAccount.interestEarned(), DOUBLE_DELTA);
    }
    
    @Test
    public void testSavingsAccount2interestEarned() {
        Account savingsAccount = new Account(Account.SAVINGS);
        savingsAccount.deposit(1000.0);
        assertEquals(1000 * 0.001, savingsAccount.interestEarned(), DOUBLE_DELTA);
    }
    
    @Test
    public void testCheckingsAccountinterestEarned() {
        Account checkingAccount = new Account(Account.CHECKING);
        checkingAccount.deposit(4000.0);
        assertEquals(4000.0 * 0.001, checkingAccount.interestEarned(), DOUBLE_DELTA);
    }
    
    @Test
    public void testAccureDailyInterest() {
        Account checkingAccount = new Account(Account.CHECKING);
        checkingAccount.deposit(4000.0);
        checkingAccount.accrueDailyInterest();
        assertTrue(checkingAccount.getBalance() > 4000.0);
    }
}
