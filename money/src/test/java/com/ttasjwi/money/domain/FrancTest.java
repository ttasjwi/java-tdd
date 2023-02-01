package com.ttasjwi.money.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrancTest {

    @Test
    @DisplayName("times(...) -> 금액을 정수배한 새로운 Franc를 반환한다.")
    public void testMultiplication() {
        Franc five = new Franc(5);
        assertThat(five.times(2)).isEqualTo(new Franc(10));
        assertThat(five.times(3)).isEqualTo(new Franc(15));
    }

}
