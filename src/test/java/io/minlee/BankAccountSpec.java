package io.minlee;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by minlee on 5/18/16.
 */
public class BankAccountSpec {

    BankAccount checkingAccount, savingsAccount, investmentAccount;
    double delta = 10e-15;

    @Before
    public void initialize(){
        checkingAccount = new BankAccount(AccountType.CHECKING, 10000, 10000, "Min");
        savingsAccount = new BankAccount(AccountType.SAVINGS, 10001, 20000, "Joe");
        investmentAccount = new BankAccount(AccountType.INVESTMENT, 10003, 30000,"Evelyn");
    }
    @Test
    public void setAccountNameTest(){
        savingsAccount.setAccountName("Joe");
        String expectedName = "Joe";
        String actualName = savingsAccount.getAccountName();
        assertEquals(expectedName,actualName);
    }
    @Test
    public void setStatusTest(){
        checkingAccount.setStatus(AccountStatus.CLOSED);
        AccountStatus expectedStatus = AccountStatus.OPEN;
        AccountStatus actualStatus = checkingAccount.getStatus();
        assertEquals(expectedStatus,actualStatus);
        checkingAccount.deductDebit(10000);
        checkingAccount.setStatus(AccountStatus.CLOSED);
        expectedStatus = AccountStatus.CLOSED;
        actualStatus = checkingAccount.getStatus();
        assertEquals(expectedStatus,actualStatus);
    }
    @Test
    public void getAccountBalanceFreezeTest(){
        checkingAccount.setStatus(AccountStatus.FREEZE);
        double expectedValue = 0;
        double actualValue = checkingAccount.getAccountBalance();
        assertEquals(expectedValue,actualValue,delta);
    }
    @Test
    public void deductDebitTest(){
        checkingAccount.deductDebit(1000);
        double expectedValue = 9000;
        double actualValue = checkingAccount.getAccountBalance();
        assertEquals(expectedValue,actualValue,delta);
    }
    @Test
    public void addCreditTest(){
        checkingAccount.addCredit(1000);
        double expectedValue = 11000;
        double actualValue = checkingAccount.getAccountBalance();
        assertEquals(expectedValue,actualValue,delta);
    }
    @Test
    public void debitTest(){
        String expectedString = "Debit approved!";
        String actualString = checkingAccount.debit(10000);
        assertEquals(expectedString,actualString);
        expectedString = "Debit not approved";
        actualString = checkingAccount.debit(10000);
        assertEquals(expectedString,actualString);
        checkingAccount.setOverdraftProtection(OverdraftProtection.DISABLED);
        expectedString = "Debit approved!";
        actualString = checkingAccount.debit(10000);
        assertEquals(expectedString,actualString);
    }

    @Test
    public void creditTest(){
        String expectedString = "Credit approved!";
        String actualString = checkingAccount.credit(10000);
        assertEquals(expectedString,actualString);
        checkingAccount.setStatus(AccountStatus.FREEZE);
        expectedString = "Credit not approved";
        actualString = checkingAccount.credit(10000);
        assertEquals(expectedString,actualString);
    }

    @Test
    public void transferTest(){
        String expectedString = "Transfer approved!";
        String actualString = checkingAccount.transfer(savingsAccount, 10000);
        assertEquals(expectedString,actualString);
        expectedString = "Transfer not approved";
        actualString = checkingAccount.transfer(savingsAccount, 10000);
        assertEquals(expectedString,actualString);
        savingsAccount.setStatus(AccountStatus.FREEZE);
        expectedString = "Transfer not approved";
        actualString = savingsAccount.transfer(checkingAccount,100);
        assertEquals(expectedString,actualString);

    }





}
