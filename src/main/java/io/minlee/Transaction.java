package io.minlee;

import java.time.LocalDateTime;

/**
 * Created by minlee on 5/19/16.
 */
public class Transaction {


    protected double amountValue;
    protected static String date, time;
    protected int transactionNumber;
    protected BankAccount mainAccount;
    private BankAccount transferToBankAccount;
    LocalDateTime localDateTime = LocalDateTime.now();

    public Transaction(BankAccount bankAccount, double amountValue, int transactionNumber){
        this.transactionNumber = transactionNumber;
        this.amountValue = amountValue;
        this.mainAccount = bankAccount;
        if(localDateTime.getMinute() < 10){
            time = localDateTime.getHour() + ":0" + localDateTime.getMinute();
        }
        else{
            time = localDateTime.getHour() + ":" + localDateTime.getMinute();
        }
        date = localDateTime.getMonth() + " "+localDateTime.getDayOfMonth()+", "+localDateTime.getYear();
    }
    public Transaction(BankAccount bankAccount, BankAccount transferToBankAcccount, double amountValue, int transactionNumber){
        this(bankAccount,amountValue,transactionNumber);
        this.transferToBankAccount = transferToBankAcccount;
    }
    public String printTransaction(String transactionType){
        Display display = new Display();
        String log = "";
        log += "Transaction #"+transactionNumber;
        log += " - "+transactionType+" "+display.getBalanceInCurrency(amountValue)+" to Account #"+mainAccount.getAccountNumber();
        log += " on Date: "+date+" Time: "+time;
        return log;
    }
    public String printTransaction(){
        Display display = new Display();
        String log = "";
        log += "Transaction #"+transactionNumber;
        log += " - Transferred "+display.getBalanceInCurrency(amountValue)+" from Account #"+mainAccount.getAccountNumber()+" to Account #"+transferToBankAccount.getAccountNumber();
        log += " on Date: "+date+" Time: "+time;
        return log;
    }
}

