package com.wang.httpparam.mode
/**
 * Author: wangxiaojie6
 * Date: 2019/3/25
 */
class GradleConfig {

    public final boolean debug

    public final boolean add

    public final boolean androidx

    public final FileParam fileParam

    public final ArrayList<String> exclude = [
            "android/arch/",
            "android/support/",
            "androidx/"
    ]

    GradleConfig(GradleParams params) {
        debug = params.debug
        add = params.add
        androidx = params.androidx
        fileParam = new FileParam(params.file)
        params.exclude.forEach { className ->
            className.replace(".", "/")
            exclude.add(className)
        }
    }


    @Override
    String toString() {
        return "debug= $debug, add= $add, androidx= $androidx, fileParam= $fileParam, exclude= $exclude"
    }
}
