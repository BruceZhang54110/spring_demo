package com.test.generic;

/**
 * 泛型接口,分为两种<br>
 * 子类明确泛型类的类型参数变量
 * 子类不明确泛型类的类型参数变量
 * @param <T>
 */
public interface Inter<T> {
    void show(T t);
}
