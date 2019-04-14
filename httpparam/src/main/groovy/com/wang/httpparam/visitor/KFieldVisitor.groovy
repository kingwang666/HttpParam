package com.wang.httpparam.visitor


import com.wang.httpparam.Constants
import com.wang.httpparam.HttpParamPlugin
import com.wang.httpparam.mode.KField
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.FieldVisitor

/**
 * Author: wangxiaojie6
 * Date: 2019/3/29
 */
class KFieldVisitor extends FieldVisitor {

    private static final String IGNORE = "Lcom/wang/httpparam/Ignore;"

    private List<KField> mFields
    private KField mField
    private boolean mAdd = true


    KFieldVisitor(int api, FieldVisitor fieldVisitor, List<KField> fields, KField field) {
        super(api, fieldVisitor)
        mFields = fields
        mField = field
    }

    @Override
    AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        mAdd &= descriptor != IGNORE
        if (mField.isReference || mField.isArray) {
            mField.isNullable = !hasNonNull(descriptor)
        }
        return super.visitAnnotation(descriptor, visible)
    }

    private static boolean hasNonNull(String descriptor){
        for (String nonnull : Constants.NON_NULL){
            if (nonnull == descriptor){
                return true
            }
        }
        return false
    }

    @Override
    void visitEnd() {
        if (mAdd){
            if (HttpParamPlugin.DEBUG){
                println "add field: $mField"
            }
            mFields.add(mField)
        }
        super.visitEnd()
    }
}
