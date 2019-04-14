package com.example.httpparam

import com.wang.httpparam.Ignore
import com.wang.httpparam.Params

/**
 * Author: wang
 * Date: 2019/4/14
 */
@Params(replace = true)
class KotlinTest {

    var aa = 1

    var str:String? = "123"

    @Ignore
    var cc = 2

    fun getParams(): Map<String, String>? {
        return null
    }
}