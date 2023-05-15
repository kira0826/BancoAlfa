package com.main.alfabank.model.domain;

import java.util.Date;

public class Income extends  Register implements Registrable{
    public Income(double amount, Date registerDate, String description) {
        super(amount, registerDate, description);
    }
}
