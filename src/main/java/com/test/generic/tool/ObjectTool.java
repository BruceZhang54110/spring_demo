package com.test.generic.tool;

/**
 *
 1:把泛型定义在类上
 2:类型变量定义在类上,方法中也可以使用
 * @param <T>
 */
public class ObjectTool<T> {

    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
    //定义泛型方法..
    public <T> void show(T t) {
        System.out.println(t);

    }

}
