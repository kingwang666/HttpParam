package com.wang.httpparam

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class HttpParamPlugin implements Plugin<Project> {

    static boolean DEBUG = false

    static final String EXT_NAME = "testplugin"


    @Override
    void apply(Project project) {
//        project.extensions.create(EXT_NAME, TestParams)
//        def android = project.extensions.getByType(AppExtension)
//        TestTransform transform = new TestTransform(project)
//        android.registerTransform(transform)
//        project.afterEvaluate {
//            initConfig(project, transform)
//        }
    }

//    static void initConfig(Project project, TestTransform transform) {
////        TestParams params = project.extensions.findByName(EXT_NAME)
////        DEBUG = params.debug
////        TestConfig config = new TestConfig(params)
////        if (DEBUG) {
////            println "plugin params $params"
////            println "config $config"
////        }
////        transform.setConfig(config)
//    }
}