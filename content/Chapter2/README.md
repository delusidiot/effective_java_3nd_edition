# Chapter 2 : Creating And Destroying Objects

객체의 생성과 파괴에 대해 설명합니다. 객체를 만들어야 할 때와 만들지 말아야 할 때를 구분하는 법 올바른 객체 생성 방법과 불필요한 생성을 피하는 방법, 제때 파괴됨을 보장하고 파괴 전에 수행해야 할 정리 작업을 관리하는 요령을 알아봅니다.

Item 1 : Consider static factory methods instead of constructors (생성자 대신 정적 팩터리 메서드를 고려하라)

Item 2 : Consider a builder when faced with many constructor parameters

Item 3 : Enforce the singleton property with a private constructor or an enum type

Item 4 : Enforce noninstantiability with a private constructor

Item 5 : Prefer dependency injection to hardwiring resources

Item 6 : Avoid creating unnecessary objects

Item 7 : Eliminate obsolete object references

Item 8 : Avoid finalizers and cleaners

Item 9 : Prefer try-with-resources to try-finally