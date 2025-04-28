package com.pluralsight;

public class Menu {

    public void displayHome() {
        System.out.println("Please select an option below");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment (Debit)");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
    }

    public void displayLedger() {
        System.out.println("A) Display All Entries");
        System.out.println("D) Display Deposits");
        System.out.println("P) Display Payments");
        System.out.println("R) Display Reports");
    }
}
