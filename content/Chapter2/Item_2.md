# Item 2: Consider a builder when faced with many constructor parameters (생성자에 매개변수가 많다면 빌더를 고려하라)

> 생성자나 정적 팩터리가 처리해야 할 매개변수가 많다면 빌더 패턴을 선택하는 게 더 낫다.

정적 팩터리와 생성자에는 똑같은 제약이 하나 있다. 바로 선택적 매개변수가 많을 때 적절히 대응하기 어렵다는 점이다. 이전의 프로그래머는 점측적 생성자 패턴을 즐겨 사용했다. ([NutritionFacts](../../src/main/java/chapter2/Item_2/NutritionFacts.java))

보통 이런 생성자는 사용자가 설정하길 원치 않는 매개변수까지 포함하기 쉬운데, 어쩔 수 없이 그런 매개변수에도 값을 지정해줘야 한다. 이러한 값의 수가 더 늘어나면 걷갑을 수 없게 된다.

이렇듯 점층적 생성자 패턴도 쓸 수는 잇지만, 매개변수 개수가 많아지면 클라이언트 코드를 작성하거나 읽기 어렵게 된다.

이 외에도 JavaBeans pattern또한 있다. 매개변수가 없는 생성자로 객체를 만들 후, Setter 메서드들을 호출해 원하는 매개변수의 값을 설정하는 방식이다. ([NutritionFactsJavaBean](../../src/main/java/chapter2/Item_2/NutritionFactsJavaBean.java))

JavaBeans는 점층적 생성자 패턴의 단점이 보이지 않는다. 하지만 객체 하나를 만들려면 메서드를 여러 개 호출해야 하고, 객체가 완전히 생성되기 전까지는 일관성(consistency)이 무너진 상태에 놓이게 된다. 이때문에 JavaBeans패턴은 클래스를 불변(Item 17)으로 만들 수 없으며 스레드 안전성을 얻으려면 프로그래머가 추가 작업을 해줘야만 한다. 이러한 단점을 완화하고자 freezing을 사용하기도 하지만 다루기 어렵고 컴파일러가 보증할 방법이 없어 런타임 오류에 취약하다.

## Builder Pattern

- 클라이언트는 필요한 객체를 만드는 대신, 필수 매개변수만으로 생성자를 호출해 빌더 객체를 얻는다. 그런 다음 빌더 객체가 제공하는 일종의 세터 메서드들로 원하는 선택 매개변수들을 설정한다. 마지막으로 매개변수가 없는 build메서드를 호출해 우리에게 필요한 객체를 얻는다.([NutritionFactsBuilderPattern](../../src/main/java/chapter2/Item_2/NutritionFactsBuilderPattern.java))
- 빌더 패턴은 파이썬과 스칼라에 있는 명명된 선택적 매개변수(named optional parameters)를 흉내낸 것이다.
- 빌더 패턴은 계층적으로 설계된 클래스와 함께 쓰기 좋다. ([Pizza](../../src/main/java/chapter2/Item_2/Pizza.java), [NyPizza](../../src/main/java/chapter2/Item_2/NyPizza.java), [Calzone](../../src/main/java/chapter2/Item_2/Calzone.java))

빌더 패턴은 상당히 유연하다. 빌더 하나로 여러 객체를 순회하면서 만들 수 있고, 빌더에 넘기는 매개변수에 따라 다른 객체를 만들 수도 있다. 객체마다 부여되는 일련번호와 같은 특정 필드는 빌더가 알아서 채우도록 할 수도 있다.

생성자 패턴보다 코드가 장황해서 매개변수가 4개 이상의 되어야 값어치를 하지만 API는 시간이 이날수록 매개변수가 많아지는 경향이 있음을 명심하자.
