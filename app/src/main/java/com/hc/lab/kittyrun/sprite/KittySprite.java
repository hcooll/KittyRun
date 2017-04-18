package com.hc.lab.kittyrun.sprite;

import android.util.Log;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.strategy.KittyJumpStrategy;
import com.hc.lab.kittyrun.util.CommonUtil;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCBezierTo;
import org.cocos2d.actions.interval.CCJumpTo;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.types.CCBezierConfig;
import org.cocos2d.types.CGPoint;

/**
 * Created by congwiny on 2017/4/14.
 */

public class KittySprite extends ActionSprite {

    private boolean isWalking;
    private boolean isFlying;
    private boolean isDead;
    private float prePoxY;

    public KittySprite(String filepath) {
        super(filepath);
        setAnchorPoint(0, 0);
        setScale(0.4f);
    }

    @Override
    public void run(Action action) {
        super.run(action);
        if (isDead) {
            return;
        }
        switch (action.type) {
            case Action.TYPE_KITTY_WALK:
                walk();
                break;
            case Action.TYPE_KITTY_JUMP:
                KittyJumpStrategy strategy = (KittyJumpStrategy) action.getStrategy();
                startJump(strategy);
                break;
        }
    }

    private void walk() {
        if (!isWalking) {
            isWalking = true;
            isFlying = false;
            prePoxY = position_.y;
            this.stopAllActions();
            this.runAction(CommonUtil.getRepeatAnimation(null, 0, 5, "image/kitty/run000%01d.png", 0.15f));
        }
    }

    private void startJump(KittyJumpStrategy strategy) {
        if (!isFlying) {
            isWalking = false;
            isFlying = true;
            this.stopAllActions();
            float duration = strategy.duration;
            CCJumpTo ccJumpTo = CCJumpTo.action(duration, strategy.toPosition, strategy.jumpHeight, 1);
            CCSequence ccSequence = CCSequence.actions(ccJumpTo, CCCallFunc.action(this, "endJump"));
            this.runAction(ccSequence);
            this.runAction(CommonUtil.getAnimation(null, 1, 3, "image/kitty/fly000%01d.png", duration / 4));
        }
    }

    public void endJump() {
        this.stopAllActions();
        walk();
    }

    public void fallDown() {
        isWalking = false;
        isFlying = false;
        isDead = true;
        this.stopAllActions();
        setTexture(CCTextureCache.sharedTextureCache().addImage("image/kitty/fly0003.png"));
        CCMoveTo ccMoveTo = CCMoveTo.action(1.0f, CGPoint.ccp(position_.x, 0));
        CCSequence ccSequence = CCSequence.actions(ccMoveTo, CCCallFunc.action(this, "gameOver"));
        this.runAction(ccSequence);
    }

    public void gameOver() {
        this.stopAllActions();
    }

    public boolean isWalking() {
        return isWalking;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public boolean isUp(float curPosY) {
        boolean isUp = prePoxY < curPosY;
        prePoxY = curPosY;
        return isUp;
    }
}
