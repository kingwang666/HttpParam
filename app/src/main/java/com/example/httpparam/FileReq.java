package com.example.httpparam;

import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;

import com.wang.httpparam.Body;
import com.wang.httpparam.ParamName;
import com.wang.httpparam.Params;
import com.wang.httpparam.ParamsType;
import com.wang.httpparam.Parts;
import com.wang.httpparam.PostFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 2019/12/17
 * Author: bigwang
 * Description:
 */
@Params(
        type = ParamsType.BODY
)
@Body
@Parts
public class FileReq extends BaseReq {

    @NonNull
    public String name = "wang";

    @ParamName("fakeAge")
    public Integer age = 18;

    @PostFile
    public File file = new File(FileHelper.URL);

    @PostFile
    public FileInput fileInput = new FileInput();

    @PostFile
    public File[] fileArray = new File[]{file};

    @PostFile
    public FileInput[] fileInputArray = new FileInput[]{fileInput};

    @PostFile
    public List<File> fileList = new ArrayList<>();

    @PostFile
    public List<FileInput> fileInputList = new ArrayList<>();

    @PostFile
    public Map<String, File> fileMap = new ArrayMap<>();

    @PostFile
    public Map<String, FileInput> fileInputMap = new ArrayMap<>();

    public FileReq() {
        fileList.add(file);
        fileInputList.add(fileInput);
        fileMap.put("fileMap", file);
        fileInputMap.put("fileInputMap", fileInput);
    }

}
