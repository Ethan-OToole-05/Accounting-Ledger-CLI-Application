package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private float amount;
    private String description;
    private String vendor;
    private DateTimeFormatter formatter;

    //Default constructor to build transactions.
    public Transaction() {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.amount = 0;
        this.description = "";
        this.vendor = "";
    }

    //When adding a new transaction take in amount, description, vendor, and current date and time.
    public Transaction(Float amount, String description, String vendor) {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.amount = amount;
        this.description = description;
        this.vendor = vendor;
    }

    //When reading from a transaction file.
    public Transaction(LocalDate date, LocalTime time, Float amount, String description, String vendor) {
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.description = description;
        this.vendor = vendor;
    }

    public float getAmount() {
        return this.amount;
    }

    public String getDescription() {
        return this.description;
    }

    public String getVendor() {
        return this.vendor;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public static LocalDateTime getDateTime(LocalDate date, LocalTime time){
        LocalDateTime dateTime;

        dateTime = LocalDateTime.of(date, time);
        return dateTime;

    }

    //Override toString to print out the entire transaction.
    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%.2f", date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), time.format(DateTimeFormatter.ofPattern("HH:mm:ss")), description, vendor, amount);
    }
}
