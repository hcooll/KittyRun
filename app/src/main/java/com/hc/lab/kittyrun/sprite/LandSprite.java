package com.hc.lab.kittyrun.sprite;

import com.hc.lab.kittyrun.base.BaseSprite;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.util.CGPointUtil;

/**
 * Created by hc on 2017/4/13 0013.
 * <p>
 * 地面:草坪、石头、雪地
 */

public abstract class LandSprite extends BaseSprite {

    // 高度等级
    public static final int HIGH_DEGREE_LOW = 0x1;
    public static final int HIGH_DEGREE_MEDIUM = 0x2;
    public static final int HIGH_DEGREE_HIGH = 0x3;

    // 宽度等级
    public static final int WIDTH_DEGREE_SHORT = 0x1;
    public static final int WIDTH_DEGREE_LONG = 0x2;

    protected int speed = 40; //速度
    protected int highDegree = HIGH_DEGREE_MEDIUM;

    protected CGPoint startPoint;// 起点
    protected CGPoint endPoint;// 终点

    public LandSprite(String filePath, CGPoint start, CGPoint end) {
        super(filePath);
        this.setAnchorPoint(0f, 1f);
        this.startPoint = start;
        this.endPoint = end;
        this.setPosition(start);
        move();
    }

    /**
     * 移动
     */
    private void move() {
        //计算速度
        float t = CGPointUtil.distance(startPoint, endPoint) / speed;
        CCMoveTo ccMoveTo = CCMoveTo.action(t, endPoint);
        CCSequence ccSequence = CCSequence.actions(ccMoveTo, CCCallFunc.action(this, "dissmiss"));
        this.runAction(ccSequence);
    }

    public void dissmiss() {
        this.removeSelf();
    }

}
