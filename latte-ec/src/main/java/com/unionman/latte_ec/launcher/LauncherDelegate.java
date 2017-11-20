package com.unionman.latte_ec.launcher;

import android.icu.text.MessageFormat;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.unionman.latte.delegates.LatteDelegate;
import com.unionman.latte.ui.launcher.ScrollLauncherTag;
import com.unionman.latte.util.storage.LattePreference;
import com.unionman.latte.util.timer.BaseTimerTask;
import com.unionman.latte.util.timer.ITimerListener;
import com.unionman.latte_ec.R;
import com.unionman.latte_ec.R2;


import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zyh on 2017/11/16.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener{

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer(){
        mTimer= new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void checkIsShowScroll(){
        if(!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else{
            //检查登录
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mTvTimer != null){
                    //mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mTvTimer.setText("跳过\n"+mCount+"s");
                    mCount --;
                    if(mCount <0 )
                        if(mTimer != null){
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                }
            }
        });
    }
}
