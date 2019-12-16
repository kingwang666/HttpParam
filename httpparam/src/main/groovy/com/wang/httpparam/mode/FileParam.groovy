package com.wang.httpparam.mode

class FileParam {

    String key = "key"

    String filename = "filename"

    String mimeType = null

    String data = "data"

    FileParam(Map<String, Object> file) {
        if (file != null){
            key = file.get("key", "key")
            filename = file.get("filename", "filename")
            mimeType = file.get("mimeType")
            data = file.get("data", "data")
        }
    }

    boolean haveMimeType(){
        return mimeType != null && mimeType.length() > 0
    }


    @Override
    String toString() {
        return "{key= $key, filename= $filename, mimeType= $mimeType, data= $data}"
    }
}