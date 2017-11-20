package com.unionman.latte.util.timer;

import java.util.TimerTask;

/**
 * Created by zyh on 2017/11/16.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mTimerListener = null;

    public BaseTimerTask(ITimerListener timerListener){
        this.mTimerListener = timerListener;
    }

    @Override
    public void run() {
        if(mTimerListener != null){
            mTimerListener.onTimer();
        }
    }
}
