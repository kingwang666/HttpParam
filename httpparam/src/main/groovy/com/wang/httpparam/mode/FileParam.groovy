package com.wang.httpparam.mode

class FileParam {

    String key = "key"

    String filename = "filename"

    String mimeType = "mimeType"

    String data = "data"

    FileParam(Map<String, Object> file) {
        if (file != null){
            key = file.get("key", "key")
            filename = file.get("filename", "filename")
            mimeType = file.get("mimeType", "mimeType")
            data = file.get("data", "data")
        }
    }


    @Override
    String toString() {
        return "{key= $key, filename= $filename, mimeType= $mimeType, data= $data}"
    }
}