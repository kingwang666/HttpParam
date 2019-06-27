package com.wang.httpparam.mode

import org.objectweb.asm.MethodVisitor;

/**
 * Created on 2019/6/27.
 * Author: bigwang
 * Description:
 */
class ParamsObjectMethodHelper extends MethodHelper {

    ParamsObjectMethodHelper(String className, String signature, String superName, String returnDesc, String returnSignature, GradleConfig config) {
        super(className, signature, superName, returnDesc, returnSignature, config)
    }

    @Override
    protected void init(MethodVisitor mv) {

    }

    @Override
    protected void addField(MethodVisitor mv, KField field) {

    }
}
