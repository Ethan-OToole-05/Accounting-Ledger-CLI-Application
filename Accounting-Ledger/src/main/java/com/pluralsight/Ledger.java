package com.pluralsight;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Ledger {
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static TimeStamp timeStamp = new TimeStamp();
    private static String fileName = "src/main/resources/transactions.csv";
    private static LocalDateTime compareDateTime;

    public Ledger() {

    }

    //Loading the transactions into our app for use.
    public ArrayList<Transaction> loadTransactions() {

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);

            String input;

            while ((input = reader.readLine()) != null) {
                if (input.startsWith("Date") || input.startsWith("date")) {
                    continue;
                }
                String[] items = input.split("\\|");
                LocalDate date = LocalDate.parse(items[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalTime time = LocalTime.parse(items[1], DateTimeFormatter.ofPattern("HH:mm:ss"));
                String description = items[2];
                String vendor = items[3];
                float amount = Float.parseFloat(items[4]);
                transactions.add(new Transaction(date, time, amount, description, vendor));

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(transactions, Comparator.comparing(Transaction::getDateTime).reversed());
        return transactions;
    }

    //Adding a new deposit to add to the transactions list.
    public static void addDeposit(String description, String vendor, float amount) {
        try {
            //If amount is negative we will make it positive regardless.
            if (amount < 0) {
                amount = Math.abs(amount);
            }
            //If description or vendor fields are empty we will not accept input.
            if (description.isEmpty() || vendor.isEmpty()) {
                System.out.println("Invalid Input. Please try again.");
            } else {
                FileWriter fileWriter = new FileWriter(fileName, true);
                BufferedWriter writer = new BufferedWriter(fileWriter);

                //Gets time and date right now.
                LocalDateTime now;
                now = timeStamp.getTimestamp();
                String formattedTime = timeStamp.formatTimestamp(now);

                writer.write(formattedTime + "|" + description + "|" + vendor + "|" + amount);
                writer.newLine();
                writer.close();
                transactions.add(new Transaction(amount, description, vendor));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Grab only our deposits from the transactions list.
    public static void getDeposits() {
        Collections.sort(transactions, Comparator.comparing(Transaction::getDateTime).reversed());
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                continue;
            } else {
                System.out.println(transaction);
            }
        }
    }


    //Always make payment amount negative if userinput is positive? Instead of saying invalid input?
    public static void addPayment(String description, String vendor, float amount) {
        try {
            //If amount is positive we will make it negative for payments.
            if (amount > 0) {
                amount = -Math.abs(amount);
            }
            //If description or vendor fields are empty we will not accept input.
            if (description.isEmpty() || vendor.isEmpty()) {
                System.out.println("Invalid input. Please try again.");
            } else {
                FileWriter fileWriter = new FileWriter(fileName, true);
                BufferedWriter writer = new BufferedWriter(fileWriter);

                LocalDateTime now;
                now = timeStamp.getTimestamp();
                String formattedTime = timeStamp.formatTimestamp(now);

                writer.write(formattedTime + "|" + description + "|" + vendor + "|" + amount);
                writer.newLine();
                writer.close();
                transactions.add(new Transaction(amount, description, vendor));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Grab only the payments from the transactions list.
    public static void getPayments() {
        Collections.sort(transactions, Comparator.comparing(Transaction::getDateTime).reversed());
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                continue;
            } else {
                System.out.println(transaction);
            }
        }
    }

    //Grab the month to date transactions.
    public static void monthToDate() {
        boolean transactionFound = false;
        Month month = LocalDate.now().getMonth();
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getMonth().equals(month)) {
                transactionFound = true;
                System.out.println(transaction);
            }
        }
        if (!transactionFound) {
            System.out.println("Transaction was not found.");
        }
    }

    //Grab the previous month to date transactions.
    public static void previousMonthToDate() {
        boolean transactionFound = false;
        Month month = LocalDate.now().getMonth().minus(1);
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getMonth().equals(month)) {
                transactionFound = true;
                System.out.println(transaction);
            }
        }
        if (!transactionFound) {
            System.out.println("Transaction not found.");
        }
    }

    //Grab the year to date transactions
    public static void yearToDate() {
        boolean transactionFound = false;
        int year = LocalDate.now().getYear();
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getYear() == year) {
                transactionFound = true;
                System.out.println(transaction);
            }
        }
        if (!transactionFound) {
            System.out.println("Transaction not found.");
        }
    }

    //Grab the previous year to date transactions
    public static void previousYearToDate() {
        boolean transactionFound = false;
        int previousYear = LocalDate.now().getYear();
        previousYear--;
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getYear() == previousYear) {
                transactionFound = true;
                System.out.println(transaction);
            }
        }
        if (!transactionFound) {
            System.out.println("Transaction not found.");
        }
    }

    //Search by vendor to get all transactions to match the vendor value.
    public static void searchByVendor(String vendor) {
        String searchVendor = vendor;
        boolean vendorFound = false;
        //What is display if we can't find the vendor?
        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(searchVendor)) {
                vendorFound = true;
                System.out.println(transaction);
            }
        }
        if (!vendorFound) {
            System.out.println("Transaction with vendor not found.");
        }
    }

    public static void customSearch(LocalDate startDate, LocalDate endDate, String description, String vendor, Float amount) {
        //Results will hold what the user wants filtered in an array of Boolean values.
        ArrayList<Boolean> results = new ArrayList<>(transactions.size());

        //Size up the array by seeing how many transactions we have. We are assuming every transaction is true first.
        for (int i = 0; i < transactions.size(); i++) {
            results.add(true);
        }

        //For loop to see which field has something in it and see if the search matches a field.
        for (int i = 0; i < transactions.size(); i++) {
            //Boolean flag to see each field to see if it is a full match.
            boolean meetsSearch = true;
            Transaction transaction = transactions.get(i);
            if (endDate != null && transaction.getDate().isAfter(endDate)) {
                meetsSearch = false;
            }
            //Checks if the transaction's start date is after the start date. If not it is false
            if (startDate != null && transaction.getDate().isBefore(startDate)) {
                meetsSearch = false;
            }
            //How to keep adding on to the end result?
            //Checks description is not empty, and if it does not match our transaction's description the search is false.
            if (!description.isEmpty() && !transaction.getDescription().equalsIgnoreCase(description)) {
                meetsSearch = false;
            }
            //Checks vendor is not empty, and if it does not match our transaction's vendor the search is false.
            if (!vendor.isEmpty() && !transaction.getVendor().equalsIgnoreCase(vendor)) {
                meetsSearch = false;
            }
            //Search up to a certain amount. If its over it is false.
            if (amount != null && transaction.getAmount() > amount) {
                //Different toggle for amounts.
                meetsSearch = false;
            }
            //Sets transaction to false if it does not meet the search.
            if (!meetsSearch) {
                results.set(i, false);
            }
            //Different toggle for amounts.
        }
        //Print out the results of each transaction that matched the filtered results.
        for (int i = 0; i < transactions.size(); i++) {
            if (results.get(i)) {
                System.out.println(transactions.get(i));
            }
        }
//            System.out.println(transaction);
//            results.add(transaction);
//        System.out.println(filteredResults);

    }

//        return results;
}

