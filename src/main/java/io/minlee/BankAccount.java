package io.minlee;

import java.util.ArrayList;

/**
 * Created by minlee on 5/18/16.
 */
public class BankAccount {

    private int accountNumber;
    private AccountType accountType;
    private double accountBalance;
    private String accountHolderName;
    private double interestRate;
    private AccountStatus accountStatus;
    private OverdraftProtection overdraftProtection;
    private ArrayList<String> transaction;

    public enum AccountType{CHECKING, SAVINGS, INVESTMENT}
    public enum AccountStatus{OPEN, CLOSED, FREEZE}
    public enum OverdraftProtection{OPEN, CLOSED, FREEZE}

    public BankAccount(AccountType accountType, int accountNumber, double accountBalance ){
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        accountStatus = AccountStatus.OPEN;
        transaction = new ArrayList<String>();
    }

    public get






}
