package com.hc.lab.kittyrun.sprite;

import android.util.Log;

import com.hc.lab.kittyrun.action.Action;
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

    private boolean working;
    private LawnStrategy mLawnStrategy;

    public LawnSprite(String filepath) {
        super(filepath);
    }

    public LawnSprite(String filepath, CGRect rect) {
        super(filepath, rect);
    }


    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    @Override
    public void run(Action action) {
        super.run(action);

        mLawnStrategy = (LawnStrategy) action.getStrategy();
        Log.e("zzz", "lawn sprite strategy==" + mLawnStrategy + "action==" + action);
        move(mLawnStrategy);
    }

    /**
     * 移动
     */
    private void move(LawnStrategy lawnStrategy) {
        //计算速度
        CGPoint start = CGPoint.make(getPosition().x, lawnStrategy.y);
        CGPoint end = CGPoint.make(-getContentSize().width, lawnStrategy.y);
        float t = CGPointUtil.distance(start, end) / lawnStrategy.speed;
        CCMoveTo ccMoveTo = CCMoveTo.action(t, end);
        CCSequence ccSequence = CCSequence.actions(ccMoveTo, CCCallFunc.action(this, "dismiss"));
        this.runAction(ccSequence);
    }

    public void dismiss() {
        this.removeSelf();
        stopAllActions();

    }

    public void stopMove() {
        stopAllActions();
    }


}
