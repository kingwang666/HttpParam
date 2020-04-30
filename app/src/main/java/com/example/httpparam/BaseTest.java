package com.example.httpparam;

import androidx.annotation.NonNull;
import com.wang.httpparam.Body;
import com.wang.httpparam.Params;
import com.wang.httpparam.ParamsType;
import com.wang.httpparam.Parts;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: wangxiaojie6
 * Date: 2019/4/3
 */
@Params(
        replace = true,
        type = ParamsType.STRING
)
@Parts
@Body
public class BaseTest {

    @NonNull
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
        Map<String, RequestBody> params = new HashMap<>();
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
