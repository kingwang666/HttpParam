package com.example.httpparam;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: wangxiaojie6
 * Date: 2019/4/3
 */
public class BaseTest {

    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("wang", "xiaojie");
        return params;
    }
}
