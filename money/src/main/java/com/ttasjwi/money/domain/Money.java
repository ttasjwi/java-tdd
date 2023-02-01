package com.ttasjwi.money.domain;

public abstract class Money {

    protected int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public static Money dollar(int amount) {
        return new Dollar(amount);
    }

    public static Money franc(int amount) {
        return new Franc(amount);
    }

    public abstract Money times(int multiplier);

    public abstract String currency();

    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return amount == money.amount && getClass() == money.getClass();
    }

}
