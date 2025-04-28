package com.pluralsight;

public class Menu {

    public void displayHome() {
        System.out.println("Please select an option below");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment (Debit)");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        //Takes users input
    }

    public void displayLedger() {
        System.out.println("A) Display All Entries");
        System.out.println("D) Display Deposits");
        System.out.println("P) Display Payments");
        System.out.println("R) Display Reports");
        //Takes users input
    }

    public void displayAddDeposit() {
        System.out.println("Please enter deposit information below.");
        System.out.println("Format is description:vendor:amount ");
        //Takes user input
    }

    public void displayMakePayment() {
        System.out.println("Please enter the payment information below. ");
        System.out.println("Format is description:vendor:amount ");
        //Takes the users input.
    }

    public void displayReports() {
        System.out.println("Which format would you like the reports to be in? ");
        System.out.println("1) Month To Date ");
        System.out.println("2) Previous Month ");
        System.out.println("3) Year To Date ");
        System.out.println("4) Previous Year ");
        System.out.println("5) Search by Vendor ");
        System.out.println("0) Back to Ledger Page");
        //Takes the users input.
    }

}
