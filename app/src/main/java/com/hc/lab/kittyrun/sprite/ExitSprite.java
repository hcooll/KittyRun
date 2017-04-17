package com.hc.lab.kittyrun.sprite;

/**
 * Created by hc on 2017/4/17 0017.
 */

public class ExitSprite extends ActionSprite {

    public ExitSprite(String filepath) {
        super(filepath);
        setAnchorPoint(1.0f, 1.0f);
        this.setPosition(screenSize.width - 20, screenSize.height - 20);
        this.setScale(0.5f);
    }

}
