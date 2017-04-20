package com.hc.lab.kittyrun.sprite;

import com.hc.lab.kittyrun.util.CommonUtil;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.types.util.CGPointUtil;

import java.util.ArrayList;

/**
 * Created by congwiny on 2017/4/18.
 */

public class SmokeSprite extends ActionSprite {

    ArrayList<CCSpriteFrame> mSmokeframes;

    public SmokeSprite(String filepath) {
        super(filepath);
    }


    private void showSmoke() {
        setVisible(true);
        this.runAction(CCSequence.actions(
                CommonUtil.getAnimation(mSmokeframes, 0, 6, "image/smoke/smoke_000%01d.png", 0.08f),
                CCCallFunc.action(this, "hide")));
    }

    public void hide() {
        setVisible(false);
    }

    public void followShowSmoke(CGPoint position, float lawnSpeed) {
        stopAllActions();
        setPosition(position);
        showSmoke();
        CGPoint end = CGPoint.make(-getContentSize().width/2, position.y);
        float t = CGPointUtil.distance(position, end) / lawnSpeed;
        CCMoveTo ccMoveTo = CCMoveTo.action(t, end);
        this.runAction(ccMoveTo);
    }

}
