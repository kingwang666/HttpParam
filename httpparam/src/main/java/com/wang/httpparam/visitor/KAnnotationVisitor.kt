package com.wang.httpparam.visitor

import com.wang.httpparam.Constants
import com.wang.httpparam.mode.GradleConfig
import com.wang.httpparam.mode.MethodHelper
import com.wang.httpparam.mode.ParamsMethodHelper
import org.objectweb.asm.AnnotationVisitor

/**
 * Author: wangxiaojie6
 * Date: 2019/3/28
 */
class KAnnotationVisitor constructor(
    annotationVisitor: AnnotationVisitor?,
    private val mHelper: MethodHelper,
    private val mConfig: GradleConfig
) :
    AnnotationVisitor(Constants.API_VERSION, annotationVisitor) {

    override fun visit(name: String, value: Any) {
        if (mConfig.debug) {
            println("name = [$name], value = [$value]");
        }
        if ("name" == name) {
            if (value is String && value.isNotEmpty()) {
                mHelper.methodName = value
            }
        } else if ("replace" == name) {
            if (value is Boolean) {
                mHelper.replace = value
            }
        } else if ("root" == name) {
            if (value is Boolean) {
                mHelper.root = value
            }
        }
        super.visit(name, value)
    }

    override fun visitEnum(name: String, descriptor: String, value: String) {
        if (mConfig.debug) {
            println("enum name = [$name], descriptor = [$descriptor], value = [$value]");
        }
        if ("type" == name) {
            if (mHelper is ParamsMethodHelper) {
                mHelper.setType(value)
            }
        }
        super.visitEnum(name, descriptor, value)
    }

    override fun visitEnd() {
        super.visitEnd()
        if (mConfig.debug) {
            println("");
            println("helper: $mHelper");
            println("");
        }
    }
}