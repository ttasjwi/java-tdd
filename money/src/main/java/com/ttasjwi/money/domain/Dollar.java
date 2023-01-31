package com.ttasjwi.money.domain;

public class Dollar {

    private int amount;

    public Dollar(int amount) {
        this.amount = amount;
    }

    public Dollar times(int multiplier) {
        return new Dollar(amount * multiplier);
    }


    //TODO : $5 + 10CHF = $10$ (환율이 2:1 인 경우)
    //TODO : Money의 반올림

    @Override
    public boolean equals(Object obj) {
        Dollar dollar = (Dollar) obj;
        return amount == dollar.amount;
    }
}
