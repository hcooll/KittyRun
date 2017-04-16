package com.hc.lab.kittyrun.sprite;

import org.cocos2d.types.CGRect;

/**
 * Created by congwiny on 2017/4/16.
 *
 * 根据Action中的生成GiftSprite
 * 返回GiftSprite到Layer层
 */

public class GiftContainerSprite extends ActionSprite {
    public GiftContainerSprite(String filepath) {
        super(filepath);
    }

    public GiftContainerSprite(String filepath, CGRect rect) {
        super(filepath, rect);
    }
}
