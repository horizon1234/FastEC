package com.unionman.latte_ec.icon;

import android.graphics.drawable.Icon;

/**
 * Created by zyh on 2017/11/3.
 */

public enum  EcIcons implements com.joanzapata.iconify.Icon{
    icon_scan('\ue679'),
    icon_ali_pay('\ue629');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }


    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
