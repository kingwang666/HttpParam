package com.wang.httpparam.mode

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import java.util.ArrayList
import java.util.function.Consumer

/**
 * Author: wangxiaojie6
 * Date: 2019/3/28
 */
abstract class MethodHelper constructor(
    @JvmField
    protected var mClassName: String,
    signature: String?,
    superName: String,
    returnDesc: String,
    returnSignature: String?,
    config: GradleConfig
) : ICodeInsert<ClassVisitor> {

    @JvmField
    protected var mConfig: GradleConfig = config

    @JvmField
    protected var mClassDesc: String = "L$mClassName;"

    @JvmField
    protected var mClassSign: String? = signature

    @JvmField
    protected var mSuperName: String = superName

    @JvmField
    protected var mReturnDesc: String = returnDesc

    @JvmField
    protected var mReturnSignature: String? = returnSignature

    @JvmField
    protected var mSame = false

    @JvmField
    protected var mArrayFirst = false

    @JvmField
    var methodName: String? = null

    @JvmField
    var root = false

    @JvmField
    var replace = false
    
    private val mLocalVariables: MutableList<LocalVariable> = ArrayList()

    override fun insert(cv: ClassVisitor, fields: List<KField>) {
        mSame = false
        mArrayFirst = true
        val mv = cv.visitMethod(Opcodes.ACC_PUBLIC, methodName, "()$mReturnDesc", if (mReturnSignature == null) null else "()$mReturnSignature", null)
        mv.visitCode()
        val l0 = Label()
        mv.visitLabel(l0)
        val start = init(mv)
        for (field in fields) {
            addField(mv, field)
        }
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitInsn(Opcodes.ARETURN)
        val end = Label()
        mv.visitLabel(end)
        mLocalVariables.forEach(Consumer { localVariable: LocalVariable -> localVariable.visit(mv) })
        addDefaultLocal(mv, l0, start, end)
        mv.visitMaxs(3, 2) //数字随意 因为ClassWriter.COMPUTE_MAXS
        mv.visitEnd()
    }

    protected fun addDefaultLocal(mv: MethodVisitor, l0: Label?, start: Label?, end: Label?) {
        mv.visitLocalVariable("this", mClassDesc, mClassSign, l0, end, 0)
        mv.visitLocalVariable("params", mReturnDesc, mReturnSignature, start, end, 1)
    }

    protected fun addLocalVariable(
        name: String,
        descriptor: String?,
        signature: String?,
        start: Label?,
        index: Int
    ): LocalVariable {
        val variable = LocalVariable(name, descriptor, signature, start, index)
        mLocalVariables.add(variable)
        return variable
    }

    protected abstract fun init(mv: MethodVisitor): Label
    protected open fun addField(mv: MethodVisitor, field: KField) {
        when (field.type) {
            KField.NORM -> addNormField(mv, field)
            KField.FILE -> addFileField(mv, field)
            KField.FILES_ARRAY -> addFilesArrayField(mv, field)
            KField.FILES_LIST -> addFilesListField(mv, field)
            KField.FILES_MAP -> addFilesMapField(mv, field)
        }
    }

    protected abstract fun addNormField(mv: MethodVisitor, field: KField)
    protected abstract fun addFileField(mv: MethodVisitor, field: KField)
    protected abstract fun addFilesArrayField(mv: MethodVisitor, field: KField)
    protected abstract fun addFilesListField(mv: MethodVisitor, field: KField)
    protected abstract fun addFilesMapField(mv: MethodVisitor, field: KField)

    companion object {
        private const val PARAMS = "Lcom/wang/httpparam/Params;"
        const val PARTS = "Lcom/wang/httpparam/Parts;"
        const val BODY = "Lcom/wang/httpparam/Body;"

        @JvmStatic
        fun create(
            className: String,
            signature: String?,
            superName: String,
            annotation: String,
            config: GradleConfig
        ): MethodHelper? {
            return when (annotation) {
                PARAMS -> {
                    ParamsMethodHelper(className, signature, superName, config)
                }
                PARTS -> {
                    PartsMethodHelper(className, signature, superName, config)
                }
                BODY -> {
                    BodyMethodHelper(className, signature, superName, config)
                }
                else -> null
            }
        }
    }


}