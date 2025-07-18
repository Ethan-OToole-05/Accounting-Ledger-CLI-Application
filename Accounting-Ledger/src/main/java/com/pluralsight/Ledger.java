package com.pluralsight;

import java.io.*;
import java.time.*;
import java.sql.*;
import java.util.*;

public class Ledger {
    private static ArrayList<Transaction> transactions = new ArrayList<>();
        private static TimeStamp timeStamp = new TimeStamp();
        private static String fileName = "src/main/resources/transactions.csv";
        private static LocalDateTime compareDateTime;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/transactions_db"; // Use your database name
    private static final String USER = "root"; // Replace with your DB username
    private static final String PASS = "yearup"; // Replace with your DB password

    public Ledger() {

    }

    //Loading the transactions into our app for use.
    public ArrayList<Transaction> loadTransactions() {
        transactions.clear(); // Clear existing transactions to load fresh from DB

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM transactions ORDER BY date DESC, time DESC")) {

            System.out.println("Loading transactions from database...");

            while (rs.next()) {
                LocalDate date = rs.getDate("date").toLocalDate();
                LocalTime time = rs.getTime("time").toLocalTime();
                String description = rs.getString("description");
                String vendor = rs.getString("vendor");
                float amount = rs.getFloat("amount");

                transactions.add(new Transaction(date, time, amount, description, vendor));
            }
            System.out.println("Transactions loaded successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }


    //Adding a new deposit to add to the transactions list.
    public static void addDeposit(String description, String vendor, float amount) {
        // Input validation remains important
        if (description.isEmpty() || vendor.isEmpty() || amount <= 0) { // Deposits must be positive
            System.out.println("Invalid Input. Description and vendor cannot be empty, and amount must be positive. Please try again.");
            return; // Exit method if input is invalid
        }

        // Ensure amount is positive for a deposit, even if somehow passed negative
        amount = Math.abs(amount);

        // Get current timestamp for date and time columns
        LocalDateTime now = timeStamp.getTimestamp(); // Or just LocalDateTime.now();
        LocalDate transactionDate = now.toLocalDate();
        LocalTime transactionTime = now.toLocalTime();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = conn.prepareStatement(
                     "INSERT INTO transactions (date, time, description, vendor, amount) VALUES (?, ?, ?, ?, ?)"
             )) {

            // Set the parameters for the PreparedStatement
            statement.setDate(1, java.sql.Date.valueOf(transactionDate));       // Convert LocalDate to java.sql.Date
            statement.setTime(2, java.sql.Time.valueOf(transactionTime));       // Convert LocalTime to java.sql.Time
            statement.setString(3, description);
            statement.setString(4, vendor);
            statement.setFloat(5, amount);

            // Execute the insert statement
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Deposit added successfully to the database!");
                // Also add to the in-memory list for immediate use without reloading
                transactions.add(new Transaction(transactionDate, transactionTime, amount, description, vendor));
                // It's good practice to re-sort after adding if your display relies on it
                Collections.sort(transactions, Comparator.comparing(Transaction::getDateTime).reversed());
            } else {
                System.out.println("Failed to add deposit to the database.");
            }

        } catch (SQLException e) {
            System.err.println("Database error while adding deposit!");
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
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

    //Adding a new payment to add to the transactions list.
    public static void addPayment(String description, String vendor, float amount) {
        // Input validation remains important
        if (description.isEmpty() || vendor.isEmpty() || amount >= 0) { // Payments must be negative
            System.out.println("Invalid Input. Description and vendor cannot be empty, and amount must be negative. Please try again.");
            return; // Exit method if input is invalid
        }

        // Get current timestamp for date and time columns
        LocalDateTime now = timeStamp.getTimestamp(); // Or just LocalDateTime.now();
        LocalDate transactionDate = now.toLocalDate();
        LocalTime transactionTime = now.toLocalTime();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement statement = conn.prepareStatement(
                     "INSERT INTO transactions (date, time, description, vendor, amount) VALUES (?, ?, ?, ?, ?)"
             )) {

            // Set the parameters for the PreparedStatement
            statement.setDate(1, java.sql.Date.valueOf(transactionDate));       // Convert LocalDate to java.sql.Date
            statement.setTime(2, java.sql.Time.valueOf(transactionTime));       // Convert LocalTime to java.sql.Time
            statement.setString(3, description);
            statement.setString(4, vendor);
            statement.setFloat(5, amount);

            // Execute the insert statement
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Payment added successfully to the database!");
                transactions.add(new Transaction(transactionDate, transactionTime, amount, description, vendor));
                Collections.sort(transactions, Comparator.comparing(Transaction::getDateTime).reversed());
            } else {
                System.out.println("Failed to add payment to the database.");
            }

        } catch (SQLException e) {
            System.err.println("Database error while adding deposit!");
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
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
        }
        //Print out the results of each transaction that matched the filtered results.
        for (int i = 0; i < transactions.size(); i++) {
            if (results.get(i)) {
                System.out.println(transactions.get(i));
            }
        }
    }
}

