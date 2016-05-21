package io.minlee;

import java.util.ArrayList;

/**
 * Created by minlee on 5/9/16.
 */
public class User {

    ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
    static int accountNumber = 100000;
    private String name, pinCode;

    public User(){

    }
    public User(String name, String pinCode){
        this.name = name;
        this.pinCode = pinCode;
    }

    public String getName(){
        return name;
    }

    public ArrayList<BankAccount> getAccounts(){
        return accounts;
    }

    public boolean checkPassword(String passwordInput){
        if(passwordInput.equals(pinCode)){
            return true;
        }
        else
            return false;
    }

    public void printUserAccountList(){
        for(int i = 0; i < accounts.size(); i++){
            System.out.println(accounts.get(i).getAccountDetails());
        }
        if(accounts.size() < 1){
            System.out.println("No accounts found");
        }
    }

    public BankAccount getPickedAccount(int accountNumber){
        int  accountIndex = -1;
        for(int i = 0; i < accounts.size(); i++){
            if(accountNumber == accounts.get(i).getAccountNumber()){
                accountIndex = i;
            }
        }
        return accounts.get(accountIndex);
    }

    public void openAccount(String accountType, double initialDeposit){
        switch (accountType.toLowerCase()){
            case "checking":
                accounts.add(new CheckingAccount(accountNumber,initialDeposit,name));
                break;
            case "savings":
                accounts.add(new SavingsAccount(accountNumber,initialDeposit,name));
                break;
            case "investment":
                accounts.add(new InvestmentAccount(accountNumber,initialDeposit,name));
                break;
            default:
                System.out.println("Wrong account type entered! No accounts created!");
        }
        accountNumber++;
    }

    public void closeAccount(int searchAccountNumber) {
        for(int i = 0; i < accounts.size(); i++){
            if(searchAccountNumber == accounts.get(i).getAccountNumber()){
                accounts.get(i).setStatus(AccountStatus.CLOSED);
            }
        }
    }

    public BankAccount findAccount(int searchAccountNumber) {
        for(int i = 0; i < accounts.size(); i++){
            if(searchAccountNumber == accounts.get(i).getAccountNumber()){
                return accounts.get(i);
            }
        }
        return null;
    }
}
