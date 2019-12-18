package com.example.httpparam;

import android.os.Environment;

import androidx.annotation.NonNull;

import com.wang.httpparam.Body;
import com.wang.httpparam.ParamName;
import com.wang.httpparam.Params;
import com.wang.httpparam.ParamsType;
import com.wang.httpparam.Parts;
import com.wang.httpparam.PostFile;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created on 2019/12/17
 * Author: bigwang
 * Description:
 */
@Params(
        type = ParamsType.BODY
)
@Body
@Parts
public class FileReq extends BaseReq {

    @NonNull
    public String name = "wang";

    @ParamName("fakeAge")
    public Integer age = 18;

    @PostFile
    public File file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.txt");


}
