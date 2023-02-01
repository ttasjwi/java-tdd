package com.ttasjwi.money.domain;

public class Franc extends Money {

    public Franc(int amount, String currency) {
        super(amount);
        this.currency = currency;
    }

    @Override
    public Money times(int multiplier) {
        return Money.franc(amount * multiplier);
    }

    @Override
    public String currency() {
        return currency;
    }
}
