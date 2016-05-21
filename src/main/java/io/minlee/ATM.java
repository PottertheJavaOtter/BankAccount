package io.minlee;


import java.util.ArrayList;

public class ATM {

    private ArrayList<User> users = new ArrayList<User>();
    User min = new User("min","0000");

    Display display = new Display();

    public String userOptionsList = "1: Open an Account\n"+
                                    "2: Close an Account\n"+
                                    "3: Access an Account\n"+
                                    "4: Print out all Accounts\n"+
                                    "0: Log out\n";

    public String userAccountOptions = "1:  Check current Balance\n"+
            "2:  Withdraw from Account\n"+
            "3:  Deposit to Account\n"+
            "4:  Transfer money from Account\n"+
            "5:  Print Transaction History\n"+
            "0:  Exit Account Options";

    public void logIn(){
        users.add(min); // Delete this when finished
        String userName = display.getUserStringPrompt("Enter your username: ");
        String pinCode = display.getUserStringPrompt("Enter your Pin Code: ");
        boolean loggingIn = false;
        //loops through the list of users in order to see if there is a user match
        User currentUser = new User();
        for (int i = 0; i < users.size(); i++) {
            if(userName.equals(users.get(i).getName())) {
                currentUser = users.get(i);
                if(currentUser.checkPassword(pinCode)) {
                    loggingIn = true;
                }
            }
        }
        if(loggingIn) {
            runCurrentUserSession(currentUser);
        }
        else{
            System.out.println("Incorrect username or PIN, exiting login\n");
        }
    }
    public void runCurrentUserSession(User currentUser){
        System.out.println("\nWelcome "+currentUser.getName()+"!");
        UserInputHandler userInputHandler = new UserInputHandler();
        System.out.println("\nThese are your current accounts:");
        currentUser.printUserAccountList();
        int userOption = -1;
        while(userOption!=0){
            System.out.println("\nOptions -\n"+userOptionsList);
            userOption = display.getUserIntPrompt("What would you like to do?: ");
            switch (userOption){
                case 0:
                    System.out.println("Logging Out\n");
                    break;
                case 1://Open an account
                    currentUser.openAccount(display.getUserStringPrompt("What account? (Checking, Savings, Investment): "), display.getUserDoublePrompt("What is your initial investment?: "));
                    break;
                case 2://Close an account
                    currentUser.closeAccount(display.getUserIntPrompt("Enter the account number you want to close: "));
                    break;
                case 3://Access an account
                    BankAccount accessedAccount = currentUser.findAccount(display.getUserIntPrompt("Enter the account number you want to access: "));
                    if(accessedAccount != null){
                        delegateUserInput(accessedAccount);
                    }
                    else {
                        System.out.println("Account not found");
                    }
                    break;
                case 4://Print out all accounts
                    System.out.println("\nThese are your current accounts:");
                    currentUser.printUserAccountList();
                    break;

            }
        }
        initialStart();
    }

    public void delegateUserInput(BankAccount currentAccount){
        int readInt = 4;
        while(readInt!=0){
            System.out.println("\n"+userAccountOptions);
            readInt = display.getUserIntPrompt("Enter an option: ");
            switch (readInt){
                case 1: //Check current Balance
                    System.out.println("\nCurrent Balance: "+ display.getBalanceInCurrency(currentAccount.getAccountBalance()));
                    break;
                case 2: //Withdraw from Account
                    String withdrawApproval = currentAccount.debit(display.getUserDoublePrompt("How much do you want to withdraw?: "));
                    System.out.println("\n"+withdrawApproval);
                    break;
                case 3: //Deposit to Account
                    String depositApproval = currentAccount.debit(display.getUserDoublePrompt("How much do you want to deposit?: "));
                    System.out.println("\n"+depositApproval);
                    break;
                case 4: //Transfer from your Account
                    BankAccount transferToAccount = searchAllAccounts(display.getUserIntPrompt("Enter the account number you want to transfer to: "), users);
                    if(transferToAccount == null){
                        System.out.println("That Account does not exist, no transfer made");
                    }
                    else {
                        String transferApproval =currentAccount.transfer(transferToAccount,display.getUserDoublePrompt("How much do you want to transfer?: "));
                        System.out.println(transferApproval);
                    }
                    break;
                case 5: //Print Transaction History
                    currentAccount.printTransactionHistory();
                    break;
                case 0: //End account access
                    System.out.println("Ending account access");
                    break;
            }
        }
    }

    public void createUser(){
        String userName = display.getUserStringPrompt("Enter your desired user name: ");
        String pinCode  = display.getUserStringPrompt("Enter your desired pin code (4 digits): ");
        User newUser = new User(userName,pinCode);
        System.out.println("Thank you for opening a user account with IBM");
        users.add(newUser);
        initialStart();

    }

    public BankAccount searchAllAccounts(int accountNumberToSearch, ArrayList<User> users){
        for(int i = 0; i < users.size(); i++){
            for(int j = 0; j < users.get(i).getAccounts().size(); j++){
                if(accountNumberToSearch == users.get(i).getAccounts().get(j).getAccountNumber()){
                    return users.get(i).getAccounts().get(j);
                }
            }
        }
        return null;
    }

    public void initialStart(){
        int option = -1;
        System.out.println("Welcome to IBM - International Bank of Min");
        System.out.println("                                ___                ");
        System.out.println("                 _____         /\\  \\             ");
        System.out.println("    ___         /::\\  \\       |::\\  \\          ");
        System.out.println("   /\\__\\       /:/\\:\\  \\      |:|:\\  \\      ");
        System.out.println("  /:/__/      /:/ /::\\__\\   __|:|\\:\\  \\       ");
        System.out.println(" /::\\  \\     /:/_/:/\\:|__| /::::|_\\:\\__\\     ");
        System.out.println(" \\/\\:\\  \\__  \\:\\/:/ /:/  / \\:\\~~\\  \\/__/ ");
        System.out.println("    \\:\\/\\__\\  \\::/_/:/  /   \\:\\  \\         ");
        System.out.println("     \\::/  /   \\:\\/:/  /     \\:\\  \\          ");
        System.out.println("     /:/  /     \\::/  /       \\:\\__\\           ");
        System.out.println("     \\/__/       \\/__/         \\/__/            \n\n");
        while(option!=0){
            option = display.getUserIntPrompt("1: LogIn\n2: Create User\n3: Exit\nWhat would you like to do?: ");
            switch (option){
                case 1:
                    logIn();
                    break;
                case 2:
                    createUser();
                    break;
                case 0:
                    System.out.println("Exiting IBM ATM");
                    break;
            }
        }
    }


}
