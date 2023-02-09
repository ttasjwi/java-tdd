package com.ttasjwi.money.domain;

public class Money {

    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public Money times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    public String currency() {
        return currency;
    }

    //TODO : $5 + 10CHF = $10$ (환율이 2:1 인 경우)
    //TODO : Money의 반올림

    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return amount == money.amount && currency().equals(money.currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
