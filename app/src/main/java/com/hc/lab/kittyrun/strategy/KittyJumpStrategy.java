package com.hc.lab.kittyrun.strategy;

import org.cocos2d.types.CGPoint;

/**
 * Created by congwiny on 2017/4/16.
 */

public class KittyJumpStrategy extends Strategy {
    public float duration;
    public float uPduration; // 向上跳的时间
    public float downDuration;// 下降的时间
    public float jumpHeight;
    public CGPoint toPosition;

    @Override
    public String toString() {
        return "KittyJumpStrategy{" +
                "duration=" + duration +
                "uPduration=" + uPduration +
                "downDuration=" + downDuration +
                ", jumpHeight=" + jumpHeight +
                ", toPosition.x=" + toPosition.x +
                ", toPosition.y=" + toPosition.y +
                '}';
    }
}
