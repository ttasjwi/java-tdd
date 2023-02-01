package com.ttasjwi.money.domain;

public abstract class Money {

    protected int amount;
    protected String currency;

    public Money(int amount) {
        this.amount = amount;
    }

    public static Money dollar(int amount) {
        return new Dollar(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Franc(amount, "CHF");
    }

    public abstract Money times(int multiplier);

    public abstract String currency();

    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return amount == money.amount && getClass() == money.getClass();
    }

}
