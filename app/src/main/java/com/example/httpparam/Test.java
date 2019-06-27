package com.example.httpparam;


import androidx.annotation.NonNull;
import com.wang.httpparam.Ignore;
import com.wang.httpparam.Params;

import java.util.Arrays;
import java.util.Map;

/**
 * Author: wangxiaojie6
 * Date: 2019/3/25
 */
@Params(replace = true)
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

    @NonNull
    public Boolean aa;
//
    public Test mTest;
//
    public int[] cc;
//
    @NonNull
    public String name = "";


    @NonNull

    public Map<String, String> getParams() {
        Map<String, String> params = super.getParams();
        params.put("aBoolean", String.valueOf(aBoolean));
        params.put("aByte", String.valueOf(aByte));
        params.put("aChar", String.valueOf(aChar));
        params.put("aDouble", String.valueOf(aDouble));
        params.put("aFloat", String.valueOf(aFloat));
        params.put("aInt", String.valueOf(aInt));
        params.put("aLong", String.valueOf(aLong));
        params.put("aShort", String.valueOf(aShort));
//        params.put("aa", aa.toString());
//        params.put("mTest", mTest == null ? "" : mTest.toString());
//        params.put("cc", cc == null ? "" : Arrays.toString(cc));
//        params.put("name", name);
        return params;
    }
}
