package com.hc.lab.kittyrun.sprite;

import android.util.Log;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.constant.DataConstant;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.nodes.CCLabelAtlas;
import org.cocos2d.types.CGRect;
import org.cocos2d.utils.javolution.TextBuilder;

/**
 * Created by congwiny on 2017/4/15.
 */

public class MileSprite extends ActionSprite {

    public static final String TAG = MileSprite.class.getSimpleName();
    private CCLabelAtlas labelAtlas;
    private TextBuilder fpsBuilder = new TextBuilder();
    private long miles;

    public MileSprite(String filepath) {
        super(filepath);
        setAnchorPoint(0.0f, 1.0f);
        setPosition(screenSize.width / 4, screenSize.height / 8 * 7);
        setTextureRect(CGRect.zero());

        labelAtlas = new CCLabelAtlas("0123456789", "image/mile/mile_number.png",
                DataConstant.MILE_NUMBER_WIDTH, DataConstant.MILE_NUMBER_HEIGHT, '0');
        labelAtlas.setAnchorPoint(1.0f, 0.0f);
        labelAtlas.setPosition(0.0f, 0.0f);
        labelAtlas.setScale(0.5f);
        labelAtlas.setString("0");
        addChild(labelAtlas);
    }

    @Override
    public void run(Action action) {
        super.run(action);
        CCScheduler.sharedScheduler().schedule("setMile", this, 1.0f, false);
    }

    public void stopRunMile() {
        unschedule("setMile");
    }

    public long getMiles() {
        return miles;
    }

    public void setMile(float t) {
        Log.d(TAG, "setMile t: " + t);
        miles += DataConstant.MILE_PER_SECEND;
        fpsBuilder.reset();
        fpsBuilder.append(miles);
        labelAtlas.setString(fpsBuilder);
    }
}
