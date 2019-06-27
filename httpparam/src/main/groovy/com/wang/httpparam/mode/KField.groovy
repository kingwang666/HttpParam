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

    public final String name

    public final String descriptor

    public final String classPath

    public final boolean isArray

    public final boolean isReference

    public final boolean isString

    public boolean isNullable

    KField(String name, String descriptor) {
        this.name = name
        this.descriptor = descriptor
        Pattern pattern = Pattern.compile("^\\[+?")
        Matcher matcher = pattern.matcher(descriptor)
        isArray = matcher.find()
        pattern = Pattern.compile("^\\[*?L(.+);\$")
        matcher = pattern.matcher(descriptor)
        isReference = matcher.matches()
        classPath = isReference ? matcher.group(1) : null
        isString = descriptor == Constants.DESC_STRING
        isNullable = isReference || isArray
    }

    void toString(MethodVisitor mv) {
        if (isArray) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/util/Arrays", "toString", "($descriptor)Ljava/lang/String;", false)
        } else if (isReference && !isString) {
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, classPath, "toString", "()Ljava/lang/String;", false)
        } else if (!isString) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/String", "valueOf", "($descriptor)Ljava/lang/String;", false)
        }
    }


    @Override
    String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", classPath='" + classPath + '\'' +
                ", isArray=" + isArray +
                ", isReference=" + isReference +
                ", isString=" + isString +
                ", isNullable=" + isNullable +
                '}'
    }
}
