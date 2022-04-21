package com.wang.httpparam.visitor

import com.wang.httpparam.Constants
import com.wang.httpparam.mode.GradleConfig
import com.wang.httpparam.mode.KField
import org.objectweb.asm.AnnotationVisitor

/**
 * Author: wangxiaojie6
 * Date: 2019/3/28
 */
class KFieldAnnotationVisitor constructor(
    annotationVisitor: AnnotationVisitor?,
    private val mField: KField,
    private val mConfig: GradleConfig
) :
    AnnotationVisitor(Constants.API_VERSION, annotationVisitor) {
    override fun visit(name: String, value: Any) {
        if (mConfig.debug) {
            println("name = [$name], value = [$value]");
        }
        if ("value" == name || "key" == name) {
            if (value is String && value.isNotEmpty()) {
                mField.customName = value
            }
        }
        super.visit(name, value)
    }
}