package com.hc.lab.kittyrun.sprite;

import android.graphics.Bitmap;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.base.BaseSprite;
import com.hc.lab.kittyrun.constant.DataConstant;
import com.hc.lab.kittyrun.model.GiftResMoel;
import com.hc.lab.kittyrun.strategy.GiftStrategy;
import com.hc.lab.kittyrun.util.SizeConvertUtils;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCScaleTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.types.CGPoint;

/**
 * Created by hc on 2017/4/13 0013.
 * 礼物
 */

public class GiftSprite extends ActionSprite {

    public GiftSprite(Bitmap image, String key) {
        super(image, key);
    }


    @Override
    public void run(Action action) {
        super.run(action);
    }

    @Override
    public void onCollision() {
        super.onCollision();
    }

    public void moveXAndScale(int position, float delay) {
        float t;
        CGPoint toPosition;
        CCMoveTo moveTo;

        switch (position) {
            case 0:
                float toX1 = SizeConvertUtils.getConvertWidth(DataConstant.ORIGIN_GIFT1_MOVE_X);
                t = toX1 / DataConstant.GIFT_MOVE_X_VELOCITY;
                toPosition = CGPoint.ccp(toX1, getPosition().y);
                moveTo = CCMoveTo.action(t, toPosition);
                this.runAction(CCSequence.actions(CCDelayTime.action(delay), moveTo, CCCallFunc.action(this, "repeatScale")));
                break;
            case 1:
                float toX2 = SizeConvertUtils.getConvertWidth(DataConstant.ORIGIN_GIFT2_MOVE_X);
                t = toX2 / DataConstant.GIFT_MOVE_X_VELOCITY;
                toPosition = CGPoint.ccp(toX2, getPosition().y);
                moveTo = CCMoveTo.action(t, toPosition);
                this.runAction(CCSequence.actions(CCDelayTime.action(delay), moveTo, CCCallFunc.action(this, "repeatScale")));
                break;
            case 2:
                float toX3 = SizeConvertUtils.getConvertWidth(DataConstant.ORIGIN_GIFT3_MOVE_X);
                t = toX3 / DataConstant.GIFT_MOVE_X_VELOCITY;
                toPosition = CGPoint.ccp(toX3, getPosition().y);
                moveTo = CCMoveTo.action(t, toPosition);
                this.runAction(CCSequence.actions(CCDelayTime.action(delay), moveTo, CCCallFunc.action(this, "repeatScale")));
                break;
        }
    }

    public void repeatScale() {
        CCScaleTo scaleToBig = CCScaleTo.action(0.3f, 1.5f);
        CCScaleTo scaleToNormal = CCScaleTo.action(0.3f, 1.0f);
        CCSequence scaleSequence = CCSequence.actions(scaleToBig, scaleToNormal);
        runAction(CCRepeatForever.action(scaleSequence));
    }
}
