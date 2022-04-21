package com.wang.httpparam.visitor

import com.wang.httpparam.Constants
import com.wang.httpparam.mode.GradleConfig
import com.wang.httpparam.mode.KField
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Attribute
import org.objectweb.asm.FieldVisitor

/**
 * Author: wangxiaojie6
 * Date: 2019/3/29
 */
class KFieldVisitor(
    fieldVisitor: FieldVisitor?,
    private val mFields: MutableList<KField>,
    private val mField: KField,
    private val mConfig: GradleConfig
) : FieldVisitor(Constants.API_VERSION, fieldVisitor) {

    companion object {
        private const val IGNORE = "Lcom/wang/httpparam/Ignore;"
        private const val FILE = "Lcom/wang/httpparam/PostFile;"
        private const val PARAM_NAME = "Lcom/wang/httpparam/ParamName;"

        private fun hasNonNull(descriptor: String): Boolean {
            for (nonnull in Constants.NON_NULL) {
                if (nonnull == descriptor) {
                    return true
                }
            }
            return false
        }
    }

    private var mAdd = true
    private var isFile = false

    override fun visitAttribute(attribute: Attribute) {
        super.visitAttribute(attribute)
        if (mConfig.debug) {
            println(mField.name + " field attribute: " + attribute);
        }
    }

    override fun visitAnnotation(descriptor: String, visible: Boolean): AnnotationVisitor {
        mAdd = mAdd && descriptor != IGNORE
        isFile = isFile || descriptor == FILE
        if (mField.isReference || mField.isArray || mField.isList || mField.isMap) {
            mField.isNullable = !hasNonNull(descriptor)
        }
        var av = super.visitAnnotation(descriptor, visible)
        if (descriptor == FILE || descriptor == PARAM_NAME) {
            av = KFieldAnnotationVisitor(av, mField, mConfig)
        }
        return av
    }

    override fun visitEnd() {
        if (mAdd) {
            if (isFile) {
                mField.initFileType()
            }

            if (mConfig.debug) {
                println("add field: $mField");
            }
            mFields.add(mField)
        }
        super.visitEnd()
    }


}