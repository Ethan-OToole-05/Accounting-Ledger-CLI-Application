package com.pluralsight;

import java.time.LocalDate;
import java.util.Scanner;

public class AccountingApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Menu menu = new Menu();
        Ledger ledger = new Ledger();
        ledger.loadTransactions();
        String userOption = "";
        Boolean continueAnswer = true;
        System.out.println("Welcome to the account ledger app!");

        while (continueAnswer) {
            menu.displayHome();
            userOption = input.nextLine();
            if (userOption.isEmpty()) {
                System.out.println("Invalid input try again please.");
            } else {
                char option = userOption.trim().toUpperCase().charAt(0);
                switch (option) {
                    case 'D':
                        try {
                            menu.displayAddDeposit();
                            System.out.print("Description: ");
                            String descriptionDepositInput = input.nextLine();
                            System.out.print("Vendor: ");
                            String vendorDepositInput = input.nextLine();
                            System.out.print("Amount: ");
                            String amountDepositInput = input.nextLine();

                            Float convertDepositAmount = Float.parseFloat(amountDepositInput);
                            String depositInputs = descriptionDepositInput + ":" + vendorDepositInput + ":" + convertDepositAmount;

                            String[] depositItems = depositInputs.split(":");
                            if (depositItems[0].isEmpty() || depositItems[1].isEmpty() || depositItems[2].isEmpty()) {
                                System.out.println("Invalid input. Make sure all fields have valid input.");
                            } else {
                                Ledger.addDeposit(depositItems[0], depositItems[1], Float.parseFloat(depositItems[2]));
                                System.out.println("Thank you. Deposit added.");
                            }
                        } catch (Exception e) {
//                            System.out.println(e.getMessage());
                            System.out.println("Invalid input. Please try again");
                        }
                        break;
                    case 'P':
                        try {
                            menu.displayMakePayment();
                            System.out.print("Description: ");
                            String descriptionPaymentInput = input.nextLine();
                            System.out.print("Vendor: ");
                            String vendorPaymentInput = input.nextLine();
                            System.out.print("Amount: ");
                            String amountPaymentInput = input.nextLine();

                            Float convertPaymentAmount = Float.parseFloat(amountPaymentInput);
                            String paymentInputs = descriptionPaymentInput + ":" + vendorPaymentInput + ":" + convertPaymentAmount;

                            String[] paymentItems = paymentInputs.split(":");
                            if (paymentItems[0].isEmpty() || paymentItems[1].isEmpty() || paymentItems[2].isEmpty()) {
                                System.out.println("Invalid input. Please try again.");
                            } else {
                                Ledger.addPayment(paymentItems[0], paymentItems[1], Float.parseFloat(paymentItems[2]));
                                System.out.println("Thank you! Payment added.");
                            }
                        } catch (Exception e) {
//                            System.out.println(e.getMessage());
                            System.out.println("Invalid input. Please try again.");
                        }
                        break;
                    case 'L':
                        //Loops until the H option is selected to go back to the home screen.
                        while (option != 'H') {
                            try {
                                menu.displayLedger();
                                userOption = input.nextLine();
                                option = userOption.trim().toUpperCase().charAt(0);
                            } catch (Exception e) {
                                System.out.println("Invalid input. Please try again.");
                            }
                            switch (option) {
                                //Ledger Screen
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
                                    int reportOption = 1;
                                    //Loop until report option is 0 to go back to the ledger.
                                    while (reportOption != 0) {
                                        try {
                                            menu.displayReports();
                                            reportOption = input.nextInt();
                                            input.nextLine(); //Consuming the CRLF.
                                        } catch (Exception e) {
                                            System.out.println("Invalid input. Please try again.");
                                        }
                                        switch (reportOption) {
                                            //Reports screen
                                            case 1:
                                                //show month to date.
                                                menu.displayMonthToDate();
                                                break;
                                            case 2:
                                                //show previous month
                                                menu.displayPreviousMonthToDate();
                                                break;
                                            case 3:
                                                //Show year to date
                                                menu.displayYearToDate();
                                                break;
                                            case 4:
                                                //show previous year
                                                menu.displayPreviousYearToDate();
                                                break;
                                            case 5:
                                                try {
                                                    //Search by Vendor
                                                    System.out.print("Please enter the name of the vendor: ");
                                                    String vendor = input.nextLine().trim();
                                                    menu.displaySearchByVendor(vendor);
                                                } catch (Exception e) {
//                                            System.out.println(e.getMessage());
                                                    System.out.println("Invalid vendor. Please try again.");
                                                }
                                                break;
                                            case 6:
                                                try {
                                                    //Custom search Challenge
                                                    menu.displayCustomSearch();
                                                    System.out.print("Start Date (Year-Month-Date): ");
                                                    String startDate = input.nextLine();
                                                    System.out.print("End Date (Year-Month-Date): ");
                                                    String endDate = input.nextLine();
                                                    System.out.print("Description: ");
                                                    String description = input.nextLine();
                                                    System.out.print("Vendor: ");
                                                    String vendor = input.nextLine();
                                                    System.out.print("Amount to search up to (Ex: all transactions up to $20.00): ");
                                                    String amount = input.nextLine();

                                                    LocalDate inputStartDate = LocalDate.parse(startDate);
                                                    LocalDate inputEndDate = LocalDate.parse(endDate);
                                                    Float inputAmount = null;
                                                    if (!amount.isEmpty()) {
                                                        inputAmount = Float.parseFloat(amount);
                                                    }
                                                    Ledger.customSearch(inputStartDate, inputEndDate, description, vendor, inputAmount);

                                                } catch (Exception e) {
//                                            System.out.println(e.getMessage());
                                                    System.out.println("Invalid search. Please try again.");
                                                }
                                                break;
                                            case 0:
                                                break;
                                            default:
                                                System.out.println("Invalid input. Please try again.");
                                        }
                                    }
                                case 'H':
                                    break;
                                default:
                                    System.out.println("Invalid input. Please try again.");
                            }
                        }
                        break;
                    case 'X':
                        //Exits the program.
                        System.out.println("Thank you for using the app!");
                        input.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid input. Please try again. ");
                }
            }
        }
    }
}
