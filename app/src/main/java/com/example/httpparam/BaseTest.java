package com.example.httpparam;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.wang.httpparam.Params;

import java.util.Map;

/**
 * Author: wangxiaojie6
 * Date: 2019/4/3
 */
@Params(replace = true)
public class BaseTest {


    @NonNull
    public Map<String, String> getParams() {
        Map<String, String> params = new ArrayMap<>();
        params.put("wang", "xiaojie");
        return params;
    }
}
