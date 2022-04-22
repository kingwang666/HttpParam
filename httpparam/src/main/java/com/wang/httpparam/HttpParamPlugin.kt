package com.wang.httpparam

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import com.wang.httpparam.mode.GradleConfig
import com.wang.httpparam.mode.GradleParams
import org.gradle.api.Plugin
import org.gradle.api.Project

class HttpParamPlugin : Plugin<Project> {

    companion object {

        private const val EXT_NAME = "httpParam"

    }

    override fun apply(project: Project) {
        project.extensions.create(EXT_NAME, GradleParams::class.java)

        val androidComponents = project.extensions.getByType(
            AndroidComponentsExtension::class.java
        )

        androidComponents.onVariants { variant ->
            variant.transformClassesWith(InsertMethodClassVisitorFactory::class.java, InstrumentationScope.ALL) {
                it.config.set(parseConfig(project))
            }
            variant.setAsmFramesComputationMode(FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_CLASSES)
        }
    }

    private fun parseConfig(project: Project): GradleConfig {
        val params = project.extensions.findByName(EXT_NAME) as? GradleParams ?: GradleParams()
        val config = GradleConfig(params)
        if (config.debug) {
            println("http param $params")
            println("http config $config")
        }
        return config
    }

}