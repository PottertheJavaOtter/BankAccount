package io.minlee;

import java.util.ArrayList;

/**
 * Created by minlee on 5/18/16.
 */
public class BankAccount {


    private int accountNumber;
    private AccountType accountType;
    private double accountBalance;
    private String accountName;
    private double interestRate;
    private AccountStatus accountStatus;
    private OverdraftProtection overdraftProtection;
    private ArrayList<String> transaction;

    public BankAccount(AccountType accountType, int accountNumber, double accountBalance, String accountName ){
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountName = accountName;
        accountStatus = AccountStatus.OPEN;
        transaction = new ArrayList<String>();
        if(accountType == AccountType.SAVINGS || accountType == AccountType.INVESTMENT){
            interestRate = 1;
        }
        overdraftProtection = OverdraftProtection.ENABLED;
    }

    public void setAccountName(String name){
        if(accountStatus != AccountStatus.CLOSED){
            this.accountName = name;
        }
    }
    public String getAccountName(){
        return accountName;
    }
    public void setStatus(AccountStatus accountStatus) {
        if(this.accountStatus != AccountStatus.CLOSED) {
            if(accountStatus == AccountStatus.CLOSED){
                if(accountBalance == 0){
                    this.accountStatus = accountStatus;
                }
            }
            else{
                this.accountStatus = accountStatus;
            }
        }

    }

    public AccountStatus getStatus(){
        return accountStatus;
    }


    public double getAccountBalance(){
        if(accountStatus == AccountStatus.FREEZE){
            return 0; //May need to change for the ATM
        }
        return accountBalance;
    }

    public void deductDebit(double debit){
        accountBalance = accountBalance - debit;

    }

    public void addCredit(double credit){
        accountBalance = accountBalance + credit;
    }

    public void setOverdraftProtection(OverdraftProtection overdraftProtection){
        this.overdraftProtection = overdraftProtection;
    }

    public String debit(double debit){
        if(accountStatus == AccountStatus.OPEN){
            if(overdraftProtection == OverdraftProtection.ENABLED) {
                if(accountBalance >= debit) {
                    deductDebit(debit);
                    return "Debit approved!";
                }
            }
            else{
                deductDebit(debit);
                return "Debit approved!";
            }
        }
        return "Debit not approved";
     }

    public String credit(double credit){
        if(accountStatus == AccountStatus.OPEN){
            addCredit(credit);
            return "Credit approved!";
        }
        return "Credit not approved";
    }

    public void makeTransfer(BankAccount transferToAccount, double transferValue){
        deductDebit(transferValue);
        transferToAccount.addCredit(transferValue);
    }

    public String transfer(BankAccount transferToAccount, double transferValue){
        if(accountStatus == AccountStatus.OPEN && transferToAccount.getStatus() == AccountStatus.OPEN){
            if(accountBalance - transferValue >= 0){
                makeTransfer(transferToAccount, transferValue);
                return "Transfer approved!";
            }
        }
        return "Transfer not approved";
    }






}
