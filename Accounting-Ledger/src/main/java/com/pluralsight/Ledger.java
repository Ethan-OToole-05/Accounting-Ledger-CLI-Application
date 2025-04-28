package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ledger {
    private ArrayList<Deposit> deposits = new ArrayList<>();
    private ArrayList<Payment> payments = new ArrayList<>();

    public Ledger() {

    }

    public ArrayList<Deposit> loadDeposits() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader reader = new BufferedReader(fileReader);

            String input;

            while((input = reader.readLine()) != null) {
                if(input.startsWith("id")) {
                    continue;
                }
                String[] items = input.split("\\|");
                //ADDRESS DATE AND TIME ON ITEMS[0] AND ITEMS[1].
                String description = items[2];
                String vendor = items[3];
                float amount = Float.parseFloat(items[4]);
                deposits.add(new Deposit(amount, description, vendor));
            }
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return deposits;
    }
    public ArrayList<Payment> loadPayments() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader reader = new BufferedReader(fileReader);

            String input;

            while((input = reader.readLine()) != null) {
                if(input.startsWith("id")) {
                    continue;
                }
                String[] items = input.split("\\|");
                //ADDRESS DATE AND TIME ON ITEMS[0] AND ITEMS[1].
                String description = items[2];
                String vendor = items[3];
                float amount = Float.parseFloat(items[4]);
                payments.add(new Payment(amount, description, vendor));
            }
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return payments;
    }
}
