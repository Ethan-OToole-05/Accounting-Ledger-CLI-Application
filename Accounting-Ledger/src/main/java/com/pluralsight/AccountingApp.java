package com.pluralsight;

import java.util.Scanner;

public class AccountingApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu();
        Ledger ledger = new Ledger();
        ledger.loadDeposits();
        ledger.loadPayments();
        String userOption = "";
        Boolean continueAnswer = true;
        System.out.println("Welcome to the account ledger app!");

        while (continueAnswer) {
            menu.displayHome();
            userOption = input.nextLine();
            char option = userOption.trim().toUpperCase().charAt(0);
            switch (option) {
                case 'D':
                    menu.displayAddDeposit();
                    String depositInput = input.nextLine();
                    String[] depositItems = depositInput.split(":");
                    Ledger.addDeposit(depositItems[0], depositItems[1], Float.parseFloat(depositItems[2]));
//                    Ledger.addDeposit("testDescription", "testVendor", 18.99f);
                    break;
                case 'P':
                    menu.displayMakePayment();
                    String paymentInput = input.nextLine();
                    String[] paymentItems = paymentInput.split(":");
                    Ledger.addPayment(paymentItems[0], paymentItems[1], Float.parseFloat(paymentItems[2]));
                    break;
                case 'L':
                    menu.displayLedger();
                    userOption = input.nextLine();
                    option = userOption.trim().toUpperCase().charAt(0);
                    switch (option) {
                        case 'A':
                            menu.displayAll();
                            break;
                        case 'D':
                            menu.displayDeposits();
                            break;
                        case 'P':
                            menu.displayPayments();
                            break;
                        case 'R':
                            menu.displayReports();
                            int reportOption = 0;
                            reportOption = input.nextInt();
                            input.nextLine(); //Consuming the CRLF.
                            switch (reportOption) {
                                case 1:
                                    //show month to date.
                                    break;
                                case 2:
                                    //show previous month
                                    break;
                                case 3:
                                    //Show year to date
                                    break;
                                case 4:
                                    //show previous year
                                    break;
                                case 5:
                                    //Search by Vendor
                                    break;
                                case 0:
                                    //Back to report page?
                                    break;
                                default:
                                    System.out.println("Invalid input. Try again");
                            }
                            break;
                        case 'H':
                            menu.displayHome();
                            break;
                    }
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
