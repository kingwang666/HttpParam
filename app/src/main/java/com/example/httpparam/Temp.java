package com.example.httpparam;

/**
 * Created on 2019/6/27.
 * Author: bigwang
 * Description:
 */
public class Temp {

    public void test(){
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "getParams22", "()Ljava/util/Map;", "()Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;", null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);
        methodVisitor.visitLineNumber(16, label0);
        methodVisitor.visitTypeInsn(NEW, "java/util/HashMap");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 1);
        Label label1 = new Label();
        methodVisitor.visitLabel(label1);
        methodVisitor.visitLineNumber(17, label1);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("wang");
        methodVisitor.visitLdcInsn("xiaojie");
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label2 = new Label();
        methodVisitor.visitLabel(label2);
        methodVisitor.visitLineNumber(18, label2);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitInsn(ARETURN);
        Label label3 = new Label();
        methodVisitor.visitLabel(label3);
        methodVisitor.visitLocalVariable("this", "Lcom/example/httpparam/BaseTest;", null, label0, label3, 0);
        methodVisitor.visitLocalVariable("params", "Ljava/util/Map;", "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;", label1, label3, 1);
        methodVisitor.visitMaxs(3, 2);
        methodVisitor.visitEnd();
    }
}
