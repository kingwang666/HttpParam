package com.wang.httpparam.mode

import com.wang.httpparam.Constants
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Author: wangxiaojie6
 * Date: 2019/3/29
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
class KField {

    public static final int NORM = 1
    public static final int FILE = 2
    public static final int FILES = 3

    public int mType

    public final String name

    public final String descriptor

    public final String signature

    public final String childDescriptor

    public final String classPath

    public final String childClassPath = null

    public final boolean isArray

    public final boolean isReference

    public final boolean isString

    public boolean isNullable

    KField(String name, String descriptor, String signature) {
        mType = NORM
        this.name = name
        this.descriptor = descriptor
        this.signature = signature
        Pattern pattern = Pattern.compile("^\\[+?")
        Matcher matcher = pattern.matcher(descriptor)
        isArray = matcher.find()
        if (signature != null) {
            pattern = Pattern.compile("^\\[*?L.+?<(.+)>;\$")
            matcher = pattern.matcher(signature)
            if (matcher.matches()){
                childDescriptor = matcher.group(1)
            }
        }
        pattern = Pattern.compile("^\\[*?L(.+);\$")
        matcher = pattern.matcher(descriptor)
        isReference = matcher.matches()
        if (isReference) {
            classPath = matcher.group(1)
        } else {
            classPath = getReferenceClassPath()
        }
        if (childDescriptor != null){
            matcher = pattern.matcher(childDescriptor)
            if (matcher.matches()){
                childClassPath = matcher.group(1)
            }
        }
        isString = descriptor == Constants.DESC_STRING
        isNullable = isReference || isArray
    }

    void toString(MethodVisitor mv) {
        if (isArray) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/util/Arrays", "toString", "(${getArrayToStringDesc()})Ljava/lang/String;", false)
        } else if (isReference && !isString) {
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, classPath, "toString", "()Ljava/lang/String;", false)
        } else if (!isString) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/String", "valueOf", "(${getStringValueOfDesc()})Ljava/lang/String;", false)
        }
    }

    void toObject(MethodVisitor mv) {
        if (!isReference) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, classPath, "valueOf", "($descriptor)L$classPath;", false)
        }
    }
    /**
     * todo descriptor 为多维数组情况
     * @return
     */
    String getArrayToStringDesc() {
        return isReference ? "[Ljava/lang/Object;" : descriptor
    }

    String getStringValueOfDesc() {
        switch (descriptor) {
            case Constants.DESC_BYTE:
            case Constants.DESC_SHORT:
                return Constants.DESC_INT
            default:
                return descriptor
        }
    }

    private String getReferenceClassPath() {
        switch (descriptor) {
            case Constants.DESC_BOOLEAN:
                return Constants.CLASS_BOOLEAN
            case Constants.DESC_BYTE:
                return Constants.CLASS_BYTE
            case Constants.DESC_CHAR:
                return Constants.CLASS_CHAR
            case Constants.DESC_DOUBLE:
                return Constants.CLASS_DOUBLE
            case Constants.DESC_FLOAT:
                return Constants.CLASS_FLOAT
            case Constants.DESC_INT:
                return Constants.CLASS_INT
            case Constants.DESC_LONG:
                return Constants.CLASS_LONG
            case Constants.DESC_SHORT:
                return Constants.CLASS_SHORT
        }
        return null
    }


    @Override
    String toString() {
        return "{name='" + name + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", signature='" + signature + '\'' +
                ", childDescriptor='" + childDescriptor + '\'' +
                ", classPath='" + classPath + '\'' +
                ", childClassPath='" + childClassPath + '\'' +
                ", isArray=" + isArray +
                ", isReference=" + isReference +
                ", isString=" + isString +
                ", isNullable=" + isNullable +
                '}'
    }
}
