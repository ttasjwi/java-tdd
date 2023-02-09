package com.ttasjwi.money.domain;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private final Map<Pair, Integer> rates = new HashMap<>();

    private static class Pair {
        private String from;
        private String to;

        public Pair(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            Pair pair = (Pair) o;
            return from.equals(pair.from) && to.equals(pair.to);
        }

        //TODO: Stub
        @Override
        public int hashCode() {
            return 0;
        }
    }

    public void addRate(String from, String to, int rate) {
        rates.put(new Pair(from, to), rate);
    }

    public Money reduce(Expression source, String to) {
        return source.reduce(this, to);
    }

    public int rate(String currency, String to) {
        return (currency.equals(to))
                ? 1
                : rates.get(new Pair(currency, to));
    }
}
