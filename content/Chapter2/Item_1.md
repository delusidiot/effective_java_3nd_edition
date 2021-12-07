# Item 1 : Consider static factory methods instead of constructors (생성자 대신 정적 팩터리 메서드를 고려하라)

클래스는 클라이언트에 public 생성자 대신 (또는 생성자와 함께) 정적 팩터리 메서드 제공할 수 있다. 이 방식에는 장점과 단점이 모두 존재한다.

## 장점

1. 이름을 가질 수 있다.
    - 단순히 생성자의 이름으로만 반환될 객체의 특성을 제대로 설명하지 못한다. 하지만 정적 팩터리 메서드 이름만 잘 지으면 반환될 객체의 특정을 쉽게 알수 있다.
2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다.
    - immutable class (Item 17)는 불필요한 객체 생성을 피할 수 있다.
    - 같은 객체가 자주 요청되는 상황에서 성능을 끌어올릴 수 있다.
    - 인스턴스를 통제할 수 있다. (instance-controlled class)
        - signleton으로 만들 수 있다. (Item 3)
        - noninstantiable로 만들 수 있다. (Item 4)
        - 인스턴스가 단 하나임을 보장할 수 있다. (Item 17)
    - FlyWeight pattern과 비슷하다.
    - 열거 타입은 인스턴스가 하나만 만들어짐을 보장한다 (Item34)
3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
    - 반환할 객체의 클래스를 자유롭게 선택할 수 있어 엄청난 유연성을 선물한다.
    - API를 만들 때 구현 클래스를 공개하지 않더라도 그 객체를 반환할 수 있어 API를 작게 유지할 수 있다.
    - 인터페이스 기반 프레임워크(Item 20)를 만드는 핵심 기술
    - 정적 팩터리 메서드를 사용하는 클라이언트는 얻은 객체를 인터페이스만으로 다루게 된다. (Item 64)
4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환 할 수 있다.
    - 반환 타입의 하위 타입이기만 하면 어떤 클래스의 객체를 반환하든 상관 없다.
    - EnumSet 클래스(Item 36)의 RegularEnumSet과 JumboEnumSet의 존재를 클라이언트는 알 필요가 없다.
5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
    - service provider framework를 만드는 근간이 된다.
      - JDBC
    - 서비스 제공자 프레임워크의 3개의 핵심 컴포넌트
      - service interface: 구현체의 동작을 정의.
      - provider registration API: 제공자가 구현체를 등록할 때 사용
      - service access API: 클라이언트가 서비스의 인스턴스를 얻을 때 사용
    - 유연한 정적 팩터리는 service access API
## 단점
1. 상속을 하려면 public이나 protected 생성자가 필요하니 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다.
   - 컬렉션 프레임워크의 유틸리티 구현 클래스들은 상속할 수 없다. 
   - 상속보다 컴포지션을 사용(Item 18)하도록 유도하고 불변 타입(Item 17)으로 만들어지면 이 제약을 지켜야 한다는 점에서 오히려 장점일수 있다.
2. 정적 팩터리 메서드는 프로그래머가 찾기 어렵니다.
   - 정적 팩터리 메서드에 흔히 사용하는 명명방식을 사용하여 이를 해소한다.

## 정적 팩터리 메서드 명명 방식

from : 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환 하는 형변환 메서드
```java
Date d = Date.from(instant);
```

of : 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환하는 집계 메서드
```java
Set<Rank> = faceCards = EnumSet.of(JACK, QUEEN, KING);
```

valueOf : from과 of의 더 자세한 버전
```java
BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
```

instance 또는 getInstance : 매개변수를 받는다면 매개변수로 명시한 인스턴스를 반환하지만, 같은 인스턴스임을 보장하지는 않는다.
```java
StackWalker luke = StackWalker.getInstance(options);
```

create 또는 newInstance : instance 혹은 getInstance와 같지만, 매번 새로운 인스턴스를 생성해 반환함을 보장한다.
```java
Object newArray = Array.newInstance(classObject, arrayLen);
```

getType : getInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 쓴다. Type은 팩터리 매서드가 반환할 객체의 타입이다.
```java
FileStore fs = Files.getFileStore(path);
```

newType: newInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 쓴다. Type은 팩터리 메서드가 반환할 객체의 타입이다.
```java
BufferedReader br = Files.newBufferedReader(path);
```

type : getType과 newType의 간결한 버전
```java
List<complaint> litany = Collections.list(legacyLitany);