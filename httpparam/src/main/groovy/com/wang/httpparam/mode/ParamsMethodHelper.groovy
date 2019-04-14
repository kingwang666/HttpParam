package com.wang.httpparam.mode

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * Author: wangxiaojie6
 * Date: 2019/3/28
 */
class ParamsMethodHelper extends MethodHelper {

    ParamsMethodHelper(String className, String signature, String superName, GradleConfig config) {
        super(className, signature, superName, "Ljava/util/Map;", "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;", config)
        methodName = "getParams"
        root = false
        replace = false
    }

    @Override
    protected void init(MethodVisitor mv) {
        if (root){
            mv.visitTypeInsn(Opcodes.NEW, "java/util/HashMap")
            mv.visitInsn(Opcodes.DUP)
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false)
            mv.visitVarInsn(Opcodes.ASTORE, 1)
        }else {
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, mSuperName, methodName, "()$mReturnDesc", false)
            mv.visitVarInsn(Opcodes.ASTORE, 1)
        }

    }

    @Override
    protected void addField(MethodVisitor mv, KField field) {
        if (field.isNullable) {
            if (mConfig.add) {
                //空的以空字符串形式加入
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
                mv.visitFrame(Opcodes.F_FULL, 2, [mClassName, "java/util/Map"] as Object[], 2, ["java/util/Map", "java/lang/String"] as Object[])
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                toString(mv, field)
                mv.visitLabel(l2)
                mv.visitFrame(Opcodes.F_FULL, 2, [mClassName, "java/util/Map"] as Object[], 3, ["java/util/Map", "java/lang/String", "java/lang/String"] as Object[])
                mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true)
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
                toString(mv, field)
                mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true)
                mv.visitInsn(Opcodes.POP)
                mv.visitLabel(l1)
                if (mSame) {
                    mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
                } else {
                    mv.visitFrame(Opcodes.F_APPEND, 1, ["java/util/Map"] as Object[], 0, null)
                    mSame = true
                }
            }
        } else {
            mv.visitVarInsn(Opcodes.ALOAD, 1)
            mv.visitLdcInsn(field.name)
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            toString(mv, field)
            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true)
            mv.visitInsn(Opcodes.POP)
            Label l1 = new Label()
            mv.visitLabel(l1)
        }
    }
}
