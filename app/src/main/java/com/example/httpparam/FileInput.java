package com.example.httpparam;

import android.os.Environment;

import java.io.File;

/**
 * Created on 2019/6/28.
 * Author: bigwang
 * Description:
 */
public class FileInput {


    public String key = "key";

    public String filename = "file name";


    public String mimeType = "mime type";


    @Data
    public File data = new File(Environment.getExternalStorageDirectory() + File.separator + "test.txt");

    @Override
    public String toString() {
        return "FileInput{" +
                "key='" + key + '\'' +
                ", filename='" + filename + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", data=" + data +
                '}';
    }
}
