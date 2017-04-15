package com.hc.lab.kittyrun.sprite;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.screenplay.ScreenPlay;
import com.hc.lab.kittyrun.util.CommonUtil;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.instant.CCHide;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCJumpTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.types.CGRect;

/**
 * Created by congwiny on 2017/4/14.
 */

public class KittySpirite extends ActionSprite {

    // 小精灵飞的高度等级
    public static final int FLY_DEGREE_LOW = 0x1;
    public static final int FLY_DEGREE_MEDIUM = 0x2;
    public static final int FLY_DEGREE_HIGH = 0x3;

    private boolean isWalking;
    private boolean isFlying;

    public KittySpirite(String filepath) {
        super(filepath);
    }

    public KittySpirite(String filepath, CGRect rect) {
        super(filepath, rect);
    }


    @Override
    public void run(Action action) {
        super.run(action);
        switch (action.type) {
            case Action.TYPE_WALK:
                walk();
                break;
            case Action.TYPE_JUMP:
                break;
        }
    }

    private void walk() {
        if (!isWalking) {
            isWalking = true;
            isFlying = false;
            this.stopAllActions();
            this.runAction(CommonUtil.getRepeatAnimation(null, 0, 5, "image/kitty/run000%01d.png", 0.15f));
            CCSequence ccSequence = CCSequence.actions(CCDelayTime.action(5), CCDelayTime.action(0.5F), CCCallFunc.action(this, "startJump"));
            this.runAction(ccSequence);
        }
    }

    private void jump() {
        if (!isFlying) {
            isWalking = false;
            isFlying = true;
            this.stopAllActions();
            CCJumpTo ccJumpTo = CCJumpTo.action(2.0f, getPosition(), 300f, 1);
            CCSequence ccSequence = CCSequence.actions(ccJumpTo, CCCallFunc.action(this, "endJump"));
            this.runAction(ccSequence);
            this.runAction(CommonUtil.getRepeatAnimation(null, 1, 3, "image/kitty/fly000%01d.png", 0.15f));
        }
    }

    public void startJump() {
        jump();
    }


    public void endJump() {
        this.stopAllActions();
        walk();
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
