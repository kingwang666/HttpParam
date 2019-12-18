package com.example.httpparam;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created on 2019/12/16
 * Author: bigwang
 * Description:
 */
class BaseReq {

    public String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }


    @NonNull
    public Map<String, RequestBody> getParams() {
        Map<String, RequestBody> params = new ArrayMap<>();
        return params;
    }


    @NonNull
    public List<MultipartBody.Part> getParts() {
        List<MultipartBody.Part> parts = new ArrayList<>();
        return parts;
    }


    @NonNull
    public MultipartBody.Builder getBody() {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        return builder;
    }
}
