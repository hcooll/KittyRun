package com.hc.lab.kittyrun.sprite;

import com.hc.lab.kittyrun.base.BaseSprite;
import com.hc.lab.kittyrun.util.CommonUtil;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCBezierBy;
import org.cocos2d.actions.interval.CCBezierTo;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCJumpTo;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CCBezierConfig;
import org.cocos2d.types.CGPoint;

import java.util.ArrayList;

/**
 * Created by hc on 2017/4/13 0013.
 * <p>
 * Kitty精灵
 */

public class KittySprite extends BaseSprite {

    // 小精灵飞的高度等级
    public static final int FLY_DEGREE_LOW = 0x1;
    public static final int FLY_DEGREE_MEDIUM = 0x2;
    public static final int FLY_DEGREE_HIGH = 0x3;

    private static ArrayList<CCSpriteFrame> runFrames;  // 跑动
    private static ArrayList<CCSpriteFrame> flyFrames;  // 飞

    private boolean isRun;

    public KittySprite() {
        super("image/kitty/run0000.png");
        run();
    }


    public void run() {
        this.runAction(CommonUtil.getRepeatAnimation(runFrames, 0, 5, "image/kitty/run000%01d.png", 0.15f));
    }

    public void fly(int degree) {
        if (isRun) {
            return;
        }
        isRun = true;
        this.stopAllActions();
//        CCBezierConfig ccBezierConfig = new CCBezierConfig();
//        ccBezierConfig.controlPoint_1 = this.getPosition();
//        ccBezierConfig.controlPoint_2 = CGPoint.make(this.getPosition().x, getFlyHigh(degree) + this.getPosition().y);
//        ccBezierConfig.endPosition = this.getPosition();
//        CCBezierTo ccBezierTo = CCBezierTo.action(FLY_TIME, ccBezierConfig);
//        CCMoveTo ccMoveTo = CCMoveTo.action(FLY_TIME,CGPoint.make(this.getPosition().x,getFlyHigh(degree) + this.getPosition().y));
        CCJumpTo ccJumpTo = CCJumpTo.action(getFlyTime(degree), getPosition(), getFlyHigh(degree), 1);
        CCSequence ccSequence = CCSequence.actions(ccJumpTo, CCCallFunc.action(this, "endFly"));
        this.runAction(ccSequence);

        this.runAction(CommonUtil.getRepeatAnimation(flyFrames, 1, 3, "image/kitty/fly000%01d.png", 0.15f));
    }

    public void endFly() {
        isRun = false;
        this.stopAllActions();
        run();
    }

    private int getFlyHigh(int degree) {
        switch (degree) {
            case FLY_DEGREE_LOW:
                return 100;
            case FLY_DEGREE_MEDIUM:
                return 200;
            case FLY_DEGREE_HIGH:
                return 300;
        }
        return 0;
    }

    private float getFlyTime(int degree) {
        switch (degree) {
            case FLY_DEGREE_LOW:
                return 1.0f;
            case FLY_DEGREE_MEDIUM:
                return 1.5f;
            case FLY_DEGREE_HIGH:
                return 2.0f;
        }
        return 0;
    }

}
