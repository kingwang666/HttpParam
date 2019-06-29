package com.wang.httpparam.visitor

import com.wang.httpparam.Constants
import com.wang.httpparam.mode.GradleConfig
import com.wang.httpparam.mode.KField
import com.wang.httpparam.mode.MethodHelper
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * Author: wangxiaojie6
 * Date: 2019/3/25
 */
class InsertMethodClassVisitor extends ClassVisitor {

    private String mName
    private String mSuperName
    private String mSignature
    private boolean mRoot
    private List<MethodHelper> mHelpers
    private List<KField> mFields
    private GradleConfig mConfig

    InsertMethodClassVisitor(int api, ClassVisitor classVisitor, GradleConfig config) {
        super(api, classVisitor)
        mHelpers = new ArrayList<>()
        mFields = new ArrayList<>()
        mConfig = config
    }

    @Override
    void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces)
        mName = name
        mSignature = signature
        mSuperName = superName
        mRoot = superName == Constants.CLASS_OBJECT
    }


    @Override
    AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        AnnotationVisitor av = super.visitAnnotation(descriptor, visible)
        MethodHelper helper = MethodHelper.create(mName, mSignature, mSuperName, descriptor, mConfig)
        if (helper != null) {
            if (mConfig.debug) {
                println ""
                println "class $mName create ${helper.getClass().getSimpleName()}"
            }
            av = new KAnnotationVisitor(Opcodes.ASM5, av, helper)
            helper.root = helper.root || mRoot
            mHelpers.add(helper)
        }
        return av
    }

    @Override
    FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        FieldVisitor fv = super.visitField(access, name, descriptor, signature, value)
        if (mHelpers.isEmpty()){
            return fv
        }
        return new KFieldVisitor(Opcodes.ASM5, fv, mFields, new KField(name, descriptor, signature))
    }


    @Override
    MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (preProcessHelpers(name, signature)) {
            if (mConfig.debug){
                println ""
                println "class $mName remove method: access = [$access], name = [$name], descriptor = [$descriptor], signature = [$signature], exceptions = [$exceptions]"
            }
            return null
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions)
    }

    private boolean preProcessHelpers(String name, String signature) {
        Iterator<MethodHelper> iterator = mHelpers.iterator()
        while (iterator.hasNext()) {
            MethodHelper helper = iterator.next()
            if (helper.methodName == name && (signature == null || signature.startsWith("()"))) {
                if (helper.replace) {
                    return true
                } else {
                    iterator.remove()
                    return false
                }
            }
        }
        return false
    }

    @Override
    void visitEnd() {
        for (MethodHelper helper : mHelpers) {
            if (mConfig.debug){
                println("")
                println "class $mName start insert method ${helper.methodName}, is root ${helper.root}"
            }
            helper.insert(cv, mFields)
            if (mConfig.debug){
                println ""
                println "class $mName inserted method ${helper.methodName}"
                println ""
                println ""
                println ""
            }
        }
        super.visitEnd()

    }

}
