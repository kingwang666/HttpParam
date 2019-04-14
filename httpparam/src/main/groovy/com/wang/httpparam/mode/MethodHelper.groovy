package com.wang.httpparam.mode


import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * Author: wangxiaojie6
 * Date: 2019/3/28
 */
abstract class MethodHelper implements ICodeInsert<ClassVisitor> {

    private static final String PARAMS = "Lcom/wang/httpparam/Params;"

    protected GradleConfig mConfig
    protected String mClassName
    protected String mClassDesc
    protected String mClassSign
    protected String mSuperName
    protected String mReturnDesc
    protected String mReturnSignature

    protected boolean mSame

    public String methodName
    public boolean root
    public boolean replace


    MethodHelper(String className, String signature, String superName, String returnDesc, String returnSignature, GradleConfig config) {
        mClassName = className
        mClassDesc = "L$className;"
        mClassSign = signature
        mSuperName = superName
        mReturnDesc = returnDesc
        mReturnSignature = returnSignature
        mConfig = config
    }

    static MethodHelper create(String className, String signature, String superName, String annotation, GradleConfig config) {
        switch (annotation) {
            case PARAMS:
                return new ParamsMethodHelper(className, signature, superName, config)
        }
        return null
    }

    @Override
    void insert(ClassVisitor cv, List<KField> fields) {
        mSame = false
        MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC, methodName, "()$mReturnDesc", "()$mReturnSignature", null)
        mv.visitCode()
        Label l0 = new Label()
        mv.visitLabel(l0)

        init(mv)
        Label start = new Label()
        mv.visitLabel(start)

        for (KField field : fields) {
            addField(mv, field)
        }

        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitInsn(Opcodes.ARETURN)
        Label end = new Label()
        mv.visitLabel(end)

        mv.visitLocalVariable("this", mClassDesc, mClassSign, l0, end, 0)
        mv.visitLocalVariable("params", mReturnDesc, mReturnSignature, start, end, 1)
        mv.visitMaxs(3, 2) //数字随意 因为ClassWriter.COMPUTE_MAXS
        mv.visitEnd()
    }

    protected abstract void init(MethodVisitor mv);

    protected abstract void addField(MethodVisitor mv, KField field);

    protected static void toString(MethodVisitor mv, KField field) {
        if (field.isArray) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/util/Arrays", "toString", "($field.descriptor)Ljava/lang/String;", false)
        } else if (field.isReference && !field.isString) {
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, field.classPath, "toString", "()Ljava/lang/String;", false)
        } else if (!field.isString) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/String", "valueOf", "($field.descriptor)Ljava/lang/String;", false)
        }
    }


    @Override
    String toString() {
        return "{" +
                ", mClassName='" + mClassName + '\'' +
                ", mClassDesc='" + mClassDesc + '\'' +
                ", mClassSign='" + mClassSign + '\'' +
                ", mSuperName='" + mSuperName + '\'' +
                ", mReturnDesc='" + mReturnDesc + '\'' +
                ", mReturnSignature='" + mReturnSignature + '\'' +
                ", mSame=" + mSame +
                ", methodName='" + methodName + '\'' +
                ", root=" + root +
                ", replace=" + replace +
                '}'
    }
}
