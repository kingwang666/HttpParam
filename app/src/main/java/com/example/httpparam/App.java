package com.example.httpparam;

import android.app.Application;

/**
 * Created on 2022/4/22
 * Author: wangxiaojie
 * Description:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FileHelper.init(this);
    }
}
