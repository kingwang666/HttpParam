package com.wang.httpparam.mode

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * Created on 2019/6/29.
 * Author: bigwang
 * Description:
 */
class PartsMethodHelper extends MethodHelper {

    PartsMethodHelper(String className, String signature, String superName, GradleConfig config) {
        super(className, signature, superName, "Ljava/util/List;", "Ljava/util/List<Lokhttp3/MultipartBody\$Part;>;", config)
        methodName = "getParts"
    }

    @Override
    protected Label init(MethodVisitor mv) {
        if (root) {
            mv.visitTypeInsn(Opcodes.NEW, "java/util/ArrayList")
            mv.visitInsn(Opcodes.DUP)
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false)
            mv.visitVarInsn(Opcodes.ASTORE, 1)
        } else {
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, mSuperName, methodName, "()$mReturnDesc", false)
            mv.visitVarInsn(Opcodes.ASTORE, 1)
        }
        Label start = new Label()
        mv.visitLabel(start)
        return start
    }

    @Override
    protected void addNormField(MethodVisitor mv, KField field) {
        if (field.isNullable) {
            if (mConfig.add) {
                mv.visitVarInsn(Opcodes.ALOAD, 1)
                mv.visitLdcInsn(field.name)
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                Label l1 = new Label()
                mv.visitJumpInsn(Opcodes.IFNONNULL, l1)
                mv.visitLdcInsn("")
                Label l2 = new Label()
                mv.visitJumpInsn(Opcodes.GOTO, l2)
                mv.visitLabel(l1)
                mv.visitFrame(Opcodes.F_FULL, 2, [mClassName, "java/util/List"] as Object[], 2, ["java/util/List", "java/lang/String"] as Object[])
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                field.toString(mv)
                mv.visitLabel(l2)
                mv.visitFrame(Opcodes.F_FULL, 2, [mClassName, "java/util/List"] as Object[], 3, ["java/util/List", "java/lang/String", "java/lang/String"] as Object[])
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/MultipartBody\$Part", "createFormData", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody\$Part;", false)
                mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true)
                mv.visitInsn(Opcodes.POP)
                Label l3 = new Label()
                mv.visitLabel(l3)
            } else {
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                Label l1 = new Label()
                mv.visitJumpInsn(Opcodes.IFNULL, l1)
                Label l2 = new Label()
                mv.visitLabel(l2)
                mv.visitVarInsn(Opcodes.ALOAD, 1)
                mv.visitLdcInsn(field.name)
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                field.toString(mv)
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/MultipartBody\$Part", "createFormData", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody\$Part;", false)
                mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true)
                mv.visitInsn(Opcodes.POP)
                mv.visitLabel(l1)
                if (mSame) {
                    mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
                } else {
                    mv.visitFrame(Opcodes.F_APPEND, 1, ["java/util/List"] as Object[], 0, null)
                    mSame = true
                }
            }
        } else {
            mv.visitVarInsn(Opcodes.ALOAD, 1)
            mv.visitLdcInsn(field.name)
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            field.toString(mv)
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/MultipartBody\$Part", "createFormData", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody\$Part;", false)
            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true)
            mv.visitInsn(Opcodes.POP)
            Label l1 = new Label()
            mv.visitLabel(l1)
        }
    }

    @Override
    protected void addFileField(MethodVisitor mv, KField field) {
        Label l1
        if (field.isNullable) {
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            l1 = new Label()
            mv.visitJumpInsn(Opcodes.IFNULL, l1)
            Label l2 = new Label()
            mv.visitLabel(l2)
        }
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.classPath, mConfig.fileParam.key, "Ljava/lang/String;")
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.classPath, mConfig.fileParam.filename, "Ljava/lang/String;")
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.classPath, mConfig.fileParam.mimeType, "Ljava/lang/String;")
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.classPath, mConfig.fileParam.data, "Ljava/io/File;")
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;", false)
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/MultipartBody\$Part", "createFormData", "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody\$Part;", false)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true)
        mv.visitInsn(Opcodes.POP)
        if (l1 == null) {
            l1 = new Label()
        }
        mv.visitLabel(l1)
        if (field.isNullable) {
            if (mSame) {
                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
            } else {
                mv.visitFrame(Opcodes.F_APPEND, 1, ["java/util/List"] as Object[], 0, null)
                mSame = true
            }
        }
    }

    @Override
    protected void addFilesListField(MethodVisitor mv, KField field) {
        Label l1
        if (field.isNullable) {
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            l1 = new Label()
            mv.visitJumpInsn(Opcodes.IFNULL, l1)
            Label l2 = new Label()
            mv.visitLabel(l2)
        }
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;", true)
        mv.visitVarInsn(Opcodes.ASTORE, 2)
        Label l3 = new Label()
        mv.visitLabel(l3)
        mv.visitFrame(Opcodes.F_APPEND, 1, ["java/util/Iterator"] as Object[], 0, null)
        mv.visitVarInsn(Opcodes.ALOAD, 2)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true)
        if (l1 == null) {
            l1 = new Label()
        }
        mv.visitJumpInsn(Opcodes.IFEQ, l1)
        mv.visitVarInsn(Opcodes.ALOAD, 2)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true)
        mv.visitTypeInsn(Opcodes.CHECKCAST, field.childClassPath)
        mv.visitVarInsn(Opcodes.ASTORE, 3)
        Label l4 = new Label()
        mv.visitLabel(l4)
        LocalVariable variable = addLocalVariable("file", field.childDescriptor, null, l4, 3)
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitVarInsn(Opcodes.ALOAD, 3)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.key, "Ljava/lang/String;")
        mv.visitVarInsn(Opcodes.ALOAD, 3)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.filename, "Ljava/lang/String;")
        mv.visitVarInsn(Opcodes.ALOAD, 3)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.mimeType, "Ljava/lang/String;")
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false)
        mv.visitVarInsn(Opcodes.ALOAD, 3)
        mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.data, "Ljava/io/File;")
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;", false)
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/MultipartBody\$Part", "createFormData", "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody\$Part;", false)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true)
        mv.visitInsn(Opcodes.POP)
        Label l5 = new Label()
        mv.visitLabel(l5)
        variable.end = l5
        mv.visitJumpInsn(Opcodes.GOTO, l3)
        mv.visitLabel(l1)
        mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null)
    }
}
