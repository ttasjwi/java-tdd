package com.ttasjwi.money.domain;

public interface Expression {

    Money reduce(Bank bank, String to);
}
