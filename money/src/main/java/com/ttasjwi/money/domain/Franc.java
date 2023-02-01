package com.ttasjwi.money.domain;

public class Franc extends Money {


    public Franc(int amount) {
        super(amount);
    }

    public Franc times(int multiplier) {
        return new Franc(amount * multiplier);
    }

    @Override
    public boolean equals(Object obj) {
        Franc dollar = (Franc) obj;
        return amount == dollar.amount;
    }
}
