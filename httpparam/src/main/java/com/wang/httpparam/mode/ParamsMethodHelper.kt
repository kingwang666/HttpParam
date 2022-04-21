package com.wang.httpparam.mode

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * Author: wangxiaojie6
 * Date: 2019/3/28
 */
class ParamsMethodHelper(className: String, signature: String?, superName: String, config: GradleConfig) :
    MethodHelper(
        className,
        signature,
        superName,
        "Ljava/util/Map;",
        "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;",
        config
    ), Opcodes {

    companion object {
        private const val STRING = 1
        private const val OBJECT = 2
        private const val BODY = 3
    }

    private var mType: Int


    init {
        methodName = "getParams"
        mType = STRING
    }

    fun setType(type: String?) {
        when (type) {
            "STRING" -> {
                mType = STRING
                mReturnSignature = "Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;"
            }
            "OBJECT" -> {
                mType = OBJECT
                mReturnSignature = "Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>;"
            }
            "BODY" -> {
                mType = BODY
                mReturnSignature = "Ljava/util/Map<Ljava/lang/String;Lokhttp3/RequestBody;>;"
            }
        }
    }

    override fun init(mv: MethodVisitor): Label {
        if (root) {
            mv.visitTypeInsn(
                Opcodes.NEW,
                if (mConfig.androidx) "androidx/collection/ArrayMap" else "android.support.v4.util.ArrayMap"
            )
            mv.visitInsn(Opcodes.DUP)
            mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                if (mConfig.androidx) "androidx/collection/ArrayMap" else "android.support.v4.util.ArrayMap",
                "<init>",
                "()V",
                false
            )
            mv.visitVarInsn(Opcodes.ASTORE, 1)
        } else {
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, mSuperName, methodName, "()$mReturnDesc", false)
            mv.visitVarInsn(Opcodes.ASTORE, 1)
        }
        val start = Label()
        mv.visitLabel(start)
        return start
    }

    override fun addField(mv: MethodVisitor, field: KField) {
        when (mType) {
            STRING -> addStringField(mv, field)
            OBJECT -> addObjectField(mv, field)
            BODY -> super.addField(mv, field)
        }
    }

    private fun addStringField(mv: MethodVisitor, field: KField) {
        if (field.isNullable) {
            if (mConfig.add) {
                //空的以空字符串形式加入
                mv.visitVarInsn(Opcodes.ALOAD, 1)
                //param name
                mv.visitLdcInsn(if (field.isCustom) field.customName else field.name)
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                val l1 = Label()
                mv.visitJumpInsn(Opcodes.IFNONNULL, l1)
                mv.visitLdcInsn("") // 默认添加的字符串
                val l2 = Label()
                mv.visitJumpInsn(Opcodes.GOTO, l2)
                mv.visitLabel(l1)
                mv.visitFrame(
                    Opcodes.F_FULL,
                    2,
                    arrayOf<Any>(mClassName, "java/util/Map"),
                    2,
                    arrayOf<Any>("java/util/Map", "java/lang/String")
                )
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                field.toString(mv) //toString
                mv.visitLabel(l2)
                mv.visitFrame(
                    Opcodes.F_FULL,
                    2,
                    arrayOf<Any>(mClassName, "java/util/Map"),
                    3,
                    arrayOf<Any>("java/util/Map", "java/lang/String", "java/lang/String")
                )
                mv.visitMethodInsn(
                    Opcodes.INVOKEINTERFACE,
                    "java/util/Map",
                    "put",
                    "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
                    true
                )
                mv.visitInsn(Opcodes.POP)
                val l3 = Label()
                mv.visitLabel(l3)
            } else {
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                val l1 = Label()
                mv.visitJumpInsn(Opcodes.IFNULL, l1)
                val l2 = Label()
                mv.visitLabel(l2)
                mv.visitVarInsn(Opcodes.ALOAD, 1)
                //param name
                mv.visitLdcInsn(if (field.isCustom) field.customName else field.name)
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                field.toString(mv) //toString
                mv.visitMethodInsn(
                    Opcodes.INVOKEINTERFACE,
                    "java/util/Map",
                    "put",
                    "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
                    true
                )
                mv.visitInsn(Opcodes.POP)
                mv.visitLabel(l1)
                if (mSame) {
                    mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
                } else {
                    mv.visitFrame(Opcodes.F_APPEND, 1, arrayOf<Any>("java/util/Map"), 0, null)
                    mSame = true
                }
            }
        } else {
            mv.visitVarInsn(Opcodes.ALOAD, 1)
            //param name
            mv.visitLdcInsn(if (field.isCustom) field.customName else field.name)
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            field.toString(mv) //toString
            mv.visitMethodInsn(
                Opcodes.INVOKEINTERFACE,
                "java/util/Map",
                "put",
                "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
                true
            )
            mv.visitInsn(Opcodes.POP)
            val l1 = Label()
            mv.visitLabel(l1)
        }
    }

    private fun addObjectField(mv: MethodVisitor, field: KField) = if (field.isNullable) {
        if (mConfig.add) {
            //空的以空字符串形式加入
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
                arrayOf<Any>(mClassName, "java/util/Map"),
                2,
                arrayOf<Any>("java/util/Map", "java/lang/String")
            )
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            mv.visitLabel(l2)
            mv.visitFrame(
                Opcodes.F_FULL,
                2,
                arrayOf<Any>(mClassName, "java/util/Map"),
                3,
                arrayOf<Any>("java/util/Map", "java/lang/String", "java/io/Serializable")
            )
            mv.visitMethodInsn(
                Opcodes.INVOKEINTERFACE,
                "java/util/Map",
                "put",
                "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
                true
            )
            mv.visitInsn(Opcodes.POP)
            val l3 = Label()
            mv.visitLabel(l3)
        } else {
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            val l1 = Label()
            mv.visitJumpInsn(Opcodes.IFNULL, l1)
            val l2 = Label()
            mv.visitLabel(l2)
            mv.visitVarInsn(Opcodes.ALOAD, 1)
            //param name
            mv.visitLdcInsn(if (field.isCustom) field.customName else field.name)
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
            mv.visitMethodInsn(
                Opcodes.INVOKEINTERFACE,
                "java/util/Map",
                "put",
                "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
                true
            )
            mv.visitInsn(Opcodes.POP)
            mv.visitLabel(l1)
            if (mSame) {
                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
            } else {
                mv.visitFrame(Opcodes.F_APPEND, 1, arrayOf<Any>("java/util/Map"), 0, null)
                mSame = true
            }
        }
    } else {
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        //param name
        mv.visitLdcInsn(if (field.isCustom) field.customName else field.name)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
        field.toObject(mv)
        mv.visitMethodInsn(
            Opcodes.INVOKEINTERFACE,
            "java/util/Map",
            "put",
            "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
            true
        )
        mv.visitInsn(Opcodes.POP)
        val label = Label()
        mv.visitLabel(label)
    }

    override fun addNormField(mv: MethodVisitor, field: KField) {
        if (field.isNullable) {
            if (mConfig.add) {
                mv.visitVarInsn(Opcodes.ALOAD, 1)
                //load key
                mv.visitLdcInsn(if (field.isCustom) field.customName else field.name)
                if (mConfig.okHttpV4) {
                    //load data
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
                        arrayOf<Any>(mClassName, "java/util/Map"),
                        2,
                        arrayOf<Any>("java/util/Map", "java/lang/String")
                    )
                    mv.visitVarInsn(Opcodes.ALOAD, 0)
                    mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                    field.toString(mv)
                    mv.visitLabel(l2)
                    mv.visitFrame(
                        Opcodes.F_FULL,
                        2,
                        arrayOf<Any>(mClassName, "java/util/Map"),
                        3,
                        arrayOf<Any>("java/util/Map", "java/lang/String", "java/lang/String")
                    )
                }


                //load mimeType
                mv.visitLdcInsn("text/plain")
                mv.visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "okhttp3/MediaType",
                    "parse",
                    "(Ljava/lang/String;)Lokhttp3/MediaType;",
                    false
                )
                if (!mConfig.okHttpV4) {
                    //load data
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
                        arrayOf<Any>(mClassName, "java/util/Map"),
                        3,
                        arrayOf<Any>("java/util/Map", "java/lang/String", "okhttp3/MediaType")
                    )
                    mv.visitVarInsn(Opcodes.ALOAD, 0)
                    mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                    field.toString(mv)
                    mv.visitLabel(l2)
                    mv.visitFrame(
                        Opcodes.F_FULL,
                        2,
                        arrayOf<Any>(mClassName, "java/util/Map"),
                        4,
                        arrayOf<Any>("java/util/Map", "java/lang/String", "okhttp3/MediaType", "java/lang/String")
                    )

                    //created
                    mv.visitMethodInsn(
                        Opcodes.INVOKESTATIC,
                        "okhttp3/RequestBody",
                        "create",
                        "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;",
                        false
                    )
                } else {
                    //created
                    mv.visitMethodInsn(
                        Opcodes.INVOKESTATIC,
                        "okhttp3/RequestBody",
                        "create",
                        "(Ljava/lang/String;Lokhttp3/MediaType;)Lokhttp3/RequestBody;",
                        false
                    )
                }
                mv.visitMethodInsn(
                    Opcodes.INVOKEINTERFACE,
                    "java/util/Map",
                    "put",
                    "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
                    true
                )
                mv.visitInsn(Opcodes.POP)
                val l3 = Label()
                mv.visitLabel(l3)
            } else {
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                val l1 = Label()
                mv.visitJumpInsn(Opcodes.IFNULL, l1)
                val l2 = Label()
                mv.visitLabel(l2)
                mv.visitVarInsn(Opcodes.ALOAD, 1)

                //load key
                mv.visitLdcInsn(if (field.isCustom) field.customName else field.name)
                if (mConfig.okHttpV4) {
                    //load data
                    mv.visitVarInsn(Opcodes.ALOAD, 0)
                    mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                    field.toString(mv)
                }


                //load mimeType
                mv.visitLdcInsn("text/plain")
                mv.visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "okhttp3/MediaType",
                    "parse",
                    "(Ljava/lang/String;)Lokhttp3/MediaType;",
                    false
                )
                if (!mConfig.okHttpV4) {
                    //load data
                    mv.visitVarInsn(Opcodes.ALOAD, 0)
                    mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                    field.toString(mv)

                    //created
                    mv.visitMethodInsn(
                        Opcodes.INVOKESTATIC,
                        "okhttp3/RequestBody",
                        "create",
                        "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;",
                        false
                    )
                } else {
                    //created
                    mv.visitMethodInsn(
                        Opcodes.INVOKESTATIC,
                        "okhttp3/RequestBody",
                        "create",
                        "(Ljava/lang/String;Lokhttp3/MediaType;)Lokhttp3/RequestBody;",
                        false
                    )
                }
                mv.visitMethodInsn(
                    Opcodes.INVOKEINTERFACE,
                    "java/util/Map",
                    "put",
                    "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
                    true
                )
                mv.visitInsn(Opcodes.POP)
                mv.visitLabel(l1)
                if (mSame) {
                    mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
                } else {
                    mv.visitFrame(Opcodes.F_APPEND, 1, arrayOf<Any>("java/util/Map"), 0, null)
                    mSame = true
                }
            }
        } else {
            mv.visitVarInsn(Opcodes.ALOAD, 1)
            //load key
            mv.visitLdcInsn(if (field.isCustom) field.customName else field.name)
            if (mConfig.okHttpV4) {
                //load data
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                field.toString(mv)
            }


            //load mimeType
            mv.visitLdcInsn("text/plain")
            mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "okhttp3/MediaType",
                "parse",
                "(Ljava/lang/String;)Lokhttp3/MediaType;",
                false
            )
            if (!mConfig.okHttpV4) {
                //load data
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                field.toString(mv)

                //created
                mv.visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "okhttp3/RequestBody",
                    "create",
                    "(Lokhttp3/MediaType;Ljava/lang/String;)Lokhttp3/RequestBody;",
                    false
                )
            } else {
                //created
                mv.visitMethodInsn(
                    Opcodes.INVOKESTATIC,
                    "okhttp3/RequestBody",
                    "create",
                    "(Ljava/lang/String;Lokhttp3/MediaType;)Lokhttp3/RequestBody;",
                    false
                )
            }
            mv.visitMethodInsn(
                Opcodes.INVOKEINTERFACE,
                "java/util/Map",
                "put",
                "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
                true
            )
            mv.visitInsn(Opcodes.POP)
            val label = Label()
            mv.visitLabel(label)
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
        addRequestBody(mv, field, 0)
        if (l1 == null) {
            l1 = Label()
        }
        mv.visitLabel(l1)
        if (field.isNullable) {
            if (mSame) {
                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null)
            } else {
                mv.visitFrame(Opcodes.F_APPEND, 1, arrayOf<Any>("java/util/Map"), 0, null)
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
                arrayOf<Any>(mClassName, "java/util/Map", field.descriptor, Opcodes.INTEGER, Opcodes.INTEGER),
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
        addRequestBody(mv, field, 5)
        val l5 = Label()
        mv.visitLabel(l5)
        variable.end = l5
        mv.visitIincInsn(4, 1)
        mv.visitJumpInsn(Opcodes.GOTO, l3)
        mv.visitLabel(l1)
        mv.visitFrame(Opcodes.F_CHOP, 3, null, 0, null)
    }

    /**
     * List方式存储
     *
     * @param mv
     * @param field
     */
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
        addRequestBody(mv, field, 3)
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
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder")
        mv.visitInsn(Opcodes.DUP)
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false)

        //load key
        mv.visitVarInsn(Opcodes.ALOAD, 3)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map\$Entry", "getKey", "()Ljava/lang/Object;", true)
        mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/String")
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "java/lang/StringBuilder",
            "append",
            "(Ljava/lang/String;)Ljava/lang/StringBuilder;",
            false
        )
        mv.visitLdcInsn("\"; filename=\"")
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "java/lang/StringBuilder",
            "append",
            "(Ljava/lang/String;)Ljava/lang/StringBuilder;",
            false
        )

        //load name
        mv.visitVarInsn(Opcodes.ALOAD, 3)
        mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map\$Entry", "getValue", "()Ljava/lang/Object;", true)
        mv.visitTypeInsn(Opcodes.CHECKCAST, field.childClassPath)
        if (field.isJavaIOFile) {
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/File", "getName", "()Ljava/lang/String;", false)
        } else {
            mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.filename, "Ljava/lang/String;")
        }
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "java/lang/StringBuilder",
            "append",
            "(Ljava/lang/String;)Ljava/lang/StringBuilder;",
            false
        )
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false)
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

            //create RequestBody
            mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "okhttp3/RequestBody",
                "create",
                "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;",
                false
            )
        } else {
            //create RequestBody
            mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "okhttp3/RequestBody",
                "create",
                "(Ljava/io/File;Lokhttp3/MediaType;)Lokhttp3/RequestBody;",
                false
            )
        }
        mv.visitMethodInsn(
            Opcodes.INVOKEINTERFACE,
            "java/util/Map",
            "put",
            "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
            true
        )
        mv.visitInsn(Opcodes.POP)
        val l5 = Label()
        mv.visitLabel(l5)
        variable.end = l5
        mv.visitJumpInsn(Opcodes.GOTO, l3)
        mv.visitLabel(l1)
        mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null)
    }

    /**
     * @param mv
     * @param field
     * @param index 0:Not for loop
     */
    private fun addRequestBody(mv: MethodVisitor, field: KField, index: Int) {
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder")
        mv.visitInsn(Opcodes.DUP)
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false)

        //load key
        if (field.isCustom) {
            mv.visitLdcInsn(field.customName + "\"; filename=\"")
        } else if (field.isJavaIOFile) {
            mv.visitLdcInsn(field.name + "\"; filename=\"")
        } else {
            mv.visitVarInsn(Opcodes.ALOAD, index)
            if (index == 0) {
                mv.visitFieldInsn(Opcodes.GETFIELD, mClassName, field.name, field.descriptor)
                mv.visitFieldInsn(Opcodes.GETFIELD, field.classPath, mConfig.fileParam.key, "Ljava/lang/String;")
            } else {
                mv.visitFieldInsn(Opcodes.GETFIELD, field.childClassPath, mConfig.fileParam.key, "Ljava/lang/String;")
            }
            mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "java/lang/StringBuilder",
                "append",
                "(Ljava/lang/String;)Ljava/lang/StringBuilder;",
                false
            )
            mv.visitLdcInsn("\"; filename=\"")
        }
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "java/lang/StringBuilder",
            "append",
            "(Ljava/lang/String;)Ljava/lang/StringBuilder;",
            false
        )

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
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "java/lang/StringBuilder",
            "append",
            "(Ljava/lang/String;)Ljava/lang/StringBuilder;",
            false
        )
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false)
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


        //load miniype
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

            //create RequestBody
            mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "okhttp3/RequestBody",
                "create",
                "(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;",
                false
            )
        } else {
            //create RequestBody
            mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "okhttp3/RequestBody",
                "create",
                "(Ljava/io/File;Lokhttp3/MediaType;)Lokhttp3/RequestBody;",
                false
            )
        }
        mv.visitMethodInsn(
            Opcodes.INVOKEINTERFACE,
            "java/util/Map",
            "put",
            "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;",
            true
        )
        mv.visitInsn(Opcodes.POP)
    }

    override fun toString(): String {
        return "ParamsMethodHelper{mType=$mType, mConfig=$mConfig, mClassName='$mClassName\', mClassDesc='$mClassDesc\', mClassSign='$mClassSign\', mSuperName='$mSuperName\', mReturnDesc='$mReturnDesc\', mReturnSignature='$mReturnSignature\', mSame=$mSame, methodName='$methodName\', root=$root, replace=$replace}"
    }
}