package com.example.httpparam;


import androidx.annotation.NonNull;
import com.wang.httpparam.Ignore;
import com.wang.httpparam.Params;

import java.util.Map;

/**
 * Author: wangxiaojie6
 * Date: 2019/3/25
 */
@Params(replace = true)
public class Test extends BaseTest {

    public boolean test;

    private boolean mm;

    @Ignore
    public Boolean aa;
//
    public Test mTest;
//
    public int[] cc;
//
    @NonNull
    public String name = "";

    public Map<String, String> getParams() {
        Map<String, String> params = super.getParams();
        params.put("name", name);
        return params;
    }
}
