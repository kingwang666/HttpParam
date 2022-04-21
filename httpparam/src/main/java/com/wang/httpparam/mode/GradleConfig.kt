package com.wang.httpparam.mode

import java.io.Serializable
import java.util.ArrayList

/**
 * Author: wangxiaojie6
 * Date: 2019/3/25
 */
class GradleConfig constructor(params: GradleParams) : Serializable {

    @JvmField
    val debug: Boolean = params.debug

    @JvmField
    val add: Boolean = params.add

    @JvmField
    val androidx: Boolean = params.androidx

    @JvmField
    val okHttpV4: Boolean = params.okHttpV4

    @JvmField
    val fileParam: FileParam = FileParam(params.file)

    @JvmField
    val exclude = ArrayList<String>()

    init {
        exclude.add("android.arch")
        exclude.add("android.support")
        exclude.add("androidx")
        params.exclude?.also {
            exclude.addAll(it)
        }
    }

    override fun toString(): String {
        return "debug= $debug, add= $add, androidx= $androidx, okHttpV4= $okHttpV4, fileParam= $fileParam, exclude= $exclude"
    }

}