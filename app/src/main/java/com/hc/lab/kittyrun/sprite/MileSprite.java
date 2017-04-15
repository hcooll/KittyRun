package com.hc.lab.kittyrun.sprite;

import android.util.Log;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.screenplay.ScreenPlay;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.nodes.CCLabelAtlas;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.utils.javolution.TextBuilder;

/**
 * Created by congwiny on 2017/4/15.
 */

public class MileSprite extends ActionSprite {
    public static final int NUMBER_WIDTH = 38;
    public static final int NUMBER_HEIGHT = 46;
    public static final String TAG = MileSprite.class.getSimpleName();
    private CCLabelAtlas labelAtlas;
    private TextBuilder fpsBuilder = new TextBuilder();
    private long miles;

    public MileSprite(ScreenPlay play) {
        super(play);
    }

    @Override
    public void run(Action action) {
        super.run(action);
        if (labelAtlas == null) {
            labelAtlas = new CCLabelAtlas("0123456789",
                    "image/number/number_mile.png",
                    NUMBER_WIDTH, NUMBER_HEIGHT, '0');
            labelAtlas.setScale(0.5f);
            addChild(labelAtlas);

            //Log.e(TAG,"rect.."+labelAtlas.getTexture();
            CGRect rect = getTextureRect();
            Log.e(TAG, "rect.." + rect.toString());
        }

        CCScheduler.sharedScheduler().schedule("startRunMile", this, 0.05f,
                false);
    }

    public void startRunMile(float t) {
        fpsBuilder.reset();
        fpsBuilder.append(miles);
        labelAtlas.setString(fpsBuilder);
        miles++;
    }
}
