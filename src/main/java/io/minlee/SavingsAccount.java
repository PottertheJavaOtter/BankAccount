package io.minlee;

/**
 * Created by minlee on 5/19/16.
 */
public class SavingsAccount extends BankAccount{

    private double interestRate;

    public SavingsAccount(int accountNumber, double accountBalance, String accountName ){
        super(accountNumber,accountBalance,accountName);
        interestRate = 1;
    }
}
