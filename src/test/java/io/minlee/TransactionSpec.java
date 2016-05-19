package io.minlee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by minlee on 5/19/16.
 */
public class TransactionSpec {

    Transaction transferTransaction, creditTransaction, debitTransaction;


    @Before
    public void initialize(){
        BankAccount checkingAccount = new CheckingAccount(10000, 10000, "Min");
        BankAccount savingsAccount = new SavingsAccount(10001, 20000, "Joe");
        creditTransaction = new CreditTransaction(checkingAccount, 100, 1000);
        debitTransaction = new DebitTransaction(checkingAccount, 100, 1001);
        transferTransaction = new Transaction(checkingAccount, savingsAccount, 100, 1002);
    }

    @Test
    public void checkCreditTransaction(){
        String expectedLog = "Transaction #1000 - Deposited $100.00 to Account #10000";
        String actualLog = creditTransaction.printTransaction().substring(0, 55);
        Assert.assertEquals(expectedLog,actualLog);

    }
    @Test
    public void checkDebitTransaction(){
        String expectedLog = "Transaction #1001 - Withdrew $100.00 to Account #10000";
        String actualLog = debitTransaction.printTransaction().substring(0, 54);
        Assert.assertEquals(expectedLog,actualLog);
    }

    @Test
    public void checkTransferTransaction(){
        String expectedLog = "Transaction #1002 - Transferred $100.00 from Account #10000 to Account #10001";
        String actualLog = transferTransaction.printTransaction().substring(0, 77);
        Assert.assertEquals(expectedLog,actualLog);
    }

}
