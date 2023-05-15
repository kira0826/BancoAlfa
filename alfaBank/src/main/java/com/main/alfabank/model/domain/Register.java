package com.main.alfabank.model.domain;

import java.util.Date;

public abstract class Register implements Comparable<Register>{

    private double amount;
    private Date registerDate;
    private String description;

    public Register(double amount, Date registerDate, String description) {
        this.amount = amount;
        this.registerDate = registerDate;
        this.description = description;
    }

    @Override
    public int compareTo(Register o) {
        return this.registerDate.compareTo(o.registerDate)*-1;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
