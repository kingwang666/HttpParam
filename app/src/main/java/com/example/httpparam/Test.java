package com.example.httpparam;


import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.wang.httpparam.*;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Author: wangxiaojie6
 * Date: 2019/3/25
 */
@Params(
        replace = true,
        type = ParamsType.STRING
)
@Parts
@Body
public class Test extends BaseTest {

    public boolean aBoolean = true;

    public byte aByte = 2;

    public char aChar ='w';

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
    public String name = "wang";

    public String[] girls;

    @PostFile
    public FileInput file;

    @PostFile
    @NonNull
    public FileInput file2 = new FileInput();

    @PostFiles
    public List<FileInput> files;

    @PostFiles
    @NonNull
    public List<FileInput> files2 = new ArrayList<>();

    @PostFiles
    public List<FileInput> files3;


}
