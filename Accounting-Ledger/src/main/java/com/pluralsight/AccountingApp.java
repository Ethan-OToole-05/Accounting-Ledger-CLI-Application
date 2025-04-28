package com.pluralsight;

import java.util.Scanner;

public class AccountingApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu();
        Ledger ledger = new Ledger();
        ledger.loadDeposits();
//        ledger.loadPayments();
        String userOption = "";
        Boolean continueAnswer = true;
        System.out.println("Welcome to the account ledger app!");

        while(continueAnswer) {
            menu.displayHome();
            userOption = input.nextLine();
            char option = userOption.trim().toUpperCase().charAt(0);
            switch(option) {
                case 'D':
                    menu.displayAddDeposit();

                    break;
                case 'P':
                    menu.displayMakePayment();
                    break;
                case 'L':
                    menu.displayLedger();
                    menu.displayDeposits();
                    break;
                case 'X':
                    //Exits the program.
                    System.out.println("Thank you for using the app!");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please try again. ");
            }
        }
    }






}
