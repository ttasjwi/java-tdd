package com.ttasjwi.money.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    @Test
    @DisplayName("금액이 같고 같은 클래스여야 동등하다.")
    //TODO: null 또는 다른 객체들에 대한 동등성은?
    //TODO: Dollar, Franc의 비교
    //TODO: hashCode() 는?
    public void testEquality() {
        assertThat(Money.dollar(5)).isEqualTo(Money.dollar(5));
        assertThat(Money.dollar(5)).isNotEqualTo(Money.dollar(6));
        assertThat(Money.franc(5)).isEqualTo(Money.franc(5));
        assertThat(Money.franc(5)).isNotEqualTo(Money.franc(6));
        assertThat(Money.franc(5)).isNotEqualTo(Money.dollar(5));
    }

    @Test
    @DisplayName("통화(Currency) 종류 테스트")
    public void testCurrency() {
        assertThat(Money.dollar(1).currency()).isEqualTo("USD");
        assertThat(Money.franc(1).currency()).isEqualTo("CHF");
    }

}
