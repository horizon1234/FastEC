package com.unionman.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.unionman.latte.app.Latte;

/**
 * Created by zyh on 2017/11/8.
 */

public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
