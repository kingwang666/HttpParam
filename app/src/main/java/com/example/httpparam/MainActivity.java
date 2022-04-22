package com.example.httpparam;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.Buffer;

/**
 * Created on 2019/12/16
 * Author: bigwang
 * Description:
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        findViewById(R.id.fab).setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            Buffer buffer = new Buffer();
//            FileReq test = new FileReq();
            Test test = new Test();
            StringBuilder builder = new StringBuilder();
            Log.d("test", "log params start");


            Map<String, RequestBody> params = test.getParams();
            Iterator<Map.Entry<String,RequestBody>> i = params.entrySet().iterator();
            StringBuilder sb = new StringBuilder();
            sb.append(" \n");
            while (i.hasNext()) {
                Map.Entry<String, RequestBody> e = i.next();
                String key = e.getKey();
                Object value = e.getValue();
                sb.append(key);
                sb.append(" = ");
                sb.append(value);
                sb.append("  (");
                sb.append(value.getClass());
                sb.append(")\n\n");
            }
            Log.d("test", sb.toString());
            Log.d("test", "log params end");
            Log.d("test", "log parts start");

            try {
                builder.append(" \n");
                for (MultipartBody.Part part : test.getParts()) {
                    builder.append(part.headers());
                    buffer.clear();
                    part.body().writeTo(buffer);
                    builder.append(buffer.readUtf8());
                    builder.append("\n\n");
                }

                Log.d("test", builder.toString());
                Log.d("test", "log parts end");
                Log.d("test", "log body start");
                buffer.clear();
                test.getBody().build().writeTo(buffer);
                Log.d("test", " \n" +  buffer.readUtf8());
                Log.d("test", "log body end");


                Log.d("test", "===================================");

                KotlinTest kotlinTest = new KotlinTest();

                Log.d("test", "log kotlin params start");
                params = kotlinTest.getParams();
                i = params.entrySet().iterator();
                sb = new StringBuilder();
                sb.append(" \n");
                while (i.hasNext()) {
                    Map.Entry<String, RequestBody> e = i.next();
                    String key = e.getKey();
                    Object value = e.getValue();
                    sb.append(key);
                    sb.append(" = ");
                    sb.append(value);
                    sb.append("  (");
                    sb.append(value.getClass());
                    sb.append(")\n\n");
                }
                Log.d("test", sb.toString());
                Log.d("test", "log kotlin params end");
                Log.d("test", "log kotlin parts start");
                builder.append(" \n");
                for (MultipartBody.Part part : kotlinTest.getParts()) {
                    builder.append(part.headers());
                    buffer.clear();
                    part.body().writeTo(buffer);
                    builder.append(buffer.readUtf8());
                    builder.append("\n\n");
                }
                Log.d("test", builder.toString());
                Log.d("test", "log kotlin parts end");

                Log.d("test", "log kotlin body start");
                buffer.clear();
                kotlinTest.getBody().build().writeTo(buffer);
                Log.d("test", " \n" + buffer.readUtf8());
                Log.d("test", "log kotlin body end");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
