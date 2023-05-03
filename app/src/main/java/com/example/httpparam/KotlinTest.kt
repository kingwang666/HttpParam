package com.example.httpparam

import android.os.Environment
import androidx.collection.ArrayMap
import com.wang.httpparam.*
import java.io.File
import java.util.*

/**
 * Author: wang
 * Date: 2019/4/14
 */
@Params(type = ParamsType.BODY)
@Body
@Parts
open class KotlinTest : BaseTest() {

    companion object{
        const val bb = "123"
    }

    var aa = 1

    var str: String? = "123"

    @Ignore
    var cc = 2


    @PostFile(key = "123File")
    var file: File = File(FileHelper.URL)


    @PostFile
    var fileInput = FileInput()

    @PostFile
    var fileArray = arrayOf<File>(file)

    @PostFile
    var fileInputArray =
        arrayOf(fileInput)

    @PostFile
    var fileList: MutableList<File> = ArrayList()

    @PostFile
    var fileInputList: MutableList<FileInput> =
        ArrayList()

    @PostFile
    var fileMap: MutableMap<String, File> =
        ArrayMap()

    @PostFile
    var fileInputMap: MutableMap<String, FileInput> =
        ArrayMap()

    init {
        fileList.add(file)
        fileInputList.add(fileInput)
        fileMap["fileMap"] = file
        fileInputMap["fileInputMap"] = fileInput
    }


}