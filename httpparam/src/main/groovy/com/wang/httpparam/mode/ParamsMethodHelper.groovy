package com.wang.httpparam.mode

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * Author: wangxiaojie6
 * Date: 2019/3/28
 */
class ParamsMethodHelper extends MethodHelper {

    private static final int STRING = 1
    private static final int OBJECT = 2
    private static final int BODY = 3

    private int mType

    ParamsMethodHelper(String className, String signature, String superName, GradleConfig config) {
        super(className, signature, superName, "Ljava/util/Map;", "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;", config)
        methodName = "getParams"
        mType = STRING
    }

    void setType(String type) {
        switch (type) {
            case "STRING":
                mType = STRING
                mReturnSignature = "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;"
                break
            case "OBJECT":
                mType = OBJECT
                mReturnSignature = "Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>;"
                break
            case "BODY":
                mType = BODY
                mReturnSignature = "Ljava/util/Map<Ljava/lang/String;Lokhttp3/RequestBody;>;"
                break
        }
    }

    @Override
    protected Label init(MethodVisitor mv) {
        if (root) {
            mv.visitTypeInsn(Opcodes.NEW, mConfig.androidx ? "androidx/collection/ArrayMap" : "android.support.v4.util.ArrayMap")
            mv.visitInsn(Opcodes.DUP)
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, mConfig.androidx ? "androidx/collection/ArrayMap" : "android.support.v4.util.ArrayMap", "<init>", "()V", false)
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
    protected void addField(MethodVisitor mv, KField field) {
        switch (mType) {
            case STRING:
                addStringField(mv, field)
                break
            case OBJECT:
                addObjectField(mv, field)
                break
            case BODY:
                super.addField(mv, field)
                break
        }
    }


    private void addStringField(MethodVisitor mv, KField field) {
        if (field.isNullable) {
            if (mConfig.add) {
                //空的以空字符串形式加入
                mv.visitVarInsn(Opcodes.ALOAD, 1)
                mv.visitLdcInsn(field.name)
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                Label l1 = new Label()
                mv.visitJumpInsn(Opcodes.IFNONNULL, l1)
                mv.visitLdcInsn("") // 默认添加的字符串
                Label l2 = new Label()
                mv.visitJumpInsn(Opcodes.GOTO, l2)
                mv.visitLabel(l1)
                mv.visitFrame(Opcodes.F_FULL, 2, [mClassName, "java/util/Map"] as Object[], 2, ["java/util/Map", "java/lang/String"] as Object[])
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                field.toString(mv) //toString
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
                field.toString(mv) //toString
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
            field.toString(mv) //toString
            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true)
            mv.visitInsn(Opcodes.POP)
            Label l1 = new Label()
            mv.visitLabel(l1)
        }
    }

    private void addObjectField(MethodVisitor mv, KField field) {
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
                mv.visitLabel(l2)
                mv.visitFrame(Opcodes.F_FULL, 2, [mClassName, "java/util/Map"] as Object[], 3, ["java/util/Map", "java/lang/String", "java/io/Serializable"] as Object[])
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
            field.toObject(mv)
            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true)
            mv.visitInsn(Opcodes.POP)
            Label label = new Label()
            mv.visitLabel(label)
        }
    }

    @Override
    protected void addNormField(MethodVisitor mv, KField field) {
        if (field.isNullable) {
            if (mConfig.add) {
                mv.visitVarInsn(Opcodes.ALOAD, 1)
                mv.visitLdcInsn(field.name)
                mv.visitLdcInsn("text/plain")
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false)
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                Label l1 = new Label()
                mv.visitJumpInsn(Opcodes.IFNONNULL, l1)
                mv.visitLdcInsn("")
                Label l2 = new Label()
                mv.visitJumpInsn(Opcodes.GOTO, l2)
                mv.visitLabel(l1)
                mv.visitFrame(Opcodes.F_FULL, 2, [mClassName, "java/util/Map"] as Object[], 3, ["java/util/Map", "java/lang/String", "okhttp3/MediaType"] as Object[])
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                field.toString(mv)
                mv.visitLabel(l2)
                mv.visitFrame(Opcodes.F_FULL, 2, [mClassName, "java/util/Map"] as Object[], 4, ["java/util/Map", "java/lang/String", "okhttp3/MediaType", "java/lang/String"] as Object[])
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false)
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
                mv.visitLdcInsn("text/plain")
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false)
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                field.toString(mv)
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false)
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
            mv.visitLdcInsn("text/plain")
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false)
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            field.toString(mv)
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;", false)
            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true)
            mv.visitInsn(Opcodes.POP)
            Label label = new Label()
            mv.visitLabel(label)
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
        if (l1 == null) {
            l1 = new Label()
        }
        mv.visitLabel(l1)
        if (field.isNullable) {
            if (mSame) {
                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
            } else {
                mv.visitFrame(Opcodes.F_APPEND, 1, ["java/util/Map"] as Object[], 0, null)
                mSame = true
            }
        }
    }

    /**
     * 只支持List方式存储
     * @param mv
     * @param field
     */
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
        Label l5 = new Label()
        mv.visitLabel(l5)
        variable.end = l5
        mv.visitJumpInsn(Opcodes.GOTO, l3)
        mv.visitLabel(l1)
        mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null)

    }

    @Override
    String toString() {
        return "ParamsMethodHelper{" +
                "mType=" + mType +
                ", mClassName='" + mClassName + '\'' +
                ", mClassDesc='" + mClassDesc + '\'' +
                ", mClassSign='" + mClassSign + '\'' +
                ", mSuperName='" + mSuperName + '\'' +
                ", mReturnDesc='" + mReturnDesc + '\'' +
                ", mReturnSignature='" + mReturnSignature + '\'' +
                ", mSame=" + mSame +
                ", methodName='" + methodName + '\'' +
                ", root=" + root +
                ", replace=" + replace +
                '}'
    }
}
