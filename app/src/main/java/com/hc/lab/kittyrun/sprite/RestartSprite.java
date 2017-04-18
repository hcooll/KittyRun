package com.hc.lab.kittyrun.sprite;

/**
 * Created by hc on 2017/4/17 0017.
 */

public class RestartSprite extends ActionSprite {

    public RestartSprite(String filepath) {
        super(filepath);
        setAnchorPoint(1.0f, 1.0f);
        this.setPosition(screenSize.width - 20, screenSize.height - 70);
        this.setScale(0.2f);
    }

}
