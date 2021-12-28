package com.griffin.testdome;//import org.junit.Assert;
//import org.junit.Test;

public class Account {
    private double balance;
    private double overdraftLimit;

    public Account(double overdraftLimit) {
        this.balance = 0;
        this.overdraftLimit = overdraftLimit > 0 ? overdraftLimit : 0;
    }

    public boolean deposit(double amount) {
        if(amount >= 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if(this.balance - amount >= -this.overdraftLimit && amount >= 0) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }
}

//public class AccountTest {
//    private double epsilon = 1e-6;
//
//    @Test
//    public void accountCannotHaveNegativeOverdraftLimit() {
//        com.griffin.testdome.Account account = new com.griffin.testdome.Account(-20);
//
//        Assert.assertEquals(0d, account.getOverdraftLimit(), epsilon);
//    }
//}