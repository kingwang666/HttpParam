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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
        file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.txt");
        files4[0] = new FileInput();
        files5.put("testMap", new FileInput());
    }


    @NonNull
    @Override
    public MultipartBody.Builder getBody() {
        MultipartBody.Builder builder = super.getBody();
        builder.addFormDataPart("aBoolean", String.valueOf(aBoolean));
        builder.addFormDataPart("aByte", String.valueOf(aByte));
        builder.addFormDataPart("aChar", String.valueOf(aChar));
        builder.addFormDataPart("aDouble", String.valueOf(aDouble));
        builder.addFormDataPart("aFloat", String.valueOf(aFloat));
        builder.addFormDataPart("aInt", String.valueOf(aInt));
        builder.addFormDataPart("aLong", String.valueOf(aLong));
        builder.addFormDataPart("aShort", String.valueOf(aShort));
        if (mTest != null) {
            builder.addFormDataPart("mTest", mTest.toString());
        }
        if (cc != null) {
            builder.addFormDataPart("cc", Arrays.toString(cc));
        }
        builder.addFormDataPart("xxxxx", name);
        if (girls != null) {
            builder.addFormDataPart("girls", Arrays.toString(girls));
        }
        if (file != null) {
            builder.addFormDataPart("123File", file.getName(), RequestBody.create(MediaType.parse(guessMimeType(file.getName())), file));
        }
        if (filein != null) {
            builder.addFormDataPart(filein.key, filein.filename, RequestBody.create(MediaType.parse(guessMimeType(filein.filename)), filein.data));
        }
        builder.addFormDataPart(file2.key, file2.filename, RequestBody.create(MediaType.parse(guessMimeType(file2.filename)), file2.data));
        if (files != null) {
            for (FileInput file : files) {
                builder.addFormDataPart("testCustomFiles", file.filename, RequestBody.create(MediaType.parse(guessMimeType(file.filename)), file.data));
            }
        }
        for (FileInput file : files2) {
            builder.addFormDataPart(file.key, file.filename, RequestBody.create(MediaType.parse(guessMimeType(file.filename)), file.data));
        }
        if (files3 != null) {
            for (FileInput file : files3) {
                builder.addFormDataPart(file.key, file.filename, RequestBody.create(MediaType.parse(guessMimeType(file.filename)), file.data));
            }
        }
        if (files4 != null) {
            for (FileInput file : files4) {
                builder.addFormDataPart(file.key, file.filename, RequestBody.create(MediaType.parse(guessMimeType(file.filename)), file.data));
            }
        }
        if (files5 != null) {
            for (Map.Entry<String, FileInput> entry : files5.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue().filename, RequestBody.create(MediaType.parse(guessMimeType(entry.getValue().filename)), entry.getValue().data));
            }
        }
        return builder;
    }
}
