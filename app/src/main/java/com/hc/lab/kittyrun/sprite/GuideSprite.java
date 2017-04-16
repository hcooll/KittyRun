package com.hc.lab.kittyrun.sprite;

import com.hc.lab.kittyrun.screenplay.ScreenPlay;

import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGRect;

/**
 * Created by congwiny on 2017/4/14.
 */

public class GuideSprite extends ActionSprite{

    public GuideSprite(String filepath) {
        super(filepath);
    }

    public GuideSprite(String filepath, CGRect rect) {
        super(filepath, rect);
    }
}
