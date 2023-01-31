package com.ttasjwi.money.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DollarTest {

    @Test
    @DisplayName("$5에 2를 곱하면 $10이 되어야 한다.")
    public void testMultiplication() {
        //given
        Dollar five = new Dollar(5);
        five.times(2);

        assertThat(five.amount).isEqualTo(10);
    }

}
