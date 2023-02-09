
# 테스트 주도 개발

---

## 기본 리듬
1. 재빨리 테스트를 하나 추가한다.
2. 모든 테스트를 실행하고, 새로 추가한 것이 실패하는지 확인한다.
3. 코드를 조금 바꾼다.
4. 모든 테스트를 실행하고 전부 성공하는지 확인한다.
5. 리팩토링을 통해 중복을 제거한다.

---

## 2장 : 타락한 객체
느낌(부작용에 대한 혐오감)을 테스트로 변환하는 것은 TDD의 일반적 주제이다.

- 기존의 설계상의 결함을 극복하고자, 우선 테스트를 실패하는 테스트로 변환했다.
- 스텁 구현으로 빠르게 컴파일을 통과하게 만들었다.
  - 스텁 : 가짜 함수?
- 올바르다고 생각하는 코드를 입력하여 테스트를 통과시킨다.

---

## 3장 : 모두를 위한 평등

### 값 객체
- 값 객체 : 한번 생성자를 통해 설정된 후에는 결코 변하지 않는 객체
- 값 객체에 대한 연산을 수행하면, 값은 변하지 않고 새로운 값 객체가 생성된다.

### 무엇을 했는가?
```java
    @Test
    @DisplayName("금액이 같아야 동등한 Dollar다.")
    public void testEquality() {
        assertThat(new Dollar(5)).isEqualTo(new Dollar(5));
        assertThat(new Dollar(5)).isNotEqualTo(new Dollar(6));
    }
```
```java
    @Override
    public boolean equals(Object obj) {
        Dollar dollar = (Dollar) obj;
        return amount == dollar.amount;
    }
```
- Dollar의 equals 메서드를 오버라이드하여, 1차적으로 동등성을 정의했다.
- 어떻게 리팩토링을 할 지 전혀 감이 오지 않을 때는 '삼각측량'을 사용한다. 두가지 이상의 테스트 코드를 통해,
무엇을 일반화해야할 지 찾아내는 기법이다.
- 물론, 테스트 사이의 중복을 제거하고 일반적인 해법을 구할 방법이 보이면 그냥 그 방법대로 구현하면 된다.
- 이 equals 오버라이드에도 한계(null, 다른 타입 여부, ...)가 존재하지만 이는 이후로 미룬다.

---

## 4장 : 프라이버시
```java
    private int amount;
```
```java
    @Test
    @DisplayName("times(...) -> 금액을 정수배한 새로운 Dollar를 반환한다.")
    public void testMultiplication() {
        Dollar five = new Dollar(5);
        assertThat(five.times(2)).isEqualTo(new Dollar(10));
        assertThat(five.times(3)).isEqualTo(new Dollar(15));
    }
```
- 우리는 테스트를 향상시키기 위해 기존의 개발된 코드들을 이용했다.
- 기존에 우리가 개발한 코드를 기반으로 한 테스트는 위험상을 내포하고 있다.
  - 기존의 기능이 깨지면 그 기능을 의존한 테스트 코드 역시 깨진다.
- 그런 위험요소가 있음에도 우리는 테스트 계속 진행했다.
- 기본에는 Dollar의 프로퍼티를 직접 접근하여 테스트를 수행했는데, 객체의 구현에 해당하는 프로퍼티를 private 접근제어자로 은닉한다.
  - 객체의 구현을 사용한 테스트는 코드와 테스트 코드 사이의 강한 결합도를 낳게 된다.
  - 가급적 퍼블릭 인터페이스를 사용한 테스트 코드를 사용할 수 있도록 하자.
- 앞서 구현한 동등성 정의 덕분에 이 테스트가 깨지지 않게 된다.
- 하지만, 앞서 동등성 정의를 잘못했다면 두 개의 테스트가 모두 깨졌을 것이다.

---

## 5장 : 솔직히 말하자면

### 무엇을 했는가?
```java

class FrancTest {

    @Test
    @DisplayName("times(...) -> 금액을 정수배한 새로운 Franc를 반환한다.")
    public void testMultiplication() {
        Franc five = new Franc(5);
        assertThat(five.times(2)).isEqualTo(new Franc(10));
        assertThat(five.times(3)).isEqualTo(new Franc(15));
    }

    @Test
    @DisplayName("금액이 같아야 동등한 Franc다.")
    public void testEquality() {
        assertThat(new Franc(5)).isEqualTo(new Franc(5));
        assertThat(new Franc(5)).isNotEqualTo(new Franc(6));
    }

}
```
- 큰 기능을 만드는 것을 공략할 수 없다.
- 일단 중복을 만들고, 고쳐서 테스트를 작성.
- 심지어 모델 코드를 복사해서 복사하고, 수정해서 테스트를 통과했다.
- DollarTest 복사하여 FrancTest 생성
- Dollar 코드를 Franc로 바꿈
- 컴파일이 안됨 -> Dollar 복사 -> Franc 코드 생성
- 테스크 작동 성공

### 우리는 무엇을 해야하는가?
- 돌아가게 만들고, 올바르게 만들어라.
- 적절한 시기에 적절한 설계를.
- 일단 돌아가는 코드를 빠르게 만드는 것이 중요.

---

## 6장 : 돌아온 '모두를 위한 평등'
```java
    @Test
    @DisplayName("금액이 같아야 동등한 Dollar다.")
    public void testEquality() {
        assertThat(new Dollar(5)).isEqualTo(new Dollar(5));
        assertThat(new Dollar(5)).isNotEqualTo(new Dollar(6));
        assertThat(new Franc(5)).isEqualTo(new Franc(5));
        assertThat(new Franc(5)).isNotEqualTo(new Franc(6));
    }
```
```java
package com.ttasjwi.money.domain;

public class Money {

    protected int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return amount == money.amount;
    }
}
```
- 중복 코드를 제거를 위해 상위 클래스인 Money를 만들었다.
- 생성자 중복을 해결하기 위해, Money에 protected 필드인 amount 두기
- equals 중복을 해결하기 위해 Money에 equals 오버라이드
- 하지만 다른 타입의 통화와 비교에 대한 정의가 이뤄지지 않음 

---

## 7장 : 사과와 오렌지

### 동등성 재정의
```java
    @Test
    @DisplayName("금액이 같고 같은 클래스여야 동등하다.")
    public void testEquality() {
        assertThat(new Dollar(5)).isEqualTo(new Dollar(5));
        assertThat(new Dollar(5)).isNotEqualTo(new Dollar(6));
        assertThat(new Franc(5)).isEqualTo(new Franc(5));
        assertThat(new Franc(5)).isNotEqualTo(new Franc(6));
        assertThat(new Franc(5)).isNotEqualTo(new Dollar(5));
    }
```
```java
    @Override
    public boolean equals(Object obj) {
        Money money = (Money) obj;
        return amount == money.amount && getClass() == money.getClass();
    }
```
- 결함을 끄집어내서 테스트에 담아냈다.
- 당장은 금액과 클래스가 동일하면 동등하도록 정의했다.
- 자바 객체 용어를 사용하기보다 통화(currency) 개념을 도입해서 비교하면 좋을 것 같지만, 당장 통화 개념을 도입할 동기가 있기 전에는 더 많은
설계를 도입하지 않기로 했다.

---

## 8장 : 객체 만들기
```java
package com.ttasjwi.money.domain;

public abstract class Money {
    
    // 생략

    public static Money dollar(int amount) {
        return new Dollar(amount);
    }

    public static Money franc(int amount) {
        return new Franc(amount);
    }

    public abstract Money times(int multiplier);
    
    // 생략
}
```
- Money에 정적 팩토리 클래스를 생성하여, Money를 통해서 인스턴스를 생성하고 클라이언트 측에 구체 클래스를 은닉
- Money를 추상클래스화 하고, 각각의 구체 클래스들의 메서드 선언부들을 추상 클래스에 맞춤
- 이렇게 놓고보니 기존의 구체클래스 테스트들이 불필요해져감을 인식하지만, 그냥 뒀다.

---

## 9장 : 우리가 사는 시간(times)
- Dollar, Franc가 공통적으로 currency()에 응답할 수 있도록 스텁 구현
- 생성자 내부적으로 스스로의 currency를 주입
- 외부에서 currency를 주입하도록 함
- 생성자를 똑같은 모양으로 만들고, 공통적인 생성자 구현을 상위 클래스로 올림
- 이 과정에서 Dollar, Franc의 times 메서드가 스스로의 생성자를 호출하는 부분을 정적 팩터리 메서드를 통해 생성하도록 딴 길로 샘
  - 저자는 리팩터링 도중에 딴 길로 새야 하는 상황을 발견하면 한 번 정도는 짧은 중단을 해도 된다고 했다.
  - 하지만 또 그 상황에서 중단하고, 다른 일로 새지는 말라고 했다.

---

## 10장 : 흥미로운 시간
- times를 올리기 위한 긴 여정의 시작
- Franc의 times에서 Money로 반환할 수는 없을까? => 구체 클래스화가 필요하다. -> times에서 Money 인스턴스 반환하도록 함
- 구체클래스로 만들기 위해 Money의 times를 스텁으로 구현(null) 반환
- 하지만 이렇게 만들어 놓고 보니 기존 테스트에서 빨간 막대가 뜸.
- 디버그를 위해 toString() 을 오버라이드
- 디버그를 해보니, currency와 amount는 동일하지만 동등하지 않다고 되어 있음. equals 오버라이드에 문제가 있었던 것.
- **equals를 어떻게 고칠지 생각하기 위해 테스트를 작성함**
- equals를 고치기 위해 times에서 반환을 일단 다시 Franc 인스턴스를 통해 하도록 함
- equals를 고치고, 테스트 초록막대화
- Franc의 times에서 다시 Money 로 반환하도록 함
- Dollar의 times 역시 Money 로 반환하도록 함
- Money로 Dollar, Franc의 times를 올림
- 이제 불필요한 Dollar, Franc를 올릴 준비가 거의 다 됐다.

---

## 11장 : 모든 악의 근원
```java

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }
```
- 클래스 상의 Dollar, Franc 구체 코드를 제거하고, 이들 구체 클래스들을 제거
- 기존의 소스 구조에서는 필요했지만 새로운 구조에서는 필요 없게된 테스트 제거

---

## 12장 : 드디어, 더하기
```java
    public void testSimpleAddition() {
        Money sum = Money.dollar(5).plus(Money.dollar(5));
        assertThat(sum).isEqualTo(Money.dollar(10));
    }
```
- $5 + 10CHF를 바로 구현하려 드는 것은 매우 복잡하고 큰 문제다.
- 일단 작은 문제인, 5$, 5$의 합산을 먼저 구현한다.
```java
    public void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);

        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");

        assertThat(reduced).isEqualTo(Money.dollar(10));
    }
```
- 우리의 계산은 결국 다른 통화의 금액의 합산도 가능하게 해야한다.
- 일단 Money에서 다른 종류의 통화를 합산하여 Money로 반환하는 것은 복잡하므로, 합산의 결과물로 별도로 Expression을 만들고
이를 Money로 환전 처리하는 책임을 Bank에게 위임한다.
  - 우리가 하려고 하는 일을 핵심 객체가 모르게 하여, 오랫동안 핵심 객체를 유연하게 한다.
- 생각의 흐름대로 테스트를 재작성하고, 컴파일이 되게 한다.
- 빨간 막대가 나온 것을 확인하고 스텁을 구현하여 초록 막대가 나오게 한다.

---
