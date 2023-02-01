package com.ttasjwi.money.domain;

public class Franc extends Money {

    private String currency;

    public Franc(int amount) {
        super(amount);
        currency = "CHF";
    }

    @Override
    public Money times(int multiplier) {
        return new Franc(amount * multiplier);
    }

    @Override
    public String currency() {
        return currency;
    }
}
