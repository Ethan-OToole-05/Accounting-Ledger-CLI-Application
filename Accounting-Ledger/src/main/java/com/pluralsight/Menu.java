package com.pluralsight;


import java.util.Scanner;

public class Menu {
    private static Scanner scanner;

    public void displayHome() {
        System.out.println();
        System.out.println("Please select an option below");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment (Debit)");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.print("Option Selection: ");
        //Code below has an error taking in null on line 19.
//        String stringOption = "";
//        stringOption = scanner.nextLine();
//        char option = stringOption.trim().toUpperCase().charAt(0);
//        switch (option) {
//            case 'D':
//                displayAddDeposit();
//                break;
//            case 'P':
//                displayMakePayment();
//                break;
//            case 'L':
//                displayLedger();
//                break;
//            case 'X':
//                System.out.println("Thank you for using the app!");
//                System.exit(0);
//            default:
//                System.out.println("Invalid input please try again.");
//
//        }
    }

    public void displayLedger() {
        System.out.println();
        System.out.println("A) Display All Entries");
        System.out.println("D) Display Deposits");
        System.out.println("P) Display Payments");
        System.out.println("R) Display Reports");
        System.out.print("Option Selection: ");
        //Takes users input
    }

    public void displayAddDeposit() {
        System.out.println();
        System.out.println("Please enter deposit information below.");
        System.out.println("Format is description:vendor:amount ");
        String input = "";

        System.out.print("Deposit Input: ");
        //Takes user input
    }

    public void displayMakePayment() {
        System.out.println();
        System.out.println("Please enter the payment information below. ");
        System.out.println("Format is description:vendor:amount ");
        System.out.print("Payment Input: ");
        //Takes the users input.
    }

    public void displayDeposits() {
        System.out.println("Here are all deposits: ");
        Ledger.getDeposits();

    }

    public void displayPayments() {
        System.out.println("Here are all the payments: ");
        Ledger.getPayments();
    }

    public void displayAll() {
        System.out.println("Here are all transactions: ");
        Ledger.getTransactions();
    }


    public void displayReports() {
        System.out.println();
        System.out.println("Which format would you like the reports to be in? ");
        System.out.println("1) Month To Date ");
        System.out.println("2) Previous Month ");
        System.out.println("3) Year To Date ");
        System.out.println("4) Previous Year ");
        System.out.println("5) Search by Vendor ");
        System.out.println("0) Back to Ledger Page");
        System.out.print("Option Selection: ");
        //Takes the users input.
    }

//    public void displayMonthToDate() {
//        System.out.println("These are the current reports from the beginning of the month to now: ");
//        System.out.println("Heres the deposits: ");
//        for (Deposit deposit : Ledger.getDeposits()){
//            //need display month method.
//            System.out.println(deposit);
//        }
//    }

}
