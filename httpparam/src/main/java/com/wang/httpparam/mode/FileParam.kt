package com.wang.httpparam.mode

import java.io.Serializable

class FileParam(file: Map<String, Any>?) : Serializable {

    @JvmField
    var key = "key"

    @JvmField
    var filename = "filename"

    @JvmField
    var mimeType: String? = null

    @JvmField
    var data = "data"

    fun haveMimeType(): Boolean {
        return mimeType?.length ?: 0 > 0
    }

    override fun toString(): String {
        return "{key= $key, filename= $filename, mimeType= $mimeType, data= $data}"
    }

    init {
        if (file != null) {
            key = (file["key"] ?: "key") as String
            filename = (file["filename"] ?: "filename") as String
            mimeType = file["mimeType"] as String?
            data = (file["data"] ?: "data") as String
        }
    }
}