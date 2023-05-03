package com.wang.httpparam.visitor

import com.wang.httpparam.Constants
import com.wang.httpparam.mode.MethodHelper.Companion.create
import com.wang.httpparam.mode.GradleConfig
import com.wang.httpparam.mode.KField
import com.wang.httpparam.mode.MethodHelper
import org.objectweb.asm.*
import java.util.ArrayList

/**
 * Author: wangxiaojie6
 * Date: 2019/3/25
 */
class InsertMethodClassVisitor constructor(classVisitor: ClassVisitor, config: GradleConfig) :
    ClassVisitor(Constants.API_VERSION, classVisitor) {

    private var mName: String? = null
    private var mSuperName: String? = null
    private var mSignature: String? = null
    private var mRoot = false
    private val mHelpers: MutableList<MethodHelper>
    private val mFields: MutableList<KField>
    private val mConfig: GradleConfig

    init {
        mHelpers = ArrayList()
        mFields = ArrayList()
        mConfig = config
    }

    override fun visit(
        version: Int,
        access: Int,
        name: String,
        signature: String?,
        superName: String,
        interfaces: Array<String>
    ) {
        super.visit(version, access, name, signature, superName, interfaces)
        mName = name
        mSignature = signature
        mSuperName = superName
        mRoot = superName == Constants.CLASS_OBJECT
    }

    override fun visitAnnotation(descriptor: String, visible: Boolean): AnnotationVisitor {
        var av = super.visitAnnotation(descriptor, visible)
        val name = mName ?: return av
        val superName = mSuperName ?: return av
        val helper = create(name, mSignature, superName, descriptor, mConfig)
        if (helper != null) {
            if (mConfig.debug) {
                println("")
                println("class " + name + " create " + helper.javaClass.simpleName)
            }
            av = KAnnotationVisitor(av, helper, mConfig)
            helper.root = helper.root || mRoot
            mHelpers.add(helper)
        }
        return av
    }

    override fun visitField(
        access: Int,
        name: String,
        descriptor: String,
        signature: String?,
        value: Any?
    ): FieldVisitor {
        val fv = super.visitField(access, name, descriptor, signature, value)
        return if (mHelpers.isEmpty() || (access and Opcodes.ACC_STATIC) != 0) {
            fv
        } else KFieldVisitor(fv, mFields, KField(name, descriptor, signature), mConfig)
    }

    override fun visitMethod(
        access: Int,
        name: String,
        descriptor: String,
        signature: String?,
        exceptions: Array<String>?
    ): MethodVisitor? {
        if (preProcessHelpers(access, name, signature)) {
            if (mConfig.debug) {
                println("")
                println(
                    "class " + mName + " remove method: access = [" + access.toString() + "], name = [" + name + "], descriptor = [" + descriptor + "], signature = [" + signature + "], exceptions = [" +
                            exceptions.contentToString() + "]"
                )
            }
            return null
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions)
    }

    private fun preProcessHelpers(access: Int, name: String, signature: String?): Boolean {
        val iterator = mHelpers.iterator()
        while (iterator.hasNext()) {
            val helper = iterator.next()
            if (helper.methodName == name && (signature == null || signature.startsWith("()"))) {
                return if (helper.replace || access and Opcodes.ACC_SYNTHETIC == Opcodes.ACC_SYNTHETIC) {
                    true
                } else {
                    if (mConfig.debug) {
                        println("")
                        println("class $mName remove $helper")
                    }
                    iterator.remove()
                    false
                }
            }
        }
        return false
    }

    override fun visitEnd() {
        for (helper in mHelpers) {
            if (mConfig.debug) {
                println("")
                println(
                    "class " + mName + " start insert method " + helper.methodName + ", is root " + java.lang.String.valueOf(
                        helper.root
                    )
                )
            }
            helper.insert(cv, mFields)
            if (mConfig.debug) {
                println("")
                println("class " + mName + " inserted method " + helper.methodName)
                println("")
                println("")
                println("")
            }
        }
        super.visitEnd()
    }
}