package io.minlee;

import java.text.NumberFormat;
import java.util.Scanner;

public class Display {

    NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
    Scanner input = new Scanner(System.in);

    public String getBalanceInCurrency(double balance){
        return numberFormat.format(balance);
    }

    public String getUserStringPrompt(String promptMsg){
        printMessage(promptMsg);
        return input.nextLine();
    }
    public double getUserDoublePrompt(String promptMsg){
        printMessage(promptMsg);
        double userInput = input.nextDouble();
        if(input.hasNextLine()){
            input.nextLine();
        }
        return userInput;
    }

    public int getUserIntPrompt(String promptMsg){
        printMessage(promptMsg);
        int userInput = input.nextInt();
        if(input.hasNextLine()){
            input.nextLine();
        }
        return userInput;
    }

    public void printMessage(String msg){
        System.out.print(msg);
    }

}
