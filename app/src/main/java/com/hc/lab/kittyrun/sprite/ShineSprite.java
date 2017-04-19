package com.hc.lab.kittyrun.sprite;

import android.util.Log;

import com.hc.lab.kittyrun.util.CommonUtil;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.util.CGPointUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by congwiny on 2017/4/19.
 */

public class ShineSprite extends ActionSprite {
    private ArrayList<CCSpriteFrame> mShineframes;
    public boolean isShineing;

    public ShineSprite(String filepath) {
        super(filepath);
    }

    @Override
    public void onCollision() {
        super.onCollision();
        setVisible(true);
        this.runAction(CCSequence.actions(
                CommonUtil.getAnimation(mShineframes, 0, 15, "image/shine/shine_00%02d.png", 0.02f),
                CCCallFunc.action(this, "hide")));
    }

    public void hide() {
        setVisible(false);
    }

    public void followOnCollision(CGPoint position, float lawnSpeed) {
        if (!isShineing) {
            Log.e("xxx","followOnCollision");
            isShineing = true;
            stopAllActions();
            setPosition(position);
            onCollision();
            CGPoint end = CGPoint.make(-getContentSize().width, position.y);
            float t = CGPointUtil.distance(position, end) / lawnSpeed;
            CCMoveTo ccMoveTo = CCMoveTo.action(t, end);

            CCSequence ccSequence = CCSequence.actions(ccMoveTo, CCCallFunc.action(this, "dismiss"));
            this.runAction(ccSequence);
        }
    }


    public void dismiss() {
        isShineing = false;
    }

}
