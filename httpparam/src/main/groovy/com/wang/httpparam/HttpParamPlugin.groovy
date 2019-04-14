package com.wang.httpparam

import com.android.build.gradle.AppExtension
import com.wang.httpparam.mode.GradleConfig
import com.wang.httpparam.mode.GradleParams
import org.gradle.api.Plugin
import org.gradle.api.Project

class HttpParamPlugin implements Plugin<Project> {

    static boolean DEBUG = false

    static final String EXT_NAME = "httpParam"


    @Override
    void apply(Project project) {
        project.extensions.create(EXT_NAME, GradleParams)
        def android = project.extensions.getByType(AppExtension)
        HttpParamTransform transform = new HttpParamTransform(project)
        android.registerTransform(transform)
        project.afterEvaluate {
            initConfig(project, transform)
        }
    }

    static void initConfig(Project project, HttpParamTransform transform) {
        GradleParams params = project.extensions.findByName(EXT_NAME)
        DEBUG = params.debug
        GradleConfig config = new GradleConfig(params)
        if (DEBUG) {
            println "http param $params"
            println "http config $config"
        }
        transform.setConfig(config)
    }
}