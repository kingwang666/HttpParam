package com.wang.httpparam.mode;

import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor;

/**
 * Created on 2019/6/28.
 * Author: bigwang
 * Description:
 */
class LocalVariable {

    public final String name

    public final String descriptor

    public final String signature

    private final Label start
    public Label end
    private final int index

    LocalVariable(String name, String descriptor, String signature, Label start, int index) {
        this.name = name
        this.descriptor = descriptor
        this.signature = signature
        this.start = start
        this.index = index
    }

    void visit(MethodVisitor mv) {
        mv.visitLocalVariable(name, descriptor, signature, start, end, index)
    }
}
