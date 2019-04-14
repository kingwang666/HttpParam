package com.wang.httpparam.mode

class FileParam {

    String key = "key"

    String filename = "filename"

    String miniType = "miniType"

    String data = "data"

    FileParam(Map<String, Object> file) {
        if (file != null){
            key = file.get("key", "key")
            filename = file.get("filename", "filename")
            miniType = file.get("miniType", "miniType")
            data = file.get("data", "data")
        }
    }


    @Override
    String toString() {
        return "{key= $key, filename= $filename, miniType= $miniType, data= $data}"
    }
}