package com.wang.httpparam

import org.objectweb.asm.Opcodes

/**
 * Author: wangxiaojie6
 * Date: 2019/3/29
 */
interface Constants {
    companion object {

        const val API_VERSION = Opcodes.ASM9

        const val CLASS_OBJECT = "java/lang/Object"
        const val DESC_STRING = "Ljava/lang/String;"
        const val DESC_LIST = "Ljava/util/List;"
        const val DESC_FILE = "Ljava/io/File;"
        const val DESC_MAP = "Ljava/util/Map;"
        const val DESC_BOOLEAN = "Z"
        const val CLASS_BOOLEAN = "java/lang/Boolean"
        const val DESC_BYTE = "B"
        const val CLASS_BYTE = "java/lang/Byte"
        const val DESC_CHAR = "C"
        const val CLASS_CHAR = "java/lang/Character"
        const val DESC_DOUBLE = "D"
        const val CLASS_DOUBLE = "java/lang/Double"
        const val DESC_FLOAT = "F"
        const val CLASS_FLOAT = "java/lang/Float"
        const val DESC_INT = "I"
        const val CLASS_INT = "java/lang/Integer"
        const val DESC_LONG = "J"
        const val CLASS_LONG = "java/lang/Long"
        const val DESC_SHORT = "S"
        const val CLASS_SHORT = "java/lang/Short"
        const val CLASS_FILE = "java/io/File"
        val NON_NULL = arrayOf(
            "Landroidx/annotation/NonNull;",
            "Landroid/support/annotation/NonNull;",
            "Landroid/annotation/NonNull;",
            "Lorg/jetbrains/annotations/NotNull;"
        )
    }
}