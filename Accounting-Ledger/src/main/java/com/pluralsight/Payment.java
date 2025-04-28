package com.pluralsight;

public class Payment {
    private float amount;
    private String description;
    private String vendor;

    public Payment() {
        this.amount = 0;
        this.description = "";
        this.vendor = "";
    }
    public Payment(float amount, String description, String vendor) {
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
}
