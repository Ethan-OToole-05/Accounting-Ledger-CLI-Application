package com.pluralsight;

import java.io.*;
import java.sql.Time;
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

        //TODO: *****ONLY SORTS BY DATE COULD HAVE EARLIER TIME *****.
        //Comparator to check both date and time.
        //Get datetime straight up?
        //Add new Transaction getDateTime somehow?

        Collections.sort(transactions, Comparator.comparing(Transaction::getDateTime).reversed());
        return transactions;
    }

    public static void addDeposit(String description, String vendor, float amount) {
        try {
            if (amount < 0 || description.isEmpty() || vendor.isEmpty()) {
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

    public static void getTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }


    //Needs to be static to work? Might be because deposits is static?
    //Todo: ***** LOOK INTO LATER *****
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


    public static void addPayment(String description, String vendor, float amount) {
        try {
            if (amount > 0 || description.isEmpty() || vendor.isEmpty()) {
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

    public static void monthToDate() {
        Month month = LocalDate.now().getMonth();
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getMonth().equals(month)) {
                System.out.println(transaction);
            }
        }
    }

    public static void previousMonthToDate() {
        Month month = LocalDate.now().getMonth().minus(1);
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getMonth().equals(month)) {
                System.out.println(transaction);
            }
        }
    }

    public static void yearToDate() {
        int year = LocalDate.now().getYear();
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getYear() == year) {
                System.out.println(transaction);
            }
        }
    }

    public static void previousYearToDate() {
        int previousYear = LocalDate.now().getYear();
        previousYear--;
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getYear() == previousYear) {
                System.out.println(transaction);
            }
        }
    }

    public static void searchByVendor(String vendor) {
        String searchVendor = vendor;

        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(searchVendor)) {
                System.out.println(transaction);
            }
        }
    }

    public static void customSearch(LocalDate startDate, LocalDate endDate, String description, String vendor, Float amount) {
        ArrayList<Transaction> results = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (endDate != null && transaction.getDate().isAfter(endDate)) {
                continue;
            }
            if (startDate != null && transaction.getDate().isBefore(startDate)) {
                continue;
            }

            //How to keep adding on to the end result?
            if (!transaction.getDescription().equalsIgnoreCase(description)) {
                continue;
            }
            if (!transaction.getVendor().equalsIgnoreCase(vendor)) {
                continue;
            }
            if (amount != null && transaction.getAmount() != amount) {
                continue;
            }
            System.out.println(transaction);
//            results.add(transaction);
        }
//        return results;
    }
}
