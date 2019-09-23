package test;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class Test extends ClassLoader implements Opcodes {


    /**
     * 字节码操作
     * @param args
     * @throws IOException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
        ClassWriter writer = new ClassWriter(0);
        writer.visit(V1_1,ACC_PUBLIC,"YogurtExample",null,"java/lang/Object",null);
        MethodVisitor methodVisitor = writer.visitMethod(ACC_PUBLIC,"<init>","()V",null,null);
        methodVisitor.visitVarInsn(ALOAD,0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL,"java/lang/Object","<init>","()V");
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(1,1);
        methodVisitor.visitEnd();
        methodVisitor = writer.visitMethod(ACC_PUBLIC + ACC_STATIC,"main","([Ljava/lang/String;)V",null,null);
        methodVisitor.visitFieldInsn(GETSTATIC,"java/lang/System","out","Ljava/io/PrintStream;");
        methodVisitor.visitLdcInsn("Hello Yogurt");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL,"java/io/PrintStream","println","(Ljava/lang/String;)V");
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(2,2);
        methodVisitor.visitEnd();
        byte[] bytecode = writer.toByteArray();
        FileOutputStream fout = new FileOutputStream("E:\\YogurtExample.class");
        fout.write(bytecode);
        fout.close();
        Test loader = new Test();
        Class yogurtClzz = loader.defineClass("YogurtExample",bytecode,0,bytecode.length);
        yogurtClzz.getMethods()[0].invoke(null,new Object[]{null});

    }
}
