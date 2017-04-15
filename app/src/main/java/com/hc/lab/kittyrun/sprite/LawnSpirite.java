package com.hc.lab.kittyrun.sprite;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.screenplay.ScreenPlay;
import com.hc.lab.kittyrun.story.Story;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.util.CGPointUtil;

/**
 * Created by congwiny on 2017/4/14.
 */

public class LawnSpirite extends ActionSprite {

    private boolean working;

    public LawnSpirite(ScreenPlay play) {
        super(play);
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
        CCTexture2D texture = CCTextureCache.sharedTextureCache().addImage("image/land/high_long_rock.png");
        setTexture(texture);
        move();

    }

    /**
     * 移动
     */
    private void move() {
        //计算速度
        CGPoint start = CGPoint.make(mLayer.getCgSize().width, 200);
        CGPoint end = CGPoint.make(-mLayer.getCgSize().width, 200);
        float t = CGPointUtil.distance(start, end) / 20;
        CCMoveTo ccMoveTo = CCMoveTo.action(t, end);
        CCSequence ccSequence = CCSequence.actions(ccMoveTo, CCCallFunc.action(this, "dismiss"));
        this.runAction(ccSequence);
    }

    public void dismiss() {
        this.removeSelf();
        play.onActionStop(mAction);
    }
}
