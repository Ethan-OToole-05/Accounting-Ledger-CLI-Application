package com.pluralsight;

import java.io.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Ledger {
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static TimeStamp timeStamp = new TimeStamp();
    private static String fileName = "src/main/resources/transactions.csv";

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
//                if (amount < 0) {
//                    continue;
//                } else {
                    transactions.add(new Transaction(date, time, amount, description, vendor));
//                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public static void addDeposit(String description, String vendor, float amount) {
        try {
            if (amount < 0) {
                System.out.println("Invalid Input. Please make sure amount is positive for adding a deposit.");
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
        for(Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }


    //Needs to be static to work? Might be because deposits is static?
    //Todo: ***** LOOK INTO LATER *****
    public static void getDeposits() {
        for(Transaction transaction : transactions) {
            if(transaction.getAmount() < 0 ) {
                continue;
            }
            else {
                System.out.println(transaction);
            }
        }
    }


    public static void addPayment(String description, String vendor, float amount) {
        try {
            if (amount > 0) {
                System.out.println("Invalid input. Please make sure that amount is negative to represent payments.");
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
        for(Transaction transaction : transactions) {
            if(transaction.getAmount() > 0 ) {
                continue;
            }
            else {
                System.out.println(transaction);
            }
        }
    }

    /*public static ArrayList monthToDate() {
        Month month = LocalDate.now().getMonth();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);

            String input;

            while((input = reader.readLine()) != null){
                if(input.)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
