package com.wang.httpparam.visitor

import com.wang.httpparam.HttpParamPlugin
import com.wang.httpparam.mode.KField
import com.wang.httpparam.mode.MethodHelper
import com.wang.httpparam.mode.ParamsMethodHelper
import org.objectweb.asm.AnnotationVisitor

/**
 * Author: wangxiaojie6
 * Date: 2019/3/28
 */
class KFieldAnnotationVisitor extends AnnotationVisitor {

    private KField mField

    KFieldAnnotationVisitor(int api, AnnotationVisitor annotationVisitor, KField field) {
        super(api, annotationVisitor)
        mField = field
    }

    @Override
    void visit(String name, Object value) {
        if (HttpParamPlugin.DEBUG) {
            println "name = [$name], value = [$value]"
        }
        switch (name){
            case "value":
            case "key":
                if (value instanceof String && !((String) value).isEmpty()) {
                    mField.customName = (String) value
                }
                break

        }
        super.visit(name, value)
    }

}
