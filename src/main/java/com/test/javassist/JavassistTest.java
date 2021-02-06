package com.test.javassist;

import javassist.*;

import java.io.IOException;

/**
 * 使用javassist 修改字节码，但是不能允许jvm运行时加载类信息
 */
public class JavassistTest {
    public static void main(String[] args) throws CannotCompileException, IOException, NotFoundException, IllegalAccessException, InstantiationException {
        Base b = new Base();
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.test.javassist.Base1");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("{System.out.println(\"start\");}");
        m.insertAfter("{System.out.println(\"end\");}");
        Class c = cc.toClass();
        cc.writeFile("com/test/javassist");
        Base h =(Base) c.newInstance();
        h.process();
    }
}
