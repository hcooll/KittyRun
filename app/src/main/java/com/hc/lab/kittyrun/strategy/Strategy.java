package com.hc.lab.kittyrun.strategy;

import org.cocos2d.types.CGPoint;

/**
 * Created by congwiny on 2017/4/15.
 * <p>
 * 保存精灵当前动作的策略
 */

public class Strategy {
    //所属精灵的tag
    public int tag;
    //锚点
    public CGPoint anchor;
    //位置
    public CGPoint position;
}
