package com.test.asm;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * ASM 字节码增强技术
 */
public class Generator {
    public static void main(String[] args) throws Exception {
        // 读取
        ClassReader classReader = new ClassReader("com.test.asm.Base");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        // 处理
        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();
        // 输出
        File f = new File("E:\\JavaDev\\template_workspace\\springcode\\target\\classes\\com\\test\\asm\\Base.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(data);
        fout.close();
        System.out.println("now generator cc success!!!!! ");
    }
}
