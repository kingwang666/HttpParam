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
    public static final String PARTS = "Lcom/wang/httpparam/Parts;"
    public static final String BODY = "Lcom/wang/httpparam/Body;"

    protected GradleConfig mConfig
    protected String mClassName
    protected String mClassDesc
    protected String mClassSign
    protected String mSuperName
    protected String mReturnDesc
    protected String mReturnSignature

    protected boolean mSame

    public String methodName
    public boolean root = false
    public boolean replace = false

    private List<LocalVariable> mLocalVariables = new ArrayList<>()


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
            case PARTS:
                return new PartsMethodHelper(className, signature, superName, config)
            case BODY:
                return new BodyMethodHelper(className, signature, superName, config)
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

        Label start = init(mv)

        for (KField field : fields) {
            addField(mv, field)
        }

        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitInsn(Opcodes.ARETURN)
        Label end = new Label()
        mv.visitLabel(end)
        mLocalVariables.forEach { variable ->
            variable.visit(mv)
        }
        addDefaultLocal(mv, l0, start, end)
        mv.visitMaxs(3, 2) //数字随意 因为ClassWriter.COMPUTE_MAXS
        mv.visitEnd()
    }

    protected addDefaultLocal(MethodVisitor mv, Label l0, Label start, Label end) {
        mv.visitLocalVariable("this", mClassDesc, mClassSign, l0, end, 0)
        mv.visitLocalVariable("params", mReturnDesc, mReturnSignature, start, end, 1)
    }

    protected LocalVariable addLocalVariable(String name, String descriptor, String signature, Label start, int index) {
        LocalVariable variable = new LocalVariable(name, descriptor, signature, start, index)
        mLocalVariables.add(variable)
        return variable
    }

    protected abstract Label init(MethodVisitor mv);

    protected void addField(MethodVisitor mv, KField field) {
        switch (field.type) {
            case KField.NORM:
                addNormField(mv, field)
                break
            case KField.FILE:
                addFileField(mv, field)
                break
            case KField.FILES_ARRAY:
                break
            case KField.FILES_LIST:
                addFilesListField(mv, field)
                break
            case KField.FILES_MAP:
                break
        }
    }

    protected abstract void addNormField(MethodVisitor mv, KField field);

    protected abstract void addFileField(MethodVisitor mv, KField field);

    protected abstract void addFilesListField(MethodVisitor mv, KField field);


}
