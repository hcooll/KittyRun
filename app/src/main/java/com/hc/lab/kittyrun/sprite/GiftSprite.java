package com.hc.lab.kittyrun.sprite;

import android.graphics.Bitmap;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.action.GiftEnterAction;
import com.hc.lab.kittyrun.base.BaseSprite;
import com.hc.lab.kittyrun.constant.DataConstant;
import com.hc.lab.kittyrun.data.BounusData;
import com.hc.lab.kittyrun.model.GiftResMoel;
import com.hc.lab.kittyrun.strategy.GiftStrategy;
import com.hc.lab.kittyrun.util.SizeConvertUtils;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCFadeIn;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCScaleTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.util.CGPointUtil;

import static android.R.attr.key;

/**
 * Created by hc on 2017/4/13 0013.
 * 礼物
 */

public class GiftSprite extends ActionSprite {

    public boolean isOnCollision;
    public boolean isCombo;

    public GiftSprite(String image) {
        super(image);
        initGift();
    }

    public GiftSprite(Bitmap image, String key) {
        super(image, key);
        initGift();
    }

    private void initGift() {
        setAnchorPoint(0.5f, 0.5f);
    }

    @Override
    public void run(Action action) {
        super.run(action);
    }

    @Override
    public void onCollision() {

        if (!isOnCollision) {
            isCombo = true;
            //增加bounus的次数
            isOnCollision = true;
            super.onCollision();
            if (getAction() != null) {
                GiftStrategy giftStrategy = (GiftStrategy) getAction().getStrategy();
                if (giftStrategy != null) {
                    if (giftStrategy.getGiftResMoel() != null
                            && giftStrategy.getGiftResMoel().avatarBmp != null) {
                        CCTexture2D ccTexture2D = new CCTexture2D();
                        ccTexture2D.initWithImage(giftStrategy.getGiftResMoel().avatarBmp);
                        setTexture(ccTexture2D);
                        fadeScaleOut();
                    }

                }
            }
        }

    }

    public void moveXAndScale(int position, float delay, float giftBarWidth) {
        float t;
        CGPoint toPosition;
        CCMoveTo moveTo;

        switch (position) {
            case 0:
                float moveX1 = giftBarWidth * DataConstant.GIFT1_MOVE_X_PERCENT;
                float toX1 = screenSize.width - moveX1;
                t = toX1 / DataConstant.GIFT_MOVE_X_VELOCITY;
                toPosition = CGPoint.ccp(toX1, getPosition().y);
                moveTo = CCMoveTo.action(t, toPosition);
                this.runAction(CCSequence.actions(CCDelayTime.action(delay), moveTo, CCCallFunc.action(this, "repeatScale")));
                break;
            case 1:
                float moveX2 = giftBarWidth * DataConstant.GIFT2_MOVE_X_PERCENT;
                float toX2 = screenSize.width - moveX2;
                t = toX2 / DataConstant.GIFT_MOVE_X_VELOCITY;
                toPosition = CGPoint.ccp(toX2, getPosition().y);
                moveTo = CCMoveTo.action(t, toPosition);
                this.runAction(CCSequence.actions(CCDelayTime.action(delay), moveTo));
                break;
            case 2:
                float moveX3 = giftBarWidth * DataConstant.GIFT3_MOVE_X_PERCENT;
                float toX3 = screenSize.width - moveX3;
                t = toX3 / DataConstant.GIFT_MOVE_X_VELOCITY;
                toPosition = CGPoint.ccp(toX3, getPosition().y);
                moveTo = CCMoveTo.action(t, toPosition);
                this.runAction(CCSequence.actions(CCDelayTime.action(delay), moveTo));
                break;
        }
    }

    public void repeatScale() {
        CCScaleTo scaleToBig = CCScaleTo.action(0.3f, 1.2f);
        CCScaleTo scaleToNormal = CCScaleTo.action(0.3f, 1.0f);
        CCSequence scaleSequence = CCSequence.actions(scaleToBig, scaleToNormal);
        runAction(CCRepeatForever.action(scaleSequence));
    }

    private void scale2Big() {
        CCSequence scaleSequence = CCSequence.actions(CCFadeIn.action(0.5f), CCScaleTo.action(0.3f, 1.3f), CCCallFunc.action(this, "setScaleBig"));
        runAction(scaleSequence);
    }

    private void fadeScaleOut() {
        CCSequence scaleSequence = CCSequence.actions(CCScaleTo.action(0.3f, 1.5f), CCDelayTime.action(0.5f), CCScaleTo.action(0.3f, 0f), CCCallFunc.action(this, "dismiss"));
        CCSequence scaleSequence2 = CCSequence.actions(CCDelayTime.action(0.8f), CCFadeOut.action(0.3f));
        runAction(scaleSequence);
        runAction(scaleSequence2);
    }


    public void followLawnMove(CGPoint position, float lawnSpeed) {
        stopAllActions();
        scale2Big();
        setPosition(position);
        CGPoint end = CGPoint.make(-getContentSize().width / 2, position.y);
        float t = CGPointUtil.distance(position, end) / lawnSpeed;
        CCMoveTo ccMoveTo = CCMoveTo.action(t, end);
        CCSequence ccSequence = CCSequence.actions(ccMoveTo, CCCallFunc.action(this, "dismiss"));
        this.runAction(ccSequence);
    }

    public void setScaleBig() {
        setScale(1.3f);
    }


    public void dismiss() {
        isOnCollision = false;
        if (!((GiftStrategy) mAction.getStrategy()).getGiftModel().isGuideGift()) {
            this.removeSelf();
        }
    }

}
