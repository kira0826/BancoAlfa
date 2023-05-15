package com.main.alfabank.model.domain;

import java.util.Date;

public class Expense extends Register implements Registrable {
    public Expense(double amount, Date registerDate, String description) {
        super(amount, registerDate, description);
    }
}
