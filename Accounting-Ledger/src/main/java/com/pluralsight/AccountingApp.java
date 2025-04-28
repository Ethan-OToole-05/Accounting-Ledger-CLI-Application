package com.pluralsight;

import java.util.Scanner;

public class AccountingApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu();
        String userOption = "";
        char option = userOption.trim().toUpperCase().charAt(0);
        Boolean continueAnswer = true;
        System.out.println("Welcome to the account ledger app!");

        while(continueAnswer) {
            menu.displayHome();
            userOption = input.nextLine();

            switch(option) {
                case 'D':
                    //Prompt to add a deposit info.
                case 'P':
                    //Prompt to make a payment.
                case 'L':
                    //Displays the ledger screen.
                case 'X':
                    //Exits the program.
                    System.exit(0);
                default:
                    System.exit(0);
            }
        }
    }






}
