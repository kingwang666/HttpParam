package com.example.httpparam

import android.content.Context
import java.io.File

/**
 * Created on 2022/4/22
 * Author: wangxiaojie
 * Description:
 */
object FileHelper {

    @JvmField
    var URL: String = ""

    @JvmStatic
    fun init(context: Context) {
        val file = File(context.externalCacheDir, "test.txt")
        file.writeText("the process id ${android.os.Process.myPid()}")
        URL = file.absolutePath
    }


}