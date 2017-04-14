package com.hc.lab.kittyrun.base;

import org.cocos2d.nodes.CCSprite;

/**
 * Created by hc on 2017/4/13 0013.
 */

public class BaseSprite extends CCSprite {

    public BaseSprite(String filepath) {
        super(filepath);
        this.setAnchorPoint(0f, 0f);
        this.setScale(0.5f);
    }

}
