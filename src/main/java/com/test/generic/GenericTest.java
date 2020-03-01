package com.test.generic;

import com.test.generic.tool.ObjectTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericTest {
    public static void main(String[] args) {

        ObjectTool<String> objectTool = new ObjectTool<>();
        objectTool.setObj("泛型类中的泛型是String");
        System.out.println(objectTool.getObj());

        objectTool.show(1111);

        Inter<String> i = new InterImpl<>();
        i.show("test");

        GenericTest test = new GenericTest();
        test.test(new ArrayList<String>(Arrays.asList(new String[]{"1","2"})));
    }

    /**
     * 类型通配符
     * @param list
     */
    public void test(List<?> list){


        for(int i=0;i<list.size();i++){

            System.out.println(list.get(i));

        }
    }
}
