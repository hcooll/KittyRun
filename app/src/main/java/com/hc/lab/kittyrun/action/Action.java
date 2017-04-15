package com.hc.lab.kittyrun.action;

import com.hc.lab.kittyrun.strategy.Strategy;

/**
 * Created by congwiny on 2017/4/14.
 */

public class Action {
    public int type;

    public static final int TYPE_WALK = 1;
    public static final int TYPE_JUMP = 2;
    public static final int TYPE_COUNT_DOWN = 3;
    public static final int TYPE_MOVE = 4;
    public static final int TYPE_GUIDE = 5;

    private Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
