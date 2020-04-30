package com.wang.httpparam.mode

class GradleParams {

    def debug = false

    def add = false

    def androidx = true

    def okHttpV4 = true

    Map<String, Object> file

    List<String> exclude = []



    @Override
    String toString() {
        return "debug= $debug, add= $add, androidx= $androidx, okHttpV4= $okHttpV4, file= $file, exclude= $exclude"
    }
}