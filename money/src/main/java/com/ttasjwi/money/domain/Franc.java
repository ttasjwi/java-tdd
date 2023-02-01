package com.ttasjwi.money.domain;

public class Franc extends Money {

    public Franc(int amount, String currency) {
        super(amount, currency);
    }

    @Override
    public Money times(int multiplier) {
        return new Franc(amount * multiplier, "CHF");
    }

    @Override
    public String currency() {
        return currency;
    }
}
