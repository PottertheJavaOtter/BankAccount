package io.minlee;

/**
 * Created by minlee on 5/19/16.
 */
public class InvestmentAccount extends BankAccount{

    private double interestRate;
    public InvestmentAccount(int accountNumber, double accountBalance, String accountName ){
        super(accountNumber,accountBalance,accountName);
        interestRate = 3;

    }
}
