package com.example.httpparam;

/**
 * Created on 2019/6/27.
 * Author: bigwang
 * Description:
 */
public class Temp {
    {
//        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "getBody", "()Lokhttp3/MultipartBody$Builder;", null, null);
//        methodVisitor.visitCode();
//        Label label0 = new Label();
//        methodVisitor.visitLabel(label0);
//
//
//        methodVisitor.visitTypeInsn(NEW, "okhttp3/MultipartBody$Builder");
//        methodVisitor.visitInsn(DUP);
//        methodVisitor.visitMethodInsn(INVOKESPECIAL, "okhttp3/MultipartBody$Builder", "<init>", "()V", false);
//        methodVisitor.visitVarInsn(ASTORE, 1);
//
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/example/httpparam/BaseTest", "getBody", "()Lokhttp3/MultipartBody$Builder;", false);
//        methodVisitor.visitVarInsn(ASTORE, 1);
//
//        Label label1 = new Label();
//        methodVisitor.visitLabel(label1);
//
//        methodVisitor.visitVarInsn(ALOAD, 1);
//        methodVisitor.visitFieldInsn(GETSTATIC, "okhttp3/MultipartBody", "FORM", "Lokhttp3/MediaType;");
//        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "okhttp3/MultipartBody$Builder", "setType", "(Lokhttp3/MediaType;)Lokhttp3/MultipartBody$Builder;", false);
//        methodVisitor.visitInsn(POP);
//        Label label2 = new Label();
//        methodVisitor.visitLabel(label2);
//
//
//        methodVisitor.visitVarInsn(ALOAD, 1);
//        methodVisitor.visitLdcInsn("aLong");
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aLong", "J");
//        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(J)Ljava/lang/String;", false);
//        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "okhttp3/MultipartBody$Builder", "addFormDataPart", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody$Builder;", false);
//        methodVisitor.visitInsn(POP);
//        Label label3 = new Label();
//        methodVisitor.visitLabel(label3);
//
//
//        methodVisitor.visitVarInsn(ALOAD, 1);
//        methodVisitor.visitLdcInsn("aShort");
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "aShort", "S");
//        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(I)Ljava/lang/String;", false);
//        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "okhttp3/MultipartBody$Builder", "addFormDataPart", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody$Builder;", false);
//        methodVisitor.visitInsn(POP);
//        Label label4 = new Label();
//        methodVisitor.visitLabel(label4);
//
//
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "mTest", "Lcom/example/httpparam/Test;");
//        Label label5 = new Label();
//        methodVisitor.visitJumpInsn(IFNULL, label5);
//        Label label6 = new Label();
//        methodVisitor.visitLabel(label6);
//        methodVisitor.visitVarInsn(ALOAD, 1);
//        methodVisitor.visitLdcInsn("mTest");
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "mTest", "Lcom/example/httpparam/Test;");
//        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
//        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "okhttp3/MultipartBody$Builder", "addFormDataPart", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody$Builder;", false);
//        methodVisitor.visitInsn(POP);
//        methodVisitor.visitLabel(label5);
//        methodVisitor.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"okhttp3/MultipartBody$Builder"}, 0, null);
//
//        methodVisitor.visitVarInsn(ALOAD, 1);
//        methodVisitor.visitLdcInsn("cc");
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "cc", "[I");
//        Label label7 = new Label();
//        methodVisitor.visitJumpInsn(IFNONNULL, label7);
//        methodVisitor.visitLdcInsn("");
//        Label label8 = new Label();
//        methodVisitor.visitJumpInsn(GOTO, label8);
//        methodVisitor.visitLabel(label7);
//        methodVisitor.visitFrame(Opcodes.F_FULL, 2, new Object[]{"com/example/httpparam/Test", "okhttp3/MultipartBody$Builder"}, 2, new Object[]{"okhttp3/MultipartBody$Builder", "java/lang/String"});
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "cc", "[I");
//        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/util/Arrays", "toString", "([I)Ljava/lang/String;", false);
//        methodVisitor.visitLabel(label8);
//        methodVisitor.visitFrame(Opcodes.F_FULL, 2, new Object[]{"com/example/httpparam/Test", "okhttp3/MultipartBody$Builder"}, 3, new Object[]{"okhttp3/MultipartBody$Builder", "java/lang/String", "java/lang/String"});
//        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "okhttp3/MultipartBody$Builder", "addFormDataPart", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody$Builder;", false);
//        methodVisitor.visitInsn(POP);
//        Label label9 = new Label();
//        methodVisitor.visitLabel(label9);
//
//
//        methodVisitor.visitVarInsn(ALOAD, 1);
//        methodVisitor.visitLdcInsn("name");
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "name", "Ljava/lang/String;");
//        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "okhttp3/MultipartBody$Builder", "addFormDataPart", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody$Builder;", false);
//        methodVisitor.visitInsn(POP);
//        Label label10 = new Label();
//        methodVisitor.visitLabel(label10);
//
//
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "girls", "[Ljava/lang/String;");
//        Label label11 = new Label();
//        methodVisitor.visitJumpInsn(IFNULL, label11);
//        Label label12 = new Label();
//        methodVisitor.visitLabel(label12);
//        methodVisitor.visitVarInsn(ALOAD, 1);
//        methodVisitor.visitLdcInsn("girls");
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "girls", "[Ljava/lang/String;");
//        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/util/Arrays", "toString", "([Ljava/lang/Object;)Ljava/lang/String;", false);
//        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "okhttp3/MultipartBody$Builder", "addFormDataPart", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody$Builder;", false);
//        methodVisitor.visitInsn(POP);
//        methodVisitor.visitLabel(label11);
//        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
//
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "file", "Lcom/example/httpparam/FileInput;");
//        Label label13 = new Label();
//        methodVisitor.visitJumpInsn(IFNULL, label13);
//        Label label14 = new Label();
//        methodVisitor.visitLabel(label14);
//        methodVisitor.visitVarInsn(ALOAD, 1);
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "file", "Lcom/example/httpparam/FileInput;");
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "key", "Ljava/lang/String;");
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "file", "Lcom/example/httpparam/FileInput;");
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "filename", "Ljava/lang/String;");
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "file", "Lcom/example/httpparam/FileInput;");
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "mimeType", "Ljava/lang/String;");
//        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "file", "Lcom/example/httpparam/FileInput;");
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "data", "Ljava/io/File;");
//        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;", false);
//        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "okhttp3/MultipartBody$Builder", "addFormDataPart", "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody$Builder;", false);
//        methodVisitor.visitInsn(POP);
//        methodVisitor.visitLabel(label13);
//        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
//
//        methodVisitor.visitVarInsn(ALOAD, 1);
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "file2", "Lcom/example/httpparam/FileInput;");
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "key", "Ljava/lang/String;");
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "file2", "Lcom/example/httpparam/FileInput;");
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "filename", "Ljava/lang/String;");
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "file2", "Lcom/example/httpparam/FileInput;");
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "mimeType", "Ljava/lang/String;");
//        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "file2", "Lcom/example/httpparam/FileInput;");
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "data", "Ljava/io/File;");
//        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;", false);
//        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "okhttp3/MultipartBody$Builder", "addFormDataPart", "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody$Builder;", false);
//        methodVisitor.visitInsn(POP);
//        Label label15 = new Label();
//        methodVisitor.visitLabel(label15);
//
//
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "files", "Ljava/util/List;");
//        Label label16 = new Label();
//        methodVisitor.visitJumpInsn(IFNULL, label16);
//        Label label17 = new Label();
//        methodVisitor.visitLabel(label17);
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "files", "Ljava/util/List;");
//        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;", true);
//        methodVisitor.visitVarInsn(ASTORE, 2);
//        Label label18 = new Label();
//        methodVisitor.visitLabel(label18);
//        methodVisitor.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/util/Iterator"}, 0, null);
//        methodVisitor.visitVarInsn(ALOAD, 2);
//        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true);
//        methodVisitor.visitJumpInsn(IFEQ, label16);
//        methodVisitor.visitVarInsn(ALOAD, 2);
//        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true);
//        methodVisitor.visitTypeInsn(CHECKCAST, "com/example/httpparam/FileInput");
//        methodVisitor.visitVarInsn(ASTORE, 3);
//        Label label19 = new Label();
//        methodVisitor.visitLabel(label19);
//        methodVisitor.visitVarInsn(ALOAD, 1);
//        methodVisitor.visitVarInsn(ALOAD, 3);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "key", "Ljava/lang/String;");
//        methodVisitor.visitVarInsn(ALOAD, 3);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "filename", "Ljava/lang/String;");
//        methodVisitor.visitVarInsn(ALOAD, 3);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "mimeType", "Ljava/lang/String;");
//        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
//        methodVisitor.visitVarInsn(ALOAD, 3);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "data", "Ljava/io/File;");
//        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;", false);
//        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "okhttp3/MultipartBody$Builder", "addFormDataPart", "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody$Builder;", false);
//        methodVisitor.visitInsn(POP);
//        Label label20 = new Label();
//        methodVisitor.visitLabel(label20);
//        methodVisitor.visitJumpInsn(GOTO, label18);
//        methodVisitor.visitLabel(label16);
//        methodVisitor.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
//
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "files2", "Ljava/util/List;");
//        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;", true);
//        methodVisitor.visitVarInsn(ASTORE, 2);
//        Label label21 = new Label();
//        methodVisitor.visitLabel(label21);
//        methodVisitor.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/util/Iterator"}, 0, null);
//        methodVisitor.visitVarInsn(ALOAD, 2);
//        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true);
//        Label label22 = new Label();
//        methodVisitor.visitJumpInsn(IFEQ, label22);
//        methodVisitor.visitVarInsn(ALOAD, 2);
//        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true);
//        methodVisitor.visitTypeInsn(CHECKCAST, "com/example/httpparam/FileInput");
//        methodVisitor.visitVarInsn(ASTORE, 3);
//        Label label23 = new Label();
//        methodVisitor.visitLabel(label23);
//        methodVisitor.visitVarInsn(ALOAD, 1);
//        methodVisitor.visitVarInsn(ALOAD, 3);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "key", "Ljava/lang/String;");
//        methodVisitor.visitVarInsn(ALOAD, 3);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "filename", "Ljava/lang/String;");
//        methodVisitor.visitVarInsn(ALOAD, 3);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "mimeType", "Ljava/lang/String;");
//        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
//        methodVisitor.visitVarInsn(ALOAD, 3);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "data", "Ljava/io/File;");
//        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;", false);
//        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "okhttp3/MultipartBody$Builder", "addFormDataPart", "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody$Builder;", false);
//        methodVisitor.visitInsn(POP);
//        Label label24 = new Label();
//        methodVisitor.visitLabel(label24);
//        methodVisitor.visitJumpInsn(GOTO, label21);
//        methodVisitor.visitLabel(label22);
//        methodVisitor.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
//
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "files3", "Ljava/util/List;");
//        Label label25 = new Label();
//        methodVisitor.visitJumpInsn(IFNULL, label25);
//        Label label26 = new Label();
//        methodVisitor.visitLabel(label26);
//        methodVisitor.visitVarInsn(ALOAD, 0);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/Test", "files3", "Ljava/util/List;");
//        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;", true);
//        methodVisitor.visitVarInsn(ASTORE, 2);
//        Label label27 = new Label();
//        methodVisitor.visitLabel(label27);
//        methodVisitor.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/util/Iterator"}, 0, null);
//        methodVisitor.visitVarInsn(ALOAD, 2);
//        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true);
//        methodVisitor.visitJumpInsn(IFEQ, label25);
//        methodVisitor.visitVarInsn(ALOAD, 2);
//        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true);
//        methodVisitor.visitTypeInsn(CHECKCAST, "com/example/httpparam/FileInput");
//        methodVisitor.visitVarInsn(ASTORE, 3);
//        Label label28 = new Label();
//        methodVisitor.visitLabel(label28);
//        methodVisitor.visitVarInsn(ALOAD, 1);
//        methodVisitor.visitVarInsn(ALOAD, 3);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "key", "Ljava/lang/String;");
//        methodVisitor.visitVarInsn(ALOAD, 3);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "filename", "Ljava/lang/String;");
//        methodVisitor.visitVarInsn(ALOAD, 3);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "mimeType", "Ljava/lang/String;");
//        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false);
//        methodVisitor.visitVarInsn(ALOAD, 3);
//        methodVisitor.visitFieldInsn(GETFIELD, "com/example/httpparam/FileInput", "data", "Ljava/io/File;");
//        methodVisitor.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;", false);
//        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "okhttp3/MultipartBody$Builder", "addFormDataPart", "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody$Builder;", false);
//        methodVisitor.visitInsn(POP);
//        Label label29 = new Label();
//        methodVisitor.visitLabel(label29);
//        methodVisitor.visitJumpInsn(GOTO, label27);
//        methodVisitor.visitLabel(label25);
//        methodVisitor.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
//
//        methodVisitor.visitVarInsn(ALOAD, 1);
//        methodVisitor.visitInsn(ARETURN);
//        Label label30 = new Label();
//        methodVisitor.visitLabel(label30);
//
//        methodVisitor.visitLocalVariable("file", "Lcom/example/httpparam/FileInput;", null, label19, label20, 3);
//        methodVisitor.visitLocalVariable("file", "Lcom/example/httpparam/FileInput;", null, label23, label24, 3);
//        methodVisitor.visitLocalVariable("file", "Lcom/example/httpparam/FileInput;", null, label28, label29, 3);
//        methodVisitor.visitLocalVariable("this", "Lcom/example/httpparam/Test;", null, label0, label30, 0);
//        methodVisitor.visitLocalVariable("builder", "Lokhttp3/MultipartBody$Builder;", null, label1, label30, 1);
//        methodVisitor.visitMaxs(5, 4);
//        methodVisitor.visitEnd();
    }
}
