package com.unionman.latte.app;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by zyh on 2017/11/1.
 */

public final class  Latte {

    public static Configurator init(Context context){
        //getConfiguration().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static HashMap<String,Object> getConfiguration(){
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplication(){
        return (Context) getConfiguration().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
