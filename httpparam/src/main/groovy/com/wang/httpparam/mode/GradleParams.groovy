package com.wang.httpparam.mode

class GradleParams {

    def debug = false

    def add = false

    def androidx = true

    Map<String, Object> file

    List<String> exclude = []



    @Override
    String toString() {
        return "debug= $debug, add= $add, androidx= $androidx, file= $file, exclude= $exclude"
    }
}