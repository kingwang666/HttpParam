package com.example.httpparam;

import java.io.File;

/**
 * Created on 2019/6/28.
 * Author: bigwang
 * Description:
 */
public class FileInput {

    @Key
    public String key = "key";

    @Filename
    public String filename = "file name";

    @MimeType
    public String mimeType = "mime type";

    @Data
    public File data = new File("123");

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
