package com.ttasjwi.money.domain;

public class Bank {

    public Money reduce(Expression source, String to) {
        return source.reduce(this, to);
    }

    public int rate(String currency, String to) {
        return (currency.equals("CHF") && to.equals("USD"))
                ? 2
                : 1;
    }
}
