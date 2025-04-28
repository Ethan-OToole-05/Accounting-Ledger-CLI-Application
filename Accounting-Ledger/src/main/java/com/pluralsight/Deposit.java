package com.pluralsight;


public class Deposit {
    private float amount;
    private String description;
    private String vendor;

    public Deposit() {
        this.amount = 0.0f;
        this.description = "";
        this.vendor = "";
    }

    public Deposit(float amount, String description, String vendor) {
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

    @Override
    public String toString() {
        return String.format("%.2f %s %s", amount, description, vendor);
    }
}
