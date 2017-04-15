package com.hc.lab.kittyrun.sprite;


import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.action.ActionRunnable;
import com.hc.lab.kittyrun.base.BaseLayer;
import com.hc.lab.kittyrun.screenplay.ScreenPlay;

import org.cocos2d.nodes.CCSprite;

/**
 * Created by congwiny on 2017/4/14.
 */

public class ActionSprite extends CCSprite implements ActionRunnable {
    protected ScreenPlay play;
    protected Action mAction;
    protected BaseLayer mLayer;

    public ActionSprite(ScreenPlay play) {
        super("fps_images.png");
        this.play = play;
    }

    @Override
    public void run(Action action) {
        play.onActionStart(action);
        mAction = action;
    }


    public BaseLayer getLayer() {
        return mLayer;
    }

    public void setLayer(BaseLayer layer) {
        this.mLayer = layer;
    }
}
