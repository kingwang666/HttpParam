package com.example.httpparam;

/**
 * Created on 2019/6/27.
 * Author: bigwang
 * Description:
 */
public class Temp {
    {
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;", true)
        mv.visitVarInsn(Opcodes.ASTORE, 2)
        Label l1 = new Label()
        mv.visitLabel(l1)
        mv.visitFrame(Opcodes.F_APPEND, 1, ["java/util/Iterator"] as Object[], 0, null)
        mv.visitVarInsn(Opcodes.ALOAD, 2)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true)
        Label l2 = new Label()
        mv.visitJumpInsn(Opcodes.IFEQ, l2)
        mv.visitVarInsn(Opcodes.ALOAD, 2)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true)
        mv.visitTypeInsn(Opcodes.CHECKCAST, field.childClassPath)
        mv.visitVarInsn(Opcodes.ASTORE, 3)
        Label l3 = new Label()
        mv.visitLabel(l3)
        LocalVariable variable = addLocalVariable("file", field.childDescriptor, null, l4, 3)
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder")
        mv.visitInsn(Opcodes.DUP)
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false)
        mv.visitVarInsn(Opcodes.ALOAD, 3)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.key, "Ljava/lang/String;")
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
        mv.visitLdcInsn("\"; filename=\"")
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
        mv.visitVarInsn(Opcodes.ALOAD, 3)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.filename, "Ljava/lang/String;")
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false)
        mv.visitVarInsn(Opcodes.ALOAD, 3)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.mimeType, "Ljava/lang/String;")
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false)
        mv.visitVarInsn(Opcodes.ALOAD, 3)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.data, "Ljava/io/File;")
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;", false)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true)
        mv.visitInsn(Opcodes.POP)
        Label l4 = new Label()
        mv.visitLabel(l4)
        variable.end = l4
        mv.visitJumpInsn(Opcodes.GOTO, l1)
        mv.visitLabel(l2)
        mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null)
    }
}
