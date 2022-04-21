package com.wang.httpparam.mode

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * Created on 2019/6/29.
 * Author: bigwang
 * Description:
 */
class BodyMethodHelper constructor(className: String, signature: String?, superName: String, config: GradleConfig) :
    MethodHelper(className, signature, superName, "Lokhttp3/MultipartBody\$Builder;", null, config), Opcodes {

    init {
        methodName = "getBody"
    }

    override fun init(mv: MethodVisitor): Label {
        return if (root) {
            mv.visitTypeInsn(Opcodes.NEW, "okhttp3/MultipartBody\$Builder")
            mv.visitInsn(Opcodes.DUP)
            mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                "okhttp3/MultipartBody\$Builder",
                "<init>",
                "()V",
                false
            )
            mv.visitVarInsn(Opcodes.ASTORE, 1)
            val start = Label()
            mv.visitLabel(start)
            mv.visitVarInsn(Opcodes.ALOAD, 1)
            mv.visitFieldInsn(Opcodes.GETSTATIC, "okhttp3/MultipartBody", "FORM", "Lokhttp3/MediaType;")
            mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "okhttp3/MultipartBody\$Builder",
                "setType",
                "(Lokhttp3/MediaType;)Lokhttp3/MultipartBody\$Builder;",
                false
            )
            mv.visitInsn(Opcodes.POP)
            val label2 = Label()
            mv.visitLabel(label2)
            start
        } else {
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, mSuperName, methodName, "()$mReturnDesc", false)
            mv.visitVarInsn(Opcodes.ASTORE, 1)
            val start = Label()
            mv.visitLabel(start)
            start
        }
    }

    override fun addNormField(mv: MethodVisitor, field: KField) {
        if (field.isNullable) {
            if (mConfig.add) {
                mv.visitVarInsn(Opcodes.ALOAD, 1)
                //param name
                mv.visitLdcInsn(if (field.isCustom) field.customName else field.name)
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                val l1 = Label()
                mv.visitJumpInsn(Opcodes.IFNONNULL, l1)
                mv.visitLdcInsn("")
                val l2 = Label()
                mv.visitJumpInsn(Opcodes.GOTO, l2)
                mv.visitLabel(l1)
                mv.visitFrame(
                    Opcodes.F_FULL,
                    2,
                    arrayOf<Any>(mClassName, "okhttp3/MultipartBody\$Builder"),
                    2,
                    arrayOf<Any>("okhttp3/MultipartBody\$Builder", "java/lang/String")
                )
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                field.toString(mv)
                mv.visitLabel(l2)
                mv.visitFrame(
                    Opcodes.F_FULL,
                    2,
                    arrayOf<Any>(mClassName, "okhttp3/MultipartBody\$Builder"),
                    3,
                    arrayOf<Any>("okhttp3/MultipartBody\$Builder", "java/lang/String", "java/lang/String")
                )
                mv.visitMethodInsn(
                    Opcodes.INVOKEVIRTUAL,
                    "okhttp3/MultipartBody\$Builder",
                    "addFormDataPart",
                    "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody\$Builder;",
                    false
                )
                mv.visitInsn(Opcodes.POP)
                val l3 = Label()
                mv.visitLabel(l3)
            } else {
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                val label5 = Label()
                mv.visitJumpInsn(Opcodes.IFNULL, label5)
                val label6 = Label()
                mv.visitLabel(label6)
                mv.visitVarInsn(Opcodes.ALOAD, 1)
                //param name
                mv.visitLdcInsn(if (field.isCustom) field.customName else field.name)
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                field.toString(mv)
                mv.visitMethodInsn(
                    Opcodes.INVOKEVIRTUAL,
                    "okhttp3/MultipartBody\$Builder",
                    "addFormDataPart",
                    "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody\$Builder;",
                    false
                )
                mv.visitInsn(Opcodes.POP)
                mv.visitLabel(label5)
                if (mSame) {
                    mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
                } else {
                    mv.visitFrame(Opcodes.F_APPEND, 1, arrayOf<Any>("okhttp3/MultipartBody\$Builder"), 0, null)
                    mSame = true
                }
            }
        } else {
            mv.visitVarInsn(Opcodes.ALOAD, 1)
            //param name
            mv.visitLdcInsn(if (field.isCustom) field.customName else field.name)
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            field.toString(mv)
            mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "okhttp3/MultipartBody\$Builder",
                "addFormDataPart",
                "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody\$Builder;",
                false
            )
            mv.visitInsn(Opcodes.POP)
            val label4 = Label()
            mv.visitLabel(label4)
        }
    }

    override fun addFileField(mv: MethodVisitor, field: KField) {
        var l1: Label? = null
        if (field.isNullable) {
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            l1 = Label()
            mv.visitJumpInsn(Opcodes.IFNULL, l1)
            val l2 = Label()
            mv.visitLabel(l2)
        }
        addPart(mv, field, 0)
        if (l1 == null) {
            l1 = Label()
        }
        mv.visitLabel(l1)
        if (field.isNullable) {
            if (mSame) {
                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
            } else {
                mv.visitFrame(Opcodes.F_APPEND, 1, arrayOf<Any>("okhttp3/MultipartBody\$Builder"), 0, null)
                mSame = true
            }
        }
    }

    override fun addFilesArrayField(mv: MethodVisitor, field: KField) {
        var l1: Label? = null
        if (field.isNullable) {
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            l1 = Label()
            mv.visitJumpInsn(Opcodes.IFNULL, l1)
            val l2 = Label()
            mv.visitLabel(l2)
        }
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitVarInsn(Opcodes.ASTORE, 2)
        mv.visitVarInsn(Opcodes.ALOAD, 2)
        mv.visitInsn(Opcodes.ARRAYLENGTH)
        mv.visitVarInsn(Opcodes.ISTORE, 3)
        mv.visitInsn(Opcodes.ICONST_0)
        mv.visitVarInsn(Opcodes.ISTORE, 4)
        val l3 = Label()
        mv.visitLabel(l3)
        if (mArrayFirst) {
            mv.visitFrame(
                Opcodes.F_FULL,
                5,
                arrayOf<Any>(
                    mClassName,
                    "okhttp3/MultipartBody\$Builder",
                    field.descriptor,
                    Opcodes.INTEGER,
                    Opcodes.INTEGER
                ),
                0,
                arrayOfNulls(0)
            )
            mArrayFirst = false
        } else {
            mv.visitFrame(
                Opcodes.F_APPEND,
                3,
                arrayOf<Any>(field.descriptor, Opcodes.INTEGER, Opcodes.INTEGER),
                0,
                null
            )
        }
        mv.visitVarInsn(Opcodes.ILOAD, 4)
        mv.visitVarInsn(Opcodes.ILOAD, 3)
        if (l1 == null) {
            l1 = Label()
        }
        mv.visitJumpInsn(Opcodes.IF_ICMPGE, l1)
        mv.visitVarInsn(Opcodes.ALOAD, 2)
        mv.visitVarInsn(Opcodes.ILOAD, 4)
        mv.visitInsn(Opcodes.AALOAD)
        mv.visitVarInsn(Opcodes.ASTORE, 5)
        val l4 = Label()
        mv.visitLabel(l4)
        val variable = addLocalVariable("file", field.childDescriptor, null, l4, 5)
        addPart(mv, field, 5)
        val l5 = Label()
        mv.visitLabel(l5)
        variable.end = l5
        mv.visitIincInsn(4, 1)
        mv.visitJumpInsn(Opcodes.GOTO, l3)
        mv.visitLabel(l1)
        mv.visitFrame(Opcodes.F_CHOP, 3, null, 0, null)
    }

    override fun addFilesListField(mv: MethodVisitor, field: KField) {
        var l1: Label? = null
        if (field.isNullable) {
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            l1 = Label()
            mv.visitJumpInsn(Opcodes.IFNULL, l1)
            val l2 = Label()
            mv.visitLabel(l2)
        }
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;", true)
        mv.visitVarInsn(Opcodes.ASTORE, 2)
        val l3 = Label()
        mv.visitLabel(l3)
        mv.visitFrame(Opcodes.F_APPEND, 1, arrayOf<Any>("java/util/Iterator"), 0, null)
        mv.visitVarInsn(Opcodes.ALOAD, 2)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true)
        if (l1 == null) {
            l1 = Label()
        }
        mv.visitJumpInsn(Opcodes.IFEQ, l1)
        mv.visitVarInsn(Opcodes.ALOAD, 2)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true)
        mv.visitTypeInsn(Opcodes.CHECKCAST, field.childClassPath)
        mv.visitVarInsn(Opcodes.ASTORE, 3)
        val l4 = Label()
        mv.visitLabel(l4)
        val variable = addLocalVariable("file", field.childDescriptor, null, l4, 3)
        addPart(mv, field, 3)
        val l5 = Label()
        mv.visitLabel(l5)
        variable.end = l5
        mv.visitJumpInsn(Opcodes.GOTO, l3)
        mv.visitLabel(l1)
        mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null)
    }

    override fun addFilesMapField(mv: MethodVisitor, field: KField) {
        var l1: Label? = null
        if (field.isNullable) {
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            l1 = Label()
            mv.visitJumpInsn(Opcodes.IFNULL, l1)
            val l2 = Label()
            mv.visitLabel(l2)
        }
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map", "entrySet", "()Ljava/util/Set;", true)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Set", "iterator", "()Ljava/util/Iterator;", true)
        mv.visitVarInsn(Opcodes.ASTORE, 2)
        val l3 = Label()
        mv.visitLabel(l3)
        mv.visitFrame(Opcodes.F_APPEND, 1, arrayOf<Any>("java/util/Iterator"), 0, null)
        mv.visitVarInsn(Opcodes.ALOAD, 2)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true)
        if (l1 == null) {
            l1 = Label()
        }
        mv.visitJumpInsn(Opcodes.IFEQ, l1)
        mv.visitVarInsn(Opcodes.ALOAD, 2)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true)
        mv.visitTypeInsn(Opcodes.CHECKCAST, "java/util/Map\$Entry")
        mv.visitVarInsn(Opcodes.ASTORE, 3)
        val l4 = Label()
        mv.visitLabel(l4)
        val variable = addLocalVariable(
            "entry",
            "Ljava/util/Map\$Entry;",
            "Ljava/util/Map\$Entry<Ljava/lang/String;" + field.childDescriptor + ">;",
            l4,
            3
        )
        mv.visitVarInsn(Opcodes.ALOAD, 1)

        //load key
        mv.visitVarInsn(Opcodes.ALOAD, 3)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map\$Entry", "getKey", "()Ljava/lang/Object;", true)
        mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/String")

        //load name
        mv.visitVarInsn(Opcodes.ALOAD, 3)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map\$Entry", "getValue", "()Ljava/lang/Object;", true)
        mv.visitTypeInsn(Opcodes.CHECKCAST, field.childClassPath)
        if (field.isJavaIOFile) {
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/File", "getName", "()Ljava/lang/String;", false)
        } else {
            mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.filename, "Ljava/lang/String;")
        }
        if (mConfig.okHttpV4) {
            //load data
            mv.visitVarInsn(Opcodes.ALOAD, 3)
            mv.visitMethodInsn(
                Opcodes.INVOKEINTERFACE,
                "java/util/Map\$Entry",
                "getValue",
                "()Ljava/lang/Object;",
                true
            )
            mv.visitTypeInsn(Opcodes.CHECKCAST, field.childClassPath)
            if (!field.isJavaIOFile) {
                mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.data, "Ljava/io/File;")
            }
        }


        //load mimeType
        if (field.isJavaIOFile) {
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitVarInsn(Opcodes.ALOAD, 3)
            mv.visitMethodInsn(
                Opcodes.INVOKEINTERFACE,
                "java/util/Map\$Entry",
                "getValue",
                "()Ljava/lang/Object;",
                true
            )
            mv.visitTypeInsn(Opcodes.CHECKCAST, field.childClassPath)
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/File", "getName", "()Ljava/lang/String;", false)
            mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                mClassName,
                "guessMimeType",
                "(Ljava/lang/String;)Ljava/lang/String;",
                false
            )
        } else if (mConfig.fileParam.haveMimeType()) {
            mv.visitVarInsn(Opcodes.ALOAD, 3)
            mv.visitMethodInsn(
                Opcodes.INVOKEINTERFACE,
                "java/util/Map\$Entry",
                "getValue",
                "()Ljava/lang/Object;",
                true
            )
            mv.visitTypeInsn(Opcodes.CHECKCAST, field.childClassPath)
            mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.mimeType, "Ljava/lang/String;")
        } else {
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitVarInsn(Opcodes.ALOAD, 3)
            mv.visitMethodInsn(
                Opcodes.INVOKEINTERFACE,
                "java/util/Map\$Entry",
                "getValue",
                "()Ljava/lang/Object;",
                true
            )
            mv.visitTypeInsn(Opcodes.CHECKCAST, field.childClassPath)
            mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.filename, "Ljava/lang/String;")
            mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                mClassName,
                "guessMimeType",
                "(Ljava/lang/String;)Ljava/lang/String;",
                false
            )
        }
        mv.visitMethodInsn(
            Opcodes.INVOKESTATIC,
            "okhttp3/MediaType",
            "parse",
            "(Ljava/lang/String;)Lokhttp3/MediaType;",
            false
        )
        if (!mConfig.okHttpV4) {
            //load data
            mv.visitVarInsn(Opcodes.ALOAD, 3)
            mv.visitMethodInsn(
                Opcodes.INVOKEINTERFACE,
                "java/util/Map\$Entry",
                "getValue",
                "()Ljava/lang/Object;",
                true
            )
            mv.visitTypeInsn(Opcodes.CHECKCAST, field.childClassPath)
            if (!field.isJavaIOFile) {
                mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.data, "Ljava/io/File;")
            }

            //created
            mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "okhttp3/RequestBody",
                "create",
                "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;",
                false
            )
        } else {
            //created
            mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "okhttp3/RequestBody",
                "create",
                "(Ljava/io/File;Lokhttp3/MediaType;)Lokhttp3/RequestBody;",
                false
            )
        }
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "okhttp3/MultipartBody\$Builder",
            "addFormDataPart",
            "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody\$Builder;",
            false
        )
        mv.visitInsn(Opcodes.POP)
        val l5 = Label()
        mv.visitLabel(l5)
        variable.end = l5
        mv.visitJumpInsn(Opcodes.GOTO, l3)
        mv.visitLabel(l1)
        mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null)
    }

    private fun addPart(mv: MethodVisitor, field: KField, index: Int) {
        mv.visitVarInsn(Opcodes.ALOAD, 1)

        //load key
        if (field.isCustom) {
            mv.visitLdcInsn(field.customName)
        } else if (field.isJavaIOFile) {
            mv.visitLdcInsn(field.name)
        } else {
            mv.visitVarInsn(Opcodes.ALOAD, index)
            if (index == 0) {
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                mv.visitFieldInsn(Opcodes.GETFIELD, field.classPath, mConfig.fileParam.key, "Ljava/lang/String;")
            } else {
                mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.key, "Ljava/lang/String;")
            }
        }


        //load name
        mv.visitVarInsn(Opcodes.ALOAD, index)
        if (index == 0) {
            mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
        }
        if (field.isJavaIOFile) {
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/File", "getName", "()Ljava/lang/String;", false)
        } else {
            mv.visitFieldInsn(
                Opcodes.GETFIELD,
                if (index == 0) field.classPath else field.childClassPath,
                mConfig.fileParam.filename,
                "Ljava/lang/String;"
            )
        }
        if (mConfig.okHttpV4) {
            //load data
            mv.visitVarInsn(Opcodes.ALOAD, index)
            if (index == 0) {
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            }
            if (!field.isJavaIOFile) {
                mv.visitFieldInsn(
                    Opcodes.GETFIELD,
                    if (index == 0) field.classPath else field.childClassPath,
                    mConfig.fileParam.data,
                    "Ljava/io/File;"
                )
            }
        }


        //load mimeType
        if (field.isJavaIOFile) {
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitVarInsn(Opcodes.ALOAD, index)
            if (index == 0) {
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            }
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/File", "getName", "()Ljava/lang/String;", false)
            mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                mClassName,
                "guessMimeType",
                "(Ljava/lang/String;)Ljava/lang/String;",
                false
            )
        } else if (mConfig.fileParam.haveMimeType()) {
            mv.visitVarInsn(Opcodes.ALOAD, index)
            if (index == 0) {
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                mv.visitFieldInsn(Opcodes.GETFIELD, field.classPath, mConfig.fileParam.mimeType, "Ljava/lang/String;")
            } else {
                mv.visitFieldInsn(
                    Opcodes.GETFIELD,
                    field.childClassPath,
                    mConfig.fileParam.mimeType,
                    "Ljava/lang/String;"
                )
            }
        } else {
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitVarInsn(Opcodes.ALOAD, index)
            if (index == 0) {
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                mv.visitFieldInsn(Opcodes.GETFIELD, field.classPath, mConfig.fileParam.filename, "Ljava/lang/String;")
            } else {
                mv.visitFieldInsn(
                    Opcodes.GETFIELD,
                    field.childClassPath,
                    mConfig.fileParam.filename,
                    "Ljava/lang/String;"
                )
            }
            mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                mClassName,
                "guessMimeType",
                "(Ljava/lang/String;)Ljava/lang/String;",
                false
            )
        }
        mv.visitMethodInsn(
            Opcodes.INVOKESTATIC,
            "okhttp3/MediaType",
            "parse",
            "(Ljava/lang/String;)Lokhttp3/MediaType;",
            false
        )
        if (!mConfig.okHttpV4) {
            //load data
            mv.visitVarInsn(Opcodes.ALOAD, index)
            if (index == 0) {
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            }
            if (!field.isJavaIOFile) {
                mv.visitFieldInsn(
                    Opcodes.GETFIELD,
                    if (index == 0) field.classPath else field.childClassPath,
                    mConfig.fileParam.data,
                    "Ljava/io/File;"
                )
            }

            //created
            mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "okhttp3/RequestBody",
                "create",
                "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;",
                false
            )
        } else {
            //created
            mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "okhttp3/RequestBody",
                "create",
                "(Ljava/io/File;Lokhttp3/MediaType;)Lokhttp3/RequestBody;",
                false
            )
        }
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "okhttp3/MultipartBody\$Builder",
            "addFormDataPart",
            "(Ljava/lang/String;Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody\$Builder;",
            false
        )
        mv.visitInsn(Opcodes.POP)
    }

    override fun toString(): String {
        return "BodyMethodHelper{mConfig=$mConfig, mClassName='$mClassName', mClassDesc='$mClassDesc', mClassSign='$mClassSign', mSuperName='$mSuperName', mReturnDesc='$mReturnDesc', mReturnSignature='$mReturnSignature\', mSame=$mSame, mArrayFirst=$mArrayFirst, methodName='$methodName', root=$root, replace=$replace}"
    }

}