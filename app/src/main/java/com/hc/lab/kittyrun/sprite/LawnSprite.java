package com.hc.lab.kittyrun.sprite;

import android.util.Log;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.constant.DataConstant;
import com.hc.lab.kittyrun.strategy.LawnStrategy;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.util.CGPointUtil;

/**
 * Created by congwiny on 2017/4/14.
 */

public class LawnSprite extends ActionSprite {

    private boolean isMoving;
    private LawnStrategy mLawnStrategy;

    public LawnSprite(String filepath) {
        super(filepath);
        setScale(DataConstant.SCALE_SIZE);
    }

    @Override
    public void run(Action action) {
        super.run(action);
        if (isMoving) {
            return;
        }
        mLawnStrategy = (LawnStrategy) action.getStrategy();
        move(mLawnStrategy);
    }

    /**
     * 移动
     */
    private void move(LawnStrategy lawnStrategy) {
        isMoving = true;
        //计算速度
        CGPoint end = CGPoint.make(-getContentSize().width, position_.y);
        float t = CGPointUtil.distance(position_, end) / lawnStrategy.speed;
        CCMoveTo ccMoveTo = CCMoveTo.action(t, end);
        CCSequence ccSequence = CCSequence.actions(ccMoveTo, CCCallFunc.action(this, "dismiss"));
        this.runAction(ccSequence);
    }

    public void dismiss() {
        this.removeSelf();
        isMoving = false;
    }

    public void stopMove() {
        stopAllActions();
        isMoving = false;
    }


}
