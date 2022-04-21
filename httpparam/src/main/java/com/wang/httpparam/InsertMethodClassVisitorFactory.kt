package com.wang.httpparam

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.wang.httpparam.mode.GradleParameter
import com.wang.httpparam.visitor.InsertMethodClassVisitor
import org.objectweb.asm.ClassVisitor

abstract class InsertMethodClassVisitorFactory : AsmClassVisitorFactory<GradleParameter> {

    companion object{
        private const val ANNOTATION_PARAMS = "com.wang.httpparam.Params"
        private const val ANNOTATION_BODY = "com.wang.httpparam.Body"
        private const val ANNOTATION_PARTS = "com.wang.httpparam.Parts"
    }


    override fun createClassVisitor(classContext: ClassContext, nextClassVisitor: ClassVisitor): ClassVisitor {
        return InsertMethodClassVisitor(nextClassVisitor, parameters.get().config.get())
    }

    override fun isInstrumentable(classData: ClassData): Boolean {
        val config = parameters.get().config.get()
        if (classData.superClasses.isEmpty() || classData.classAnnotations.isEmpty()){
            return false
        }
        if (classData.className == "R.class" || classData.className.startsWith("R$") || classData.className == "BuildConfig") {
            return false
        }
        config.exclude.forEach {
            if (classData.className.startsWith(it)) {
                return false
            }
        }
        return classData.classAnnotations.contains(ANNOTATION_PARAMS)
                || classData.classAnnotations.contains(ANNOTATION_PARTS)
                || classData.classAnnotations.contains(ANNOTATION_BODY)
    }
}