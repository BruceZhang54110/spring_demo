package com.test.generic;

/**
 * 子类不明确泛型类的类型参数变量
 * @param <T>
 */
public class InterImpl<T> implements Inter<T> {
    @Override
    public void show(T t) {

    }
}
