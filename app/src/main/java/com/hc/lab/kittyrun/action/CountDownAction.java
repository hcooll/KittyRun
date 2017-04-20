package com.hc.lab.kittyrun.action;

/**
 * Created by congwiny on 2017/4/14.
 */

public class CountDownAction extends Action{

    public boolean isFirstGuide;

    public CountDownAction(boolean isFirstGuide) {
        this.type = Action.TYPE_COUNT_DOWN;
        this.isFirstGuide = isFirstGuide;
    }
}
