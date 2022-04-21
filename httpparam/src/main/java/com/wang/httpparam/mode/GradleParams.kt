package com.wang.httpparam.mode

open class GradleParams {

    var debug = false
    var add = false
    var androidx = true
    var okHttpV4 = true
    var file: Map<String, Any>? = null
    var exclude: List<String>? = null

    override fun toString(): String {
        return "GradleParams{" +
                "debug=" + debug +
                ", add=" + add +
                ", androidx=" + androidx +
                ", okHttpV4=" + okHttpV4 +
                ", file=" + file +
                ", exclude=" + exclude +
                '}'
    }
}