package com.example.administrator.zxing;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Administrator on 2017/7/25.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        ZXingLibrary.initDisplayOpinion(this);
    }
}
