package io.minlee;

import io.minlee.BankAccount;
import io.minlee.Display;

/**
 * Created by minlee on 5/7/16.
 */
public class CreditTransaction extends Transaction{

    public CreditTransaction(BankAccount bankAccount, double amountValue, int transactionNumber){
        super(bankAccount, amountValue, transactionNumber);
    }

    public String printTransaction(){
       return super.printTransaction("Deposited");
    }
}
