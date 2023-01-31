package com.ttasjwi.money.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DollarTest {

    @Test
    @DisplayName("times(...) -> 금액을 정수배한 새로운 Dollar를 반환한다.")
    public void testMultiplication() {
        Dollar five = new Dollar(5);

        Dollar product = five.times(2);
        assertThat(product.amount).isEqualTo(10);

        product = five.times(3);
        assertThat(product.amount).isEqualTo(15);
    }

    @Test
    @DisplayName("금액이 같아야 동등한 Dollar다.")

    //TODO: null 또는 다른 객체들에 대한 동등성은?
    //TODO: hashCode() 는?
    public void testEquality() {
        assertThat(new Dollar(5)).isEqualTo(new Dollar(5));
        assertThat(new Dollar(5)).isNotEqualTo(new Dollar(6));
    }

}
