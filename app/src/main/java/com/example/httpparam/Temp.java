package com.example.httpparam;

/**
 * Created on 2019/6/27.
 * Author: bigwang
 * Description:
 */
public class Temp {
    {
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
        methodVisitor.visitLdcInsn("aBoolean");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aBoolean", "Z");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label2 = new Label();
        methodVisitor.visitLabel(label2);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aByte");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aByte", "B");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label3 = new Label();
        methodVisitor.visitLabel(label3);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aChar");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aChar", "C");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label4 = new Label();
        methodVisitor.visitLabel(label4);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aDouble");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aDouble", "D");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label5 = new Label();
        methodVisitor.visitLabel(label5);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aFloat");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aFloat", "F");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label6 = new Label();
        methodVisitor.visitLabel(label6);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aInt");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aInt", "I");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label7 = new Label();
        methodVisitor.visitLabel(label7);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aLong");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aLong", "J");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label8 = new Label();
        methodVisitor.visitLabel(label8);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aShort");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aShort", "S");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label9 = new Label();
        methodVisitor.visitLabel(label9);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aa");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aa", "Ljava/lang/Boolean;");
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label10 = new Label();
        methodVisitor.visitLabel(label10);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("mTest");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "mTest", "Lcom/example/httpparam/Test;");
        Label label11 = new Label();
        methodVisitor.visitJumpInsn(IFNONNULL, label11);
        methodVisitor.visitLdcInsn("");
        Label label12 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label12);
        methodVisitor.visitLabel(label11);
        methodVisitor.visitFrame(Opcodes.F_FULL, 2, new Object[]{"com/example/httpparam/Test", "java/util/Map"}, 2, new Object[]{"java/util/Map", "java/lang/String"});
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "mTest", "Lcom/example/httpparam/Test;");
        methodVisitor.visitLabel(label12);
        methodVisitor.visitFrame(Opcodes.F_FULL, 2, new Object[]{"com/example/httpparam/Test", "java/util/Map"}, 3, new Object[]{"java/util/Map", "java/lang/String", "java/lang/Object"});
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label13 = new Label();
        methodVisitor.visitLabel(label13);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("cc");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "cc", "[I");
        Label label14 = new Label();
        methodVisitor.visitJumpInsn(IFNONNULL, label14);
        methodVisitor.visitLdcInsn("");
        Label label15 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label15);
        methodVisitor.visitLabel(label14);
        methodVisitor.visitFrame(Opcodes.F_FULL, 2, new Object[]{"com/example/httpparam/Test", "java/util/Map"}, 2, new Object[]{"java/util/Map", "java/lang/String"});
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "cc", "[I");
        methodVisitor.visitLabel(label15);
        methodVisitor.visitFrame(Opcodes.F_FULL, 2, new Object[]{"com/example/httpparam/Test", "java/util/Map"}, 3, new Object[]{"java/util/Map", "java/lang/String", "java/io/Serializable"});
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label16 = new Label();
        methodVisitor.visitLabel(label16);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("name");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "name", "Ljava/lang/String;");
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label17 = new Label();
        methodVisitor.visitLabel(label17);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("girls");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "girls", "[Ljava/lang/String;");
        Label label18 = new Label();
        methodVisitor.visitJumpInsn(IFNONNULL, label18);
        methodVisitor.visitLdcInsn("");
        Label label19 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label19);
        methodVisitor.visitLabel(label18);
        methodVisitor.visitFrame(Opcodes.F_FULL, 2, new Object[]{"com/example/httpparam/Test", "java/util/Map"}, 2, new Object[]{"java/util/Map", "java/lang/String"});
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "girls", "[Ljava/lang/String;");
        methodVisitor.visitLabel(label19);
        methodVisitor.visitFrame(Opcodes.F_FULL, 2, new Object[]{"com/example/httpparam/Test", "java/util/Map"}, 3, new Object[]{"java/util/Map", "java/lang/String", "java/io/Serializable"});
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label20 = new Label();
        methodVisitor.visitLabel(label20);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitInsn(ARETURN);
        Label label21 = new Label();
        methodVisitor.visitLabel(label21);
        methodVisitor.visitLocalVariable("this", "Lcom/example/httpparam/Test;", null, label0, label21, 0);
        methodVisitor.visitLocalVariable("params", "Ljava/util/Map;", "Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>;", label1, label21, 1);
        methodVisitor.visitMaxs(4, 2);
        methodVisitor.visitEnd();
    }
}
