package com.example.httpparam;

/**
 * Created on 2019/6/27.
 * Author: bigwang
 * Description:
 */
public class Temp {
    {
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder")
        mv.visitInsn(Opcodes.DUP)
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.classPath, mConfig.fileParam.key, "Ljava/lang/String;")
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
        mv.visitLdcInsn("\"; filename=\"")
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.classPath, mConfig.fileParam.filename, "Ljava/lang/String;")
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.classPath, mConfig.fileParam.mimeType, "Ljava/lang/String;")
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.classPath, mConfig.fileParam.data, "Ljava/io/File;")
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;", false)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true)
        mv.visitInsn(Opcodes.POP)
        Label label = new Label()
        mv.visitLabel(label)
    }
}
