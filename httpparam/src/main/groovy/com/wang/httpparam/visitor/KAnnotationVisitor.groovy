package com.wang.httpparam.visitor

import com.wang.httpparam.HttpParamPlugin
import com.wang.httpparam.mode.MethodHelper
import org.objectweb.asm.AnnotationVisitor

/**
 * Author: wangxiaojie6
 * Date: 2019/3/28
 */
class KAnnotationVisitor extends AnnotationVisitor {

    private MethodHelper mHelper

    KAnnotationVisitor(int api, AnnotationVisitor annotationVisitor, MethodHelper helper) {
        super(api, annotationVisitor)
        mHelper = helper
    }

    @Override
    void visit(String name, Object value) {
        if (HttpParamPlugin.DEBUG) {
            println "name = [$name], value = [$value]"
        }
        switch (name){
            case "name":
                if (value instanceof String && !((String) value).isEmpty()) {
                    mHelper.methodName = (String) value
                }
                break
            case "replace":
                if (value instanceof Boolean) {
                    mHelper.replace = (boolean) value
                }
                break
            case "root":
                if (value instanceof Boolean) {
                    mHelper.root = (boolean) value
                }
                break
        }
        super.visit(name, value)
    }

}
