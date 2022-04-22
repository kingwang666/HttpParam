package com.example.httpparam;


import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;

import com.wang.httpparam.Body;
import com.wang.httpparam.Ignore;
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
 * Author: wangxiaojie6
 * Date: 2019/3/25
 */
@Params(
        replace = true,
        type = ParamsType.BODY
)
@Parts
@Body
public class Test extends BaseTest {

    public boolean aBoolean = true;

    public byte aByte = 2;

    public char aChar = 'w';

    public double aDouble = 1.1;

    public float aFloat = 2.2f;

    public int aInt = 3;

    public long aLong = 0xff;

    public short aShort = 1;

    @Ignore
    private boolean mm;
    //
    public Test mTest;
    //
    public int[] cc;
    //
    @NonNull
    @ParamName("xxxxx")
    public String name = "wang";

    public String[] girls;

    @PostFile(key = "123File")
    public File file;

    @PostFile
    public FileInput filein;

    @PostFile
    @NonNull
    public FileInput file2 = new FileInput();

    @PostFile(key = "testCustomFiles")
    public List<FileInput> files = new ArrayList<>();

    @PostFile
    @NonNull
    public List<FileInput> files2 = new ArrayList<>();

    @PostFile
    public List<FileInput> files3;

    @PostFile
    public FileInput[] files4 = new FileInput[1];

    @PostFile
    public Map<String, FileInput> files5 = new ArrayMap<>();


    public Test() {
        file = new File(FileHelper.URL);
        files4[0] = new FileInput();
        files5.put("testMap", new FileInput());
    }


}
