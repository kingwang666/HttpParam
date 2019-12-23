package com.example.httpparam

import android.os.Environment
import androidx.collection.ArrayMap
import com.wang.httpparam.Body
import com.wang.httpparam.Ignore
import com.wang.httpparam.Params
import com.wang.httpparam.PostFile
import java.io.File

/**
 * Author: wang
 * Date: 2019/4/14
 */
@Params(replace = true)
@Body
open class KotlinTest : BaseTest() {

    var aa = 1

    var str: String? = "123"

    @Ignore
    var cc = 2


    @PostFile(key = "123File")
    var file: File? = File(Environment.getExternalStorageDirectory().toString() + File.separator + "test.txt")


}