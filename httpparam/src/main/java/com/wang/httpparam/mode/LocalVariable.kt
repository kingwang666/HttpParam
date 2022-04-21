package com.wang.httpparam.mode

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor

/**
 * Created on 2019/6/28.
 * Author: bigwang
 * Description:
 */
class LocalVariable constructor(
    @JvmField
    val name: String,
    @JvmField
    val descriptor: String?,
    @JvmField
    val signature: String?,

    private val start: Label?,
    private val index: Int
) {
    @JvmField
    var end: Label? = null

    fun visit(mv: MethodVisitor) {
        mv.visitLocalVariable(name, descriptor, signature, start, end, index)
    }
}