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
public class Test extends BaseTest {

    public boolean aBoolean;

    public byte aByte;

    public char aChar;

    public double aDouble;

    public float aFloat;

    public int aInt;

    public long aLong;

    public short aShort;

    @Ignore
    private boolean mm;
    //
    public Test mTest;
    //
    public int[] cc;
    //
    @NonNull
    public String name = "";

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

    @NonNull
    public List<MultipartBody.Part> getParts() {
        List<MultipartBody.Part> parts = super.getParts();
        if (cc != null) {
            parts.add(MultipartBody.Part.createFormData("cc", Arrays.toString(cc)));
        }

        return parts;
    }


    @NonNull
    public Map<String, String> getParams() {
        Map<String, String> params = new ArrayMap<>();
//        params.put("cc", cc == null ? "" : Arrays.toString(cc));
        if (mTest != null) {
            params.put("mTest", mTest.toString());
        }


        return params;
    }


    @NonNull
    public MultipartBody.Builder getBody() {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("aLong", String.valueOf(aLong));
        builder.addFormDataPart("aShort", String.valueOf(aShort));
        if (mTest != null) {
            builder.addFormDataPart("mTest", mTest.toString());
        }
        builder.addFormDataPart("cc", cc == null ? "" : Arrays.toString(cc));
        builder.addFormDataPart("name", name);
        if (girls != null) {
            builder.addFormDataPart("girls", Arrays.toString(girls));
        }
        if (file != null) {
            builder.addFormDataPart(file.key, file.filename, RequestBody.create(MediaType.parse(file.mimeType), file.data));
        }
        builder.addFormDataPart(file2.key, file2.filename, RequestBody.create(MediaType.parse(file2.mimeType), file2.data));
        if (files != null) {
            for (FileInput file : files) {
                builder.addFormDataPart(file.key, file.filename, RequestBody.create(MediaType.parse(file.mimeType), file.data));
            }
        }
        for (FileInput file : files2) {
            builder.addFormDataPart(file.key, file.filename, RequestBody.create(MediaType.parse(file.mimeType), file.data));
        }
        if (files3 != null) {
            for (FileInput file : files3) {
                builder.addFormDataPart(file.key, file.filename, RequestBody.create(MediaType.parse(file.mimeType), file.data));
            }
        }
        return builder;
    }
}
