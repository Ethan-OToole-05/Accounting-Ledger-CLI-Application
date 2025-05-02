package com.pluralsight;

public class Menu {

    //Displays the home screen.
    public void displayHome() {
        System.out.println();
        System.out.println("Home");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Please select an option below");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment (Debit)");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.print("Option Selection: ");
        //Takes user input
    }

    //Displays the ledger screen.
    public void displayLedger() {
        System.out.println();
        System.out.println("Ledger Page. ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Please select an option below");
        System.out.println("A) Display All Entries");
        System.out.println("D) Display Deposits");
        System.out.println("P) Display Payments");
        System.out.println("R) Display Reports");
        System.out.println("H) Back to Home Screen");
        System.out.print("Option Selection: ");
        //Takes users input
    }

    //Displays the add deposit screen.
    public void displayAddDeposit() {
        System.out.println();
        System.out.println("Please enter deposit information below.");
        //Takes user input after.
    }

    //Displays the make payment screen.
    public void displayMakePayment() {
        System.out.println();
        System.out.println("Please enter the payment information below. ");
        //Takes the users input after.
    }

    //Displays the deposits screen.
    public void displayDeposits() {
        System.out.println("Here are all deposits: ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Ledger.getDeposits();
        //Displays each deposit.
    }

    //Displays the payments screen.
    public void displayPayments() {
        System.out.println("Here are all the payments: ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Ledger.getPayments();
        //Displays each payment.
    }

    //Displays all the transactions screen.
    public void displayAll() {
        System.out.println("Here are all transactions: ");
        System.out.println("Deposits: ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Ledger.getDeposits();
        System.out.println("Payments: ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Ledger.getPayments();
        //Displays all transactions.
    }

    //Displays the reports options screen.
    public void displayReports() {
        System.out.println();
        System.out.println("Reports Page");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Which format would you like the reports to be in? ");
        System.out.println("1) Month To Date ");
        System.out.println("2) Previous Month ");
        System.out.println("3) Year To Date ");
        System.out.println("4) Previous Year ");
        System.out.println("5) Search by Vendor ");
        System.out.println("6) Custom Search");
        System.out.println("0) Back to Ledger Page");
        System.out.print("Option Selection: ");
        //Takes the users input.
    }

    //Displays all transactions from month to date.
    public void displayMonthToDate() {
        System.out.println("These are the current reports from the beginning of the month to now: ");
        Ledger.monthToDate();
        //Displays all transactions month to date.
    }

    //Displays all the transactions from the previous month.
    public void displayPreviousMonthToDate() {
        System.out.println("These are the current reports from the previous month: ");
        Ledger.previousMonthToDate();
        //Displays all transactions from the previous month to date.
    }

    //Displays all the transactions from the beginning of the year to date.
    public void displayYearToDate() {
        System.out.println("These are the current reports from the beginning of the year to now: ");
        Ledger.yearToDate();
        //Displays all transactions from the current year to date.
    }

    //Displays all the transactions from last year.
    public void displayPreviousYearToDate() {
        System.out.println("These are the current reports from the previous year: ");
        Ledger.previousYearToDate();
        //Displays all transactions from the previous year to date.
    }

    //Displays all the transactions based on a vendor.
    public void displaySearchByVendor(String vendor) {
        System.out.println("These are the current reports from the searched vendor: ");
        Ledger.searchByVendor(vendor);
        //Displays all transactions based on vendor.
    }

    //Displays all transactions based on a user's search.
    public void displayCustomSearch() {
        System.out.println("Please enter the search values you want: ");
        System.out.println();
        //Displays all transactions based on user search inputs.
    }
}
