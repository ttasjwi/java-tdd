package com.ttasjwi.money.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    @Test
    @DisplayName("plus(...)를 통해 같은 통화의 Money를 합산하기")
    public void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);

        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");

        assertThat(reduced).isEqualTo(Money.dollar(10));
    }

    @Test
    @DisplayName("plus는 두 금액의 합에 해당하는 Sum을 반환한다.")
    public void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertThat(sum.augend).isEqualTo(five);
        assertThat(sum.addend).isEqualTo(five);
    }

    @Test
    @DisplayName("Bank의 reduce를 통해 금액의 합 Sum을 Money로 환산한다.")
    public void testReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertThat(result).isEqualTo(Money.dollar(7));
    }

    @Test
    @DisplayName("Bank의 reduce를 통해 Money를 Money로 환산한다.")
    public void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertThat(result).isEqualTo(Money.dollar(1));
    }


    @Test
    @DisplayName("times(...) -> 금액을 정수배한 새로운 Money를 반환한다.")
    public void testMultiplication() {
        Money five = Money.dollar(5);
        assertThat(five.times(2)).isEqualTo(Money.dollar(10));
        assertThat(five.times(3)).isEqualTo(Money.dollar(15));
    }

    @Test
    @DisplayName("금액이 같고 같은 클래스여야 동등하다.")
    //TODO: null 또는 다른 객체들에 대한 동등성은?
    //TODO: hashCode() 는?
    public void testEquality() {
        assertThat(Money.dollar(5)).isEqualTo(Money.dollar(5));
        assertThat(Money.dollar(5)).isNotEqualTo(Money.dollar(6));
        assertThat(Money.franc(5)).isNotEqualTo(Money.dollar(5));
    }

    @Test
    @DisplayName("통화(Currency) 종류 테스트")
    public void testCurrency() {
        assertThat(Money.dollar(1).currency()).isEqualTo("USD");
        assertThat(Money.franc(1).currency()).isEqualTo("CHF");
    }

}
