package com.example.possystem;

import java.io.Serializable;

public class Table implements Serializable {
    private CharSequence number;
    private double checkPrice;

    public double getCheckPrice() {
        return checkPrice;
    }

    public CharSequence getNumber() {
        return number;
    }

    public void setCheckPrice(double checkPrice) {
        this.checkPrice = checkPrice;
    }

    public void setNumber(CharSequence number) {
        this.number = number;
    }


}
