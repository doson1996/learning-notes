package com.ds.graph.nebula.data;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.util.TraceClassVisitor;

import java.lang.reflect.Method;

/**
 * @author ds
 * @date 2024/11/28
 * @description
 */
public class ClassTest {
    public static void main(String[] args) throws Exception {
        ClassReader classReader = new ClassReader("com.ds.graph.nebula.data.InitData");
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM5) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                System.out.println("name = " + access + "  " + name + "  " + desc + "  " + signature);
                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        };
        classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);
    }
}
