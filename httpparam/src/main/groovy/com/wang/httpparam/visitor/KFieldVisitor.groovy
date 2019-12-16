package com.wang.httpparam.visitor


import com.wang.httpparam.Constants
import com.wang.httpparam.HttpParamPlugin
import com.wang.httpparam.mode.KField
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.Opcodes

/**
 * Author: wangxiaojie6
 * Date: 2019/3/29
 */
class KFieldVisitor extends FieldVisitor {

    private static final String IGNORE = "Lcom/wang/httpparam/Ignore;"
    private static final String FILE = "Lcom/wang/httpparam/PostFile;"
    private static final String PARAM_NAME = "Lcom/wang/httpparam/ParamName;"

    private List<KField> mFields
    private KField mField
    private boolean mAdd = true
    private boolean isFile = false

    KFieldVisitor(int api, FieldVisitor fieldVisitor, List<KField> fields, KField field) {
        super(api, fieldVisitor)
        mFields = fields
        mField = field
    }

    @Override
    AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        mAdd &= descriptor != IGNORE
        isFile |= descriptor == FILE
        if (mField.isReference || mField.isArray || mField.isList || mField.isMap) {
            mField.isNullable = !hasNonNull(descriptor)
        }
        AnnotationVisitor av = super.visitAnnotation(descriptor, visible)
        if (descriptor == FILE || descriptor == PARAM_NAME) {
            av = new KFieldAnnotationVisitor(Opcodes.ASM5, av, mField)
        }
        return av
    }

    private static boolean hasNonNull(String descriptor) {
        for (String nonnull : Constants.NON_NULL) {
            if (nonnull == descriptor) {
                return true
            }
        }
        return false
    }

    @Override
    void visitEnd() {
        if (mAdd) {
            if (isFile) {
                mField.initFileType()
            }
            if (HttpParamPlugin.DEBUG) {
                println "add field: $mField"
            }
            mFields.add(mField)
        }
        super.visitEnd()
    }
}
