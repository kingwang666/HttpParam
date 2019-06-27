package com.example.httpparam;

/**
 * Created on 2019/6/27.
 * Author: bigwang
 * Description:
 */
public class Temp {

    public void test(){
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "getParams", "()Ljava/util/Map;", "()Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>;", null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);


        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/example/httpparam/BaseTest", "getParams", "()Ljava/util/Map;", false);
        methodVisitor.visitVarInsn(ASTORE, 1);

        Label label1 = new Label();
        methodVisitor.visitLabel(label1);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("test");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "test", "Z");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label2 = new Label();
        methodVisitor.visitLabel(label2);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aa");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aa", "Ljava/lang/Boolean;");
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label3 = new Label();
        methodVisitor.visitLabel(label3);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("mTest");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "mTest", "Lcom/example/httpparam/Test;");
        Label label4 = new Label();
        methodVisitor.visitJumpInsn(IFNONNULL, label4);
        methodVisitor.visitLdcInsn("");
        Label label5 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label5);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitFrame(Opcodes.F_FULL, 2, new Object[]{"com/example/httpparam/Test", "java/util/Map"}, 2, new Object[]{"java/util/Map", "java/lang/String"});
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "mTest", "Lcom/example/httpparam/Test;");
        methodVisitor.visitLabel(label5);
        methodVisitor.visitFrame(Opcodes.F_FULL, 2, new Object[]{"com/example/httpparam/Test", "java/util/Map"}, 3, new Object[]{"java/util/Map", "java/lang/String", "java/lang/Object"});
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label6 = new Label();
        methodVisitor.visitLabel(label6);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("cc");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "cc", "[I");
        Label label7 = new Label();
        methodVisitor.visitJumpInsn(IFNONNULL, label7);
        methodVisitor.visitLdcInsn("");
        Label label8 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label8);
        methodVisitor.visitLabel(label7);
        methodVisitor.visitFrame(Opcodes.F_FULL, 2, new Object[]{"com/example/httpparam/Test", "java/util/Map"}, 2, new Object[]{"java/util/Map", "java/lang/String"});
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "cc", "[I");
        methodVisitor.visitLabel(label8);
        methodVisitor.visitFrame(Opcodes.F_FULL, 2, new Object[]{"com/example/httpparam/Test", "java/util/Map"}, 3, new Object[]{"java/util/Map", "java/lang/String", "java/io/Serializable"});
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label9 = new Label();
        methodVisitor.visitLabel(label9);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("name");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "name", "Ljava/lang/String;");
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label10 = new Label();
        methodVisitor.visitLabel(label10);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitInsn(ARETURN);
        Label label11 = new Label();
        methodVisitor.visitLabel(label11);

        methodVisitor.visitLocalVariable("this", "Lcom/example/httpparam/Test;", null, label0, label11, 0);
        methodVisitor.visitLocalVariable("params", "Ljava/util/Map;", "Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>;", label1, label11, 1);
        methodVisitor.visitMaxs(3, 2);
        methodVisitor.visitEnd();
    }

    public void test2(){
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "getParams", "()Ljava/util/Map;", "()Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;", null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);


        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/example/httpparam/BaseTest", "getParams", "()Ljava/util/Map;", false);
        methodVisitor.visitVarInsn(ASTORE, 1);

        Label label1 = new Label();
        methodVisitor.visitLabel(label1);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aBoolean");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aBoolean", "Z");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(Z)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label2 = new Label();
        methodVisitor.visitLabel(label2);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aByte");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aByte", "B");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(I)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label3 = new Label();
        methodVisitor.visitLabel(label3);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aChar");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aChar", "C");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(C)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label4 = new Label();
        methodVisitor.visitLabel(label4);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aDouble");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aDouble", "D");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(D)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label5 = new Label();
        methodVisitor.visitLabel(label5);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aFloat");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aFloat", "F");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(F)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label6 = new Label();
        methodVisitor.visitLabel(label6);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aInt");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aInt", "I");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(I)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label7 = new Label();
        methodVisitor.visitLabel(label7);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aLong");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aLong", "J");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(J)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label8 = new Label();
        methodVisitor.visitLabel(label8);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aShort");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aShort", "S");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(I)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label9 = new Label();
        methodVisitor.visitLabel(label9);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitInsn(ARETURN);
        Label label10 = new Label();
        methodVisitor.visitLabel(label10);
        methodVisitor.visitLocalVariable("this", "Lcom/example/httpparam/Test;", null, label0, label10, 0);
        methodVisitor.visitLocalVariable("params", "Ljava/util/Map;", "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;", label1, label10, 1);
        methodVisitor.visitMaxs(4, 2);
        methodVisitor.visitEnd();
    }
}
