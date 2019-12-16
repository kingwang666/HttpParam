package com.example.httpparam;


import androidx.annotation.NonNull;

import com.wang.httpparam.PostFile;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Test2 extends BaseReq {

    @PostFile(key = "testFile")
    @NonNull
    File[] files;

    @PostFile()
    File[] files2;

    @PostFile(key = "testFileInput")
    @NonNull
    FileInput[] filesInput;

    @PostFile()
    FileInput[] filesInput2;

    @PostFile(key = "testFileList")
    @NonNull
    List<File> filesList;

    @PostFile()
    List<File> filesList2;

    @PostFile(key = "testFileInputList")
    @NonNull
    List<FileInput> filesInputList;

    @PostFile()
    List<FileInput> filesInputList2;

    @PostFile(key = "testFileMap")
    @NonNull
    Map<String, File> filesMap;

    @PostFile()
    Map<String, File> filesMap2;

    @PostFile(key = "testFileInputMap")
    @NonNull
    Map<String, FileInput> filesInputMap;

    @PostFile()
    Map<String, FileInput> filesInputMap2;

}
