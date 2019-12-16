package com.wang.httpparam.mode

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * Created on 2019/6/29.
 * Author: bigwang
 * Description:
 */
class PartsMethodHelper extends MethodHelper implements Opcodes {

    PartsMethodHelper(String className, String signature, String superName, GradleConfig config) {
        super(className, signature, superName, "Ljava/util/List;", "Ljava/util/List<Lokhttp3/MultipartBody\$Part;>;", config)
        methodName = "getParts"
    }

    @Override
    protected Label init(MethodVisitor mv) {
        if (root) {
            mv.visitTypeInsn(NEW, "java/util/ArrayList")
            mv.visitInsn(DUP)
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false)
            mv.visitVarInsn(ASTORE, 1)
        } else {
            mv.visitVarInsn(ALOAD, 0)
            mv.visitMethodInsn(INVOKESPECIAL, mSuperName, methodName, "()$mReturnDesc", false)
            mv.visitVarInsn(ASTORE, 1)
        }
        Label start = new Label()
        mv.visitLabel(start)
        return start
    }

    @Override
    protected void addNormField(MethodVisitor mv, KField field) {
        if (field.isNullable) {
            if (mConfig.add) {
                mv.visitVarInsn(ALOAD, 1)
                //param name
                mv.visitLdcInsn(field.isCustom() ? field.customName : field.name)
                mv.visitVarInsn(ALOAD, 0)
                mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
                Label l1 = new Label()
                mv.visitJumpInsn(IFNONNULL, l1)
                mv.visitLdcInsn("")
                Label l2 = new Label()
                mv.visitJumpInsn(GOTO, l2)
                mv.visitLabel(l1)
                mv.visitFrame(F_FULL, 2, [mClassName, "java/util/List"] as Object[], 2, ["java/util/List", "java/lang/String"] as Object[])
                mv.visitVarInsn(ALOAD, 0)
                mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
                field.toString(mv)
                mv.visitLabel(l2)
                mv.visitFrame(F_FULL, 2, [mClassName, "java/util/List"] as Object[], 3, ["java/util/List", "java/lang/String", "java/lang/String"] as Object[])
                mv.visitMethodInsn(INVOKESTATIC, "okhttp3/MultipartBody\$Part", "createFormData", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody\$Part;", false)
                mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true)
                mv.visitInsn(POP)
                Label l3 = new Label()
                mv.visitLabel(l3)
            } else {
                mv.visitVarInsn(ALOAD, 0)
                mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
                Label l1 = new Label()
                mv.visitJumpInsn(IFNULL, l1)
                Label l2 = new Label()
                mv.visitLabel(l2)
                mv.visitVarInsn(ALOAD, 1)
                //param name
                mv.visitLdcInsn(field.isCustom() ? field.customName : field.name)
                mv.visitVarInsn(ALOAD, 0)
                mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
                field.toString(mv)
                mv.visitMethodInsn(INVOKESTATIC, "okhttp3/MultipartBody\$Part", "createFormData", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody\$Part;", false)
                mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true)
                mv.visitInsn(POP)
                mv.visitLabel(l1)
                if (mSame) {
                    mv.visitFrame(F_SAME, 0, null, 0, null)
                } else {
                    mv.visitFrame(F_APPEND, 1, ["java/util/List"] as Object[], 0, null)
                    mSame = true
                }
            }
        } else {
            mv.visitVarInsn(ALOAD, 1)
            //param name
            mv.visitLdcInsn(field.isCustom() ? field.customName : field.name)
            mv.visitVarInsn(ALOAD, 0)
            mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
            field.toString(mv)
            mv.visitMethodInsn(INVOKESTATIC, "okhttp3/MultipartBody\$Part", "createFormData", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody\$Part;", false)
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true)
            mv.visitInsn(POP)
            Label l1 = new Label()
            mv.visitLabel(l1)
        }
    }

    @Override
    protected void addFileField(MethodVisitor mv, KField field) {
        Label l1
        if (field.isNullable) {
            mv.visitVarInsn(ALOAD, 0)
            mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
            l1 = new Label()
            mv.visitJumpInsn(IFNULL, l1)
            Label l2 = new Label()
            mv.visitLabel(l2)
        }

        addPart(mv, field, 0)

        if (l1 == null) {
            l1 = new Label()
        }
        mv.visitLabel(l1)
        if (field.isNullable) {
            if (mSame) {
                mv.visitFrame(F_SAME, 0, null, 0, null)
            } else {
                mv.visitFrame(F_APPEND, 1, ["java/util/List"] as Object[], 0, null)
                mSame = true
            }
        }
    }

    @Override
    protected void addFilesArrayField(MethodVisitor mv, KField field) {
        Label l1
        if (field.isNullable) {
            mv.visitVarInsn(ALOAD, 0)
            mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
            l1 = new Label()
            mv.visitJumpInsn(IFNULL, l1)
            Label l2 = new Label()
            mv.visitLabel(l2)
        }

        mv.visitVarInsn(ALOAD, 0)
        mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitVarInsn(ASTORE, 2)
        mv.visitVarInsn(ALOAD, 2)
        mv.visitInsn(ARRAYLENGTH)
        mv.visitVarInsn(ISTORE, 3)
        mv.visitInsn(ICONST_0)
        mv.visitVarInsn(ISTORE, 4)
        Label l3 = new Label()
        mv.visitLabel(l3)
        if (mArrayFirst) {
            mv.visitFrame(F_FULL, 5, [mClassName, "java/util/List", field.descriptor, INTEGER, INTEGER] as Object[], 0, [] as Object[])
            mArrayFirst = false
        } else {
            mv.visitFrame(F_APPEND, 3, [field.descriptor, INTEGER, INTEGER] as Object[], 0, null)
        }
        mv.visitVarInsn(ILOAD, 4)
        mv.visitVarInsn(ILOAD, 3)
        if (l1 == null) {
            l1 = new Label()
        }
        mv.visitJumpInsn(IF_ICMPGE, l1)
        mv.visitVarInsn(ALOAD, 2)
        mv.visitVarInsn(ILOAD, 4)
        mv.visitInsn(AALOAD)
        mv.visitVarInsn(ASTORE, 5)
        Label l4 = new Label()
        mv.visitLabel(l4)
        LocalVariable variable = addLocalVariable("file", field.childDescriptor, null, l4, 5)

        addPart(mv, field, 5)

        Label l5 = new Label()
        mv.visitLabel(l5)
        variable.end = l5
        mv.visitIincInsn(4, 1)
        mv.visitJumpInsn(GOTO, l3)
        mv.visitLabel(l1)
        mv.visitFrame(F_CHOP, 3, null, 0, null)
    }

    @Override
    protected void addFilesListField(MethodVisitor mv, KField field) {
        Label l1
        if (field.isNullable) {
            mv.visitVarInsn(ALOAD, 0)
            mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
            l1 = new Label()
            mv.visitJumpInsn(IFNULL, l1)
            Label l2 = new Label()
            mv.visitLabel(l2)
        }
        mv.visitVarInsn(ALOAD, 0)
        mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;", true)
        mv.visitVarInsn(ASTORE, 2)
        Label l3 = new Label()
        mv.visitLabel(l3)
        mv.visitFrame(F_APPEND, 1, ["java/util/Iterator"] as Object[], 0, null)
        mv.visitVarInsn(ALOAD, 2)
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true)
        if (l1 == null) {
            l1 = new Label()
        }
        mv.visitJumpInsn(IFEQ, l1)
        mv.visitVarInsn(ALOAD, 2)
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true)
        mv.visitTypeInsn(CHECKCAST, field.childClassPath)
        mv.visitVarInsn(ASTORE, 3)
        Label l4 = new Label()
        mv.visitLabel(l4)
        LocalVariable variable = addLocalVariable("file", field.childDescriptor, null, l4, 3)

        addPart(mv, field, 3)

        Label l5 = new Label()
        mv.visitLabel(l5)
        variable.end = l5
        mv.visitJumpInsn(GOTO, l3)
        mv.visitLabel(l1)
        mv.visitFrame(F_CHOP, 1, null, 0, null)
    }

    @Override
    protected void addFilesMapField(MethodVisitor mv, KField field) {
        Label l1
        if (field.isNullable) {
            mv.visitVarInsn(ALOAD, 0)
            mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
            l1 = new Label()
            mv.visitJumpInsn(IFNULL, l1)
            Label l2 = new Label()
            mv.visitLabel(l2)
        }

        mv.visitVarInsn(ALOAD, 0)
        mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "entrySet", "()Ljava/util/Set;", true)
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Set", "iterator", "()Ljava/util/Iterator;", true)
        mv.visitVarInsn(ASTORE, 2)
        Label l3 = new Label()
        mv.visitLabel(l3)
        mv.visitFrame(F_APPEND, 1, ["java/util/Iterator"] as Object[], 0, null)
        mv.visitVarInsn(ALOAD, 2)
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true)
        if (l1 == null){
            l1 = new Label()
        }
        mv.visitJumpInsn(IFEQ, l1)
        mv.visitVarInsn(ALOAD, 2)
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true)
        mv.visitTypeInsn(CHECKCAST, "java/util/Map\$Entry")
        mv.visitVarInsn(ASTORE, 3)
        Label l4 = new Label()
        mv.visitLabel(l4)

        LocalVariable variable = addLocalVariable("entry", "Ljava/util/Map\$Entry;", "Ljava/util/Map\$Entry<Ljava/lang/String;${field.childDescriptor}>;", l4, 3)

        mv.visitVarInsn(ALOAD, 1)

        //load key
        mv.visitVarInsn(ALOAD, 3)
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map\$Entry", "getKey", "()Ljava/lang/Object;", true)
        mv.visitTypeInsn(CHECKCAST, "java/lang/String")

        //load name
        mv.visitVarInsn(ALOAD, 3)
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map\$Entry", "getValue", "()Ljava/lang/Object;", true)
        mv.visitTypeInsn(CHECKCAST, field.childClassPath)
        if (field.isJavaIOFile) {
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/File", "getName", "()Ljava/lang/String;", false)
        } else {
            mv.visitFieldInsn(GETFIELD, field.childClassPath, mConfig.fileParam.filename, "Ljava/lang/String;")
        }

        //load mimeType
        if (field.isJavaIOFile) {
            mv.visitVarInsn(ALOAD, 0)
            mv.visitVarInsn(ALOAD, 3)
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map\$Entry", "getValue", "()Ljava/lang/Object;", true)
            mv.visitTypeInsn(CHECKCAST, field.childClassPath)
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/File", "getName", "()Ljava/lang/String;", false)
            mv.visitMethodInsn(INVOKEVIRTUAL, mClassName, "guessMimeType", "(Ljava/lang/String;)Ljava/lang/String;", false)
        } else if (mConfig.fileParam.haveMimeType()) {
            mv.visitVarInsn(ALOAD, 3)
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map\$Entry", "getValue", "()Ljava/lang/Object;", true)
            mv.visitTypeInsn(CHECKCAST, field.childClassPath)
            mv.visitFieldInsn(GETFIELD, field.childClassPath, mConfig.fileParam.mimeType, "Ljava/lang/String;")
        } else {
            mv.visitVarInsn(ALOAD, 0)
            mv.visitVarInsn(ALOAD, 3)
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map\$Entry", "getValue", "()Ljava/lang/Object;", true)
            mv.visitTypeInsn(CHECKCAST, field.childClassPath)
            mv.visitFieldInsn(GETFIELD, field.childClassPath, mConfig.fileParam.filename, "Ljava/lang/String;")
            mv.visitMethodInsn(INVOKEVIRTUAL, mClassName, "guessMimeType", "(Ljava/lang/String;)Ljava/lang/String;", false)
        }
        mv.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false)

        //load data
        mv.visitVarInsn(ALOAD, 3)
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map\$Entry", "getValue", "()Ljava/lang/Object;", true)
        mv.visitTypeInsn(CHECKCAST, field.childClassPath)
        if (!field.isJavaIOFile) {
            mv.visitFieldInsn(GETFIELD, field.childClassPath, mConfig.fileParam.data, "Ljava/io/File;")
        }

        mv.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;", false)
        mv.visitMethodInsn(INVOKESTATIC, "okhttp3/MultipartBody\$Part", "createFormData", "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody\$Part;", false)
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true)
        mv.visitInsn(POP)
        Label l5 = new Label()
        mv.visitLabel(l5)

        variable.end = l5

        mv.visitJumpInsn(GOTO, l3)
        mv.visitLabel(l1)
        mv.visitFrame(F_CHOP, 1, null, 0, null)
    }

    private void addPart(MethodVisitor mv, KField field, int index) {

        mv.visitVarInsn(ALOAD, 1)

        //load key
        if (field.isCustom()) {
            mv.visitLdcInsn(field.customName)
        } else if (field.isJavaIOFile) {
            mv.visitLdcInsn(field.name)
        } else {
            mv.visitVarInsn(ALOAD, index)
            if (index == 0) {
                mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
                mv.visitFieldInsn(GETFIELD, field.classPath, mConfig.fileParam.key, "Ljava/lang/String;")
            } else {
                mv.visitFieldInsn(GETFIELD, field.childClassPath, mConfig.fileParam.key, "Ljava/lang/String;")
            }
        }

        //load name
        mv.visitVarInsn(ALOAD, index)
        if (index == 0) {
            mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
        }
        if (field.isJavaIOFile) {
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/File", "getName", "()Ljava/lang/String;", false)
        } else {
            mv.visitFieldInsn(GETFIELD, index == 0 ? field.classPath : field.childClassPath, mConfig.fileParam.filename, "Ljava/lang/String;")
        }

        //load mimeType
        if (field.isJavaIOFile) {
            mv.visitVarInsn(ALOAD, 0)
            mv.visitVarInsn(ALOAD, index)
            if (index == 0) {
                mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
            }
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/File", "getName", "()Ljava/lang/String;", false)
            mv.visitMethodInsn(INVOKEVIRTUAL, mClassName, "guessMimeType", "(Ljava/lang/String;)Ljava/lang/String;", false)
        } else if (mConfig.fileParam.haveMimeType()) {
            mv.visitVarInsn(ALOAD, index)
            if (index == 0) {
                mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
                mv.visitFieldInsn(GETFIELD, field.classPath, mConfig.fileParam.mimeType, "Ljava/lang/String;")
            } else {
                mv.visitFieldInsn(GETFIELD, field.childClassPath, mConfig.fileParam.mimeType, "Ljava/lang/String;")
            }
        } else {
            mv.visitVarInsn(ALOAD, 0)
            mv.visitVarInsn(ALOAD, index)
            if (index == 0) {
                mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
                mv.visitFieldInsn(GETFIELD, field.classPath, mConfig.fileParam.filename, "Ljava/lang/String;")
            } else {
                mv.visitFieldInsn(GETFIELD, field.childClassPath, mConfig.fileParam.filename, "Ljava/lang/String;")
            }
            mv.visitMethodInsn(INVOKEVIRTUAL, mClassName, "guessMimeType", "(Ljava/lang/String;)Ljava/lang/String;", false)
        }
        mv.visitMethodInsn(INVOKESTATIC, "okhttp3/MediaType", "parse", "(Ljava/lang/String;)Lokhttp3/MediaType;", false)


        //load data
        mv.visitVarInsn(ALOAD, index)
        if (index == 0) {
            mv.visitFieldInsn(GETFIELD, mClassName, field.name, field.descriptor)
        }
        if (!field.isJavaIOFile) {
            mv.visitFieldInsn(GETFIELD, index == 0 ? field.classPath : field.childClassPath, mConfig.fileParam.data, "Ljava/io/File;")
        }

        mv.visitMethodInsn(INVOKESTATIC, "okhttp3/RequestBody", "create", "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;", false)
        mv.visitMethodInsn(INVOKESTATIC, "okhttp3/MultipartBody\$Part", "createFormData", "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody\$Part;", false)
        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true)

        mv.visitInsn(POP)
    }


    @Override
    String toString() {
        return "PartsMethodHelper{" +
                "mConfig=" + mConfig +
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
