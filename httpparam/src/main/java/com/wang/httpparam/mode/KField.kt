package com.wang.httpparam.mode

import com.wang.httpparam.Constants
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import java.util.regex.Pattern

/**
 * Author: wangxiaojie6
 * Date: 2019/3/29
 *
 *
 * Element Type	   	Encoding
 * boolean	   	Z
 * byte	   	B
 * char	   	C
 * class or interface	   	Lclassname;
 * double	   	D
 * float	   	F
 * int	   	I
 * long	   	J
 * short	   	S
 */
class KField constructor(name: String, descriptor: String, signature: String?) {

    companion object {
        const val NORM = 1
        const val FILE = 2
        const val FILES_ARRAY = 3
        const val FILES_LIST = 4
        const val FILES_MAP = 5
    }

    @JvmField
    var type: Int

    @JvmField
    val name: String

    @JvmField
    val descriptor: String

    @JvmField
    val signature: String?

    @JvmField
    var childDescriptor: String? = null

    @JvmField
    var classPath: String? = null

    @JvmField
    var childClassPath: String? = null

    @JvmField
    var customName: String? = null

    @JvmField
    val isArray: Boolean

    /**
     * only support key is string
     */
    @JvmField
    val isMap: Boolean

    @JvmField
    val isList: Boolean

    @JvmField
    val isReference: Boolean

    @JvmField
    val isString: Boolean

    @JvmField
    val isJavaIOFile: Boolean

    @JvmField
    var isNullable: Boolean


    init {
        type = NORM
        this.name = name
        this.descriptor = descriptor
        this.signature = signature
        var pattern = Pattern.compile("^\\[+?")
        var matcher = pattern.matcher(descriptor)
        isArray = matcher.find()
        isList = descriptor == Constants.DESC_LIST
        isMap = descriptor == Constants.DESC_MAP
        if (signature != null) {
            pattern =
                if (isList) Pattern.compile("^\\[*?L.+?<(.+)>;$") else if (isMap) Pattern.compile("^\\[*?L.+?<Ljava/lang/String;(.+)>;$") else if (isArray) Pattern.compile(
                    "^\\[(.+)$"
                ) else Pattern.compile("^\\[*?L.+?<(.+)>;$")
            matcher = pattern.matcher(signature)
            if (matcher.matches()) {
                childDescriptor = matcher.group(1)
            }
        } else if (isArray) {
            pattern = Pattern.compile("^\\[(.+)$")
            matcher = pattern.matcher(descriptor)
            if (matcher.matches()) {
                childDescriptor = matcher.group(1)
            }
        }
        pattern = Pattern.compile("^\\[*?L(.+);$")
        matcher = pattern.matcher(descriptor)
        isReference = matcher.matches()
        classPath = if (isReference) {
            matcher.group(1)
        } else {
            referenceClassPath
        }
        val childDescriptor = this.childDescriptor
        if (childDescriptor != null) {
            matcher = pattern.matcher(childDescriptor)
            childClassPath = if (matcher.matches()) matcher.group(1) else null
        } else {
            childClassPath = null
        }
        isJavaIOFile =
            descriptor == Constants.DESC_FILE || childClassPath != null && childClassPath == Constants.CLASS_FILE
        isString = descriptor == Constants.DESC_STRING
        isNullable = isReference || isArray || isList || isMap
    }

    fun toString(mv: MethodVisitor) {
        if (isArray) {
            mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "java/util/Arrays",
                "toString",
                "($arrayToStringDesc)Ljava/lang/String;",
                false
            )
        } else if (isReference && !isString) {
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false)
        } else if (!isString) {
            mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "java/lang/String",
                "valueOf",
                "($stringValueOfDesc)Ljava/lang/String;",
                false
            )
        }
    }

    fun toObject(mv: MethodVisitor) {
        if (!isReference) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, classPath, "valueOf", "($descriptor)L$classPath;", false)
        }
    }

    /**
     * @return
     */
    private val arrayToStringDesc: String
        get() = if (isReference) "[Ljava/lang/Object;" else descriptor

    private val stringValueOfDesc: String
        get() = if (Constants.DESC_BYTE == descriptor || Constants.DESC_SHORT == descriptor) {
            Constants.DESC_INT
        } else {
            descriptor
        }

    val isCustom: Boolean
        get() = customName?.length ?: 0 > 0

    private val referenceClassPath: String?
        get() {
            when (descriptor) {
                Constants.DESC_BOOLEAN -> {
                    return Constants.CLASS_BOOLEAN
                }
                Constants.DESC_BYTE -> {
                    return Constants.CLASS_BYTE
                }
                Constants.DESC_CHAR -> {
                    return Constants.CLASS_CHAR
                }
                Constants.DESC_DOUBLE -> {
                    return Constants.CLASS_DOUBLE
                }
                Constants.DESC_FLOAT -> {
                    return Constants.CLASS_FLOAT
                }
                Constants.DESC_INT -> {
                    return Constants.CLASS_INT
                }
                Constants.DESC_LONG -> {
                    return Constants.CLASS_LONG
                }
                Constants.DESC_SHORT -> {
                    return Constants.CLASS_SHORT
                }
                else -> return null
            }
        }

    fun initFileType() {
        type = when {
            isList -> {
                FILES_LIST
            }
            isMap -> {
                FILES_MAP
            }
            isArray -> {
                FILES_ARRAY
            }
            else -> {
                FILE
            }
        }
    }

    override fun toString(): String {
        return "{type=$type, name='$name', descriptor='$descriptor', signature='$signature', childDescriptor='$childDescriptor', classPath='$classPath', childClassPath='$childClassPath', customName='$customName', isArray=$isArray, isMap=$isMap, isList=$isList, isReference=$isReference, isString=$isString, isJavaIOFile=$isJavaIOFile, isNullable=$isNullable}"
    }

}