package com.test.hashmaptest;

// import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name","zhanghongwei");
        map.put("sex","nan");
        // System.out.println(EnumTest.INSTANCE.getObj());
        // System.out.println(EnumTest.INSTANCE.getObj());
        //System.out.println(2 << 1);
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("name", "aaa");
    }
}

