package com.pluralsight;

import java.io.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Ledger {
    private static ArrayList<Deposit> deposits = new ArrayList<>();
    private static ArrayList<Payment> payments = new ArrayList<>();
    private static TimeStamp timeStamp = new TimeStamp();

    public Ledger() {

    }

    public ArrayList<Deposit> loadDeposits() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader reader = new BufferedReader(fileReader);

            String input;

            while ((input = reader.readLine()) != null) {
                if (input.startsWith("Date") || input.startsWith("date")) {
                    continue;
                }
                String[] items = input.split("\\|");
                //ADDRESS DATE AND TIME ON ITEMS[0] AND ITEMS[1].
                String description = items[2];
                String vendor = items[3];
                float amount = Float.parseFloat(items[4]);
                if (amount < 0) {
                    continue;
                } else {
                    deposits.add(new Deposit(amount, description, vendor));
                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deposits;
    }

    public static void addDeposit(String description, String vendor, float amount) {
        try {
            if (amount < 0) {
                System.out.println("Invalid Input. Please make sure amount is positive for adding a deposit.");
            } else {
                FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
                BufferedWriter writer = new BufferedWriter(fileWriter);

                //Gets time and date right now.
                LocalDateTime now;
                now = timeStamp.getTimestamp();
                String formattedTime = timeStamp.formatTimestamp(now);

                writer.write(formattedTime + "|" + description + "|" + vendor + "|" + amount);
                writer.newLine();
                writer.close();
                deposits.add(new Deposit(amount, description, vendor));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Needs to be static to work? Might be because deposits is static?
    //Todo: ***** LOOK INTO LATER *****
    public static ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    public ArrayList<Payment> loadPayments() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader reader = new BufferedReader(fileReader);

            String input;

            while ((input = reader.readLine()) != null) {
                if ((input.startsWith("Date") || input.startsWith("date"))) {
                    continue;
                }
                String[] items = input.split("\\|");
                //ADDRESS DATE AND TIME ON ITEMS[0] AND ITEMS[1].
                String description = items[2];
                String vendor = items[3];
                float amount = Float.parseFloat(items[4]);
                if (amount > 0) {
                    continue;
                } else {
                    payments.add(new Payment(amount, description, vendor));
                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public static void addPayment(String description, String vendor, float amount) {
        try {
            if (amount > 0) {
                System.out.println("Invalid input. Please make sure that amount is negative to represent payments.");
            } else {
                FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
                BufferedWriter writer = new BufferedWriter(fileWriter);

                LocalDateTime now;
                now = timeStamp.getTimestamp();
                String formattedTime = timeStamp.formatTimestamp(now);

                writer.write(formattedTime + "|" + description + "|" + vendor + "|" + amount);
                writer.newLine();
                writer.close();
                payments.add(new Payment(amount, description, vendor));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Payment> getPayments() {
        return payments;
    }

}
