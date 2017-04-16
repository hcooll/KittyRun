package com.hc.lab.kittyrun.strategy;

import org.cocos2d.types.CGPoint;

/**
 * Created by congwiny on 2017/4/16.
 */

public class KittyJumpStrategy extends Strategy {
    public float duration;
    public float jumpHeight;
    public CGPoint toPosition;

    @Override
    public String toString() {
        return "KittyJumpStrategy{" +
                "duration=" + duration +
                ", jumpHeight=" + jumpHeight +
                ", toPosition.x=" + toPosition.x +
                ", toPosition.y=" + toPosition.y +
                '}';
    }
}
