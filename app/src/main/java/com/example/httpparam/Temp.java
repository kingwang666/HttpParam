package com.example.httpparam;

/**
 * Created on 2019/6/27.
 * Author: bigwang
 * Description:
 */
public class Temp {
    {
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "getParams", "()Ljava/util/Map;", "()Ljava/util/Map<Ljava/lang/String;Lokhttp3/RequestBody;>;", null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);


        methodVisitor.visitTypeInsn(NEW, "androidx/collection/ArrayMap");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "androidx/collection/ArrayMap", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 1);
        Label label1 = new Label();
        methodVisitor.visitLabel(label1);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aBoolean");
        methodVisitor.visitLdcInsn("text/plain");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aBoolean", "Z");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(Z)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label2 = new Label();
        methodVisitor.visitLabel(label2);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aByte");
        methodVisitor.visitLdcInsn("text/plain");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aByte", "B");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(I)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label3 = new Label();
        methodVisitor.visitLabel(label3);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aChar");
        methodVisitor.visitLdcInsn("text/plain");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aChar", "C");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(C)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label4 = new Label();
        methodVisitor.visitLabel(label4);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aDouble");
        methodVisitor.visitLdcInsn("text/plain");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aDouble", "D");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(D)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label5 = new Label();
        methodVisitor.visitLabel(label5);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aFloat");
        methodVisitor.visitLdcInsn("text/plain");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aFloat", "F");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(F)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label6 = new Label();
        methodVisitor.visitLabel(label6);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aInt");
        methodVisitor.visitLdcInsn("text/plain");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aInt", "I");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(I)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label7 = new Label();
        methodVisitor.visitLabel(label7);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aLong");
        methodVisitor.visitLdcInsn("text/plain");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aLong", "J");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(J)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label8 = new Label();
        methodVisitor.visitLabel(label8);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aShort");
        methodVisitor.visitLdcInsn("text/plain");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aShort", "S");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(I)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label9 = new Label();
        methodVisitor.visitLabel(label9);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("aa");
        methodVisitor.visitLdcInsn("text/plain");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aa", "Ljava/lang/Boolean;");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Boolean", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label10 = new Label();
        methodVisitor.visitLabel(label10);


        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "mTest", "Lcom/example/httpparam/Test;");
        Label label11 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label11);
        Label label12 = new Label();
        methodVisitor.visitLabel(label12);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("mTest");
        methodVisitor.visitLdcInsn("text/plain");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "mTest", "Lcom/example/httpparam/Test;");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitLabel(label11);
        methodVisitor.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/util/Map"}, 0, null);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("cc");
        methodVisitor.visitLdcInsn("text/plain");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "cc", "[I");
        Label label13 = new Label();
        methodVisitor.visitJumpInsn(IFNONNULL, label13);
        methodVisitor.visitLdcInsn("");
        Label label14 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label14);
        methodVisitor.visitLabel(label13);
        methodVisitor.visitFrame(Opcodes.F_FULL, 2, new Object[]{"com/example/httpparam/Test", "java/util/Map"}, 3, new Object[]{"java/util/Map", "java/lang/String", "okhttp3/MediaType"});
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "cc", "[I");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/util/Arrays", "toString", "([I)Ljava/lang/String;", false);
        methodVisitor.visitLabel(label14);
        methodVisitor.visitFrame(Opcodes.F_FULL, 2, new Object[]{"com/example/httpparam/Test", "java/util/Map"}, 4, new Object[]{"java/util/Map", "java/lang/String", "okhttp3/MediaType", "java/lang/String"});
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label15 = new Label();
        methodVisitor.visitLabel(label15);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("name");
        methodVisitor.visitLdcInsn("text/plain");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "name", "Ljava/lang/String;");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        Label label16 = new Label();
        methodVisitor.visitLabel(label16);


        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "girls", "[Ljava/lang/String;");
        Label label17 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label17);
        Label label18 = new Label();
        methodVisitor.visitLabel(label18);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitLdcInsn("girls");
        methodVisitor.visitLdcInsn("text/plain");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "girls", "[Ljava/lang/String;");
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/util/Arrays", "toString", "([Ljava/lang/Object;)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitLabel(label17);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);


        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitInsn(ARETURN);
        Label label19 = new Label();
        methodVisitor.visitLabel(label19);
        methodVisitor.visitLocalVariable("this", "Lcom/example/httpparam/Test;", null, label0, label19, 0);
        methodVisitor.visitLocalVariable("params", "Ljava/util/Map;", "Ljava/util/Map<Ljava/lang/String;Lokhttp3/RequestBody;>;", label1, label19, 1);
        methodVisitor.visitMaxs(5, 2);
        methodVisitor.visitEnd();
    }
}
