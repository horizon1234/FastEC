package com.unionman.fastec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.unionman.latte.app.Latte;
import com.unionman.latte.net.interceptors.DebugInterceptor;
import com.unionman.latte_ec.icon.FontEcModule;


/**
 * Created by zyh on 2017/11/1.
 */

public class ExampleApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.text))
                .configure();

    }
}
