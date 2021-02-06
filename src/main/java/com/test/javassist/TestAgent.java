package com.test.javassist;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class TestAgent {
    public static void agentmain(String args, Instrumentation instrumentation) {
        //指定我们自己定义的Transformer，在其中利用Javassist做字节码替换
        instrumentation.addTransformer(new TestTransformer(), true);
        try {
            instrumentation.retransformClasses(Base1.class);
            System.out.println("Agent Load Done.");
        } catch (UnmodifiableClassException e) {
            System.out.println("Agent Load failed!");
            e.printStackTrace();
        }
    }
}
