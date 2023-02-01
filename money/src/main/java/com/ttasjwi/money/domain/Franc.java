package com.ttasjwi.money.domain;

public class Franc extends Money {


    public Franc(int amount) {
        super(amount);
    }

    public Money times(int multiplier) {
        return new Franc(amount * multiplier);
    }

}