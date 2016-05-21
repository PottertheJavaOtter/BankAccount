package io.minlee;

import java.util.ArrayList;

/**
 * Created by minlee on 5/18/16.
 */
abstract public class BankAccount {


    private int accountNumber;
    private double accountBalance;
    private String accountName;
    private AccountStatus accountStatus;
    private OverdraftProtection overdraftProtection;
    private ArrayList<Transaction> ledger;
    private static int transactionNumber = 10000;

    public BankAccount(int accountNumber, double accountBalance, String accountName ){
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountName = accountName;
        accountStatus = AccountStatus.OPEN;
        ledger = new ArrayList<>();
        overdraftProtection = OverdraftProtection.ENABLED;
    }

    public int getAccountNumber(){
        return accountNumber;
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
                else {
                    System.out.println("Cannot close account with a balance greater than 0");
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
                    addDebitTransaction(debit);
                    return "Debit approved!";
                }
            }
            else{
                deductDebit(debit);
                addDebitTransaction(debit);
                return "Debit approved!";
            }
        }
        return "Debit not approved";
     }

    public String credit(double credit){
        if(accountStatus == AccountStatus.OPEN){
            addCredit(credit);
            addCreditTransaction(credit);
            return "Credit approved!";
        }
        return "Credit not approved";
    }

    private void addCreditTransaction(double credit) {
        ledger.add(new CreditTransaction(this, credit, transactionNumber));
        transactionNumber++;
    }
    private void addDebitTransaction(double debit) {
        ledger.add(new DebitTransaction(this, debit, transactionNumber));
        transactionNumber++;
    }
    private void addTransferTransaction(BankAccount transferToAccount, double transferValue) {
        ledger.add(new Transaction(this, transferToAccount, transferValue, transactionNumber));
        transferToAccount.getLedger().add(new Transaction(this, transferToAccount, transferValue, transactionNumber));
        transactionNumber++;
    }

    public void makeTransfer(BankAccount transferToAccount, double transferValue){
        deductDebit(transferValue);
        transferToAccount.addCredit(transferValue);
    }

    public String transfer(BankAccount transferToAccount, double transferValue){
        if(accountStatus == AccountStatus.OPEN && transferToAccount.getStatus() == AccountStatus.OPEN){
            if(accountBalance - transferValue >= 0){
                makeTransfer(transferToAccount, transferValue);
                addTransferTransaction(transferToAccount, transferValue);
                return "Transfer approved!";
            }
        }
        return "Transfer not approved";
    }

    public String getAccountDetails(){
        String accountDetails = "Account #"+accountNumber;
        accountDetails+=" "+ this.getClass().getSimpleName();
        accountDetails+=" "+ getStatus().toString();
        return accountDetails;
    }

    public void printTransactionHistory(){
        for(int i = 0; i < ledger.size(); i++){
            System.out.println(ledger.get(i).printTransaction());
        }
    }

    public ArrayList<Transaction> getLedger(){
        return ledger;
    }


}
