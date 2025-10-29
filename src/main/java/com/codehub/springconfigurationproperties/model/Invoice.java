package com.codehub.springconfigurationproperties.model;

import lombok.Data;

@Data
public class Invoice {
    private String invoiceNumber;
    private String customerName;
    private double amount;
    private String date;
}
