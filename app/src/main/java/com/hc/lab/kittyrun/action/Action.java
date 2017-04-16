package com.hc.lab.kittyrun.action;

import android.util.Log;

import com.hc.lab.kittyrun.strategy.Strategy;

/**
 * Created by congwiny on 2017/4/14.
 */

public class Action {
    public int type;

    public static final int TYPE_KITTY_WALK = 1;
    public static final int TYPE_KITTY_JUMP = 2;
    public static final int TYPE_COUNT_DOWN = 3;
    public static final int TYPE_LAWN_MOVE = 4;
    public static final int TYPE_GUIDE = 5;

    private Strategy mStrategy;

    public Strategy getStrategy() {
        return mStrategy;
    }

    public void setStrategy(Strategy strategy) {
        this.mStrategy = strategy;
    }
}
