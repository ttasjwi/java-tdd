
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
