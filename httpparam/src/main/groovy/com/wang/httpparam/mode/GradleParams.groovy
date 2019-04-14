package com.wang.httpparam.mode

class GradleParams {

    def debug = false

    def add = false

    String fileClass

    Map<String, Object> file

    List<String> exclude = []


    @Override
    String toString() {
        return "debug= $debug, add= $add, fileClass= $fileClass, file= $file, exclude= $exclude"
    }
}