package com.ttasjwi.money.domain;

public class Dollar extends Money {

    private String currency;

    public Dollar(int amount) {
        super(amount);
        currency = "USD";
    }

    @Override
    public Money times(int multiplier) {
        return new Dollar(amount * multiplier);
    }

    @Override
    public String currency() {
        return currency;
    }

    //TODO : $5 + 10CHF = $10$ (환율이 2:1 인 경우)
    //TODO : Money의 반올림
}
