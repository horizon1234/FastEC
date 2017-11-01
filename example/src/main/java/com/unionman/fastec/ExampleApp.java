package com.unionman.fastec;

import android.app.Application;

import com.unionman.latte.app.Latte;


/**
 * Created by zyh on 2017/11/1.
 */

public class ExampleApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1")
                .configure();

    }
}
