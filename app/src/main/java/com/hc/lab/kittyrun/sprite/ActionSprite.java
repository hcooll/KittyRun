package com.hc.lab.kittyrun.sprite;


import android.graphics.Bitmap;
import android.util.Log;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.action.ActionRunnable;
import com.hc.lab.kittyrun.listener.ActionStatusListener;
import com.hc.lab.kittyrun.base.BaseLayer;
import com.hc.lab.kittyrun.monitor.Collision;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

/**
 * Created by congwiny on 2017/4/14.
 */

public class ActionSprite extends CCSprite implements ActionRunnable, Collision {
    protected ActionStatusListener mActionListener;
    protected Action mAction;
    protected BaseLayer mLayer;
    protected CGSize screenSize;

    public ActionSprite(String filepath) {
        super(filepath);
        initSprite();
    }

    public ActionSprite(String filepath, CGRect rect) {
        super(filepath, rect);
        initSprite();
    }

    public ActionSprite(Bitmap image, String key) {
        super(image, key);
        initSprite();
    }

    public ActionSprite(CCTexture2D texture) {
        super(texture);
        initSprite();
    }

    public ActionSprite(CCTexture2D texture, CGRect rect) {
        super(texture, rect);
        initSprite();
    }

    private void initSprite() {
        screenSize = CCDirector.sharedDirector().screenSize();
    }

    @Override
    public void run(Action action) {
        if (mActionListener != null) {
            mActionListener.onActionStart(action);
        }
    }


    public BaseLayer getLayer() {
        return mLayer;
    }

    public void setLayer(BaseLayer layer) {
        this.mLayer = layer;
    }

    public ActionStatusListener getActionStatusListener() {
        return mActionListener;
    }

    public void setActionStatusListener(ActionStatusListener actionListener) {
        this.mActionListener = actionListener;
    }

    public Action getAction() {
        return mAction;
    }

    public void setAction(Action action) {
        this.mAction = action;
    }

    //当碰撞的时候调用
    @Override
    public void onCollision() {

    }

    @Override
    public void onExit() {
        super.onExit();
        Log.e("Action sprite", "onExit:" + this);
        if (mActionListener != null) {
            mActionListener.onActionStop(mAction);
        }
    }
}
