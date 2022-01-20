package com.griffin.testdome;

import org.junit.Assert;
import org.junit.Test;


/*
Create a simple bank Account class which allows deposits and withdrawals subject to a fixed overdraft protection.
Use the JUNit Assert classes to create a set of unit tests which validate all the Account functionality.
 */
public class Account {
    private double balance;
    private double overdraftLimit;

    /*
    Create a new account with a zero balance and no overdraft limit support.
     */
    public Account() {
        balance = 0.0;
        overdraftLimit = 0.0;
    }

    /*
    Create a new account with a zero balance and overdraft limit. Note that the overdraft
    limit must be a positive number.
     */
    public Account(double overdraftLimit) {
        this.balance = 0;
        this.overdraftLimit = overdraftLimit > 0 ? overdraftLimit : 0;
    }

    /*
    Simple deposit, requiring a positive amount.
     */
    public boolean deposit(double amount) {
        if (amount >= 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    /*
    Withdrawal method, subject to the Account's overdraft protection.
     */
    public boolean withdraw(double amount) {
        // Validate amount - must be > 0.
        if (amount <= 0)
            return false;

        // Determine if amount requested for withdrawal would exceed overdraft limit?
        double newBalance = balance - amount;
        if (newBalance < 0) {
            if ((overdraftLimit + newBalance) < 0) {
                // Surpassed the overdraft limit. Cannot honor withdraw request.
                return false;
            } else {
                balance = newBalance;
                return true;
            }
        } else {
            // Always allow a positive new balance
            balance = newBalance;
            return true;
        }
    }

    public double getBalance() {
        return balance;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public boolean setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
        return true;
    }

    public static void main(String[] args) {
        Account account = new Account();
        account.testRunner();
    }

    public void testRunner() {

        AccountTest accountTest = new AccountTest();
        /*
        Run JUNit tests via main method.
        Alternatively, the AccountTest class can be invoked by testing tools to run JUnit tests.
         */
        try {
            accountTest.testAccount();
            accountTest.testAccountWithOverdraft();
            accountTest.testDeposit();
            accountTest.testWithdraw();
            accountTest.testBalance();
            accountTest.testOverdraft();
        } catch (AssertionError aError) {
            System.out.println("Account JUnit test failed with: " + aError);
        }
    }

    /*
    AccountTest class contains the JUnit annotated tests for the Account class.
     */
    public static class AccountTest {
        private final double epsilon = 1e-6;
        private final double overdraftAmount = 100.00;    // Overdraft allowed = $100.00
        private final double depositAmount = 1000.00;


        @Test(expected = AssertionError.class)
        public void testAccount() {
            Account account = new Account();
            Assert.assertEquals(account.getOverdraftLimit(), 0.0, epsilon);
        }

        @Test
        public void testAccountWithOverdraft() {
            Account account = new Account(overdraftAmount);
            Assert.assertEquals(account.getOverdraftLimit(), overdraftAmount, epsilon);
        }

        @Test
        public void testDeposit() {
            Account account = new Account(overdraftAmount);
            Assert.assertTrue(account.deposit(depositAmount));  // 0+1000 == 1000
            Assert.assertEquals(account.getBalance(), depositAmount, epsilon);  // 1000 == 1000
            double negativeDepositAmount = -1000.00;
            Assert.assertFalse(account.deposit(negativeDepositAmount));
        }
        @Test
        public void testWithdraw() {
            Account account = new Account(overdraftAmount);
            Assert.assertTrue(account.deposit(depositAmount));  // 0+1000 == 1000
            Assert.assertTrue(account.withdraw(depositAmount)); // 1000 - 1000 == 0.0
        }
        @Test
        public void testBalance() {
            Account account = new Account(overdraftAmount);
            Assert.assertTrue(account.deposit(depositAmount));  // 0+1000 == 1000
            Assert.assertTrue(account.withdraw(depositAmount/2)); // 1000 - 500 == 500
            Assert.assertEquals(account.getBalance(), 500.0, epsilon); // == 0.0
        }
        @Test
        public void testOverdraft() {
            Account account = new Account(overdraftAmount/2);
            Assert.assertTrue(account.setOverdraftLimit(overdraftAmount));  // 1000
            Assert.assertEquals(account.getOverdraftLimit(), overdraftAmount, epsilon);  // 1000 == 1000
            Assert.assertFalse(account.withdraw(2*overdraftAmount));  // 1000 - 2000 == -1000. (1000 > 100)
        }
    }
}