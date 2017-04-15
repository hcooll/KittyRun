package com.hc.lab.kittyrun.sprite;


import android.graphics.Bitmap;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.action.ActionRunnable;
import com.hc.lab.kittyrun.action.ActionStatusListener;
import com.hc.lab.kittyrun.base.BaseLayer;
import com.hc.lab.kittyrun.screenplay.ScreenPlay;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

/**
 * Created by congwiny on 2017/4/14.
 */

public class ActionSprite extends CCSprite implements ActionRunnable {
    protected ActionStatusListener mActionListener;
    protected Action mAction;
    protected BaseLayer mLayer;

    public ActionSprite(String filepath) {
        super(filepath);
    }

    public ActionSprite(String filepath, CGRect rect) {
        super(filepath, rect);
    }

    public ActionSprite(Bitmap image, String key) {
        super(image, key);
    }

    public ActionSprite(CCTexture2D texture) {
        super(texture);
    }

    public ActionSprite(CCTexture2D texture, CGRect rect) {
        super(texture, rect);
    }

    @Override
    public void run(Action action) {
        mAction = action;
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
}
