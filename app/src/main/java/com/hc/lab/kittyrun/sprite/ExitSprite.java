package com.hc.lab.kittyrun.sprite;

import com.hc.lab.kittyrun.constant.DataConstant;

/**
 * Created by hc on 2017/4/17 0017.
 */

public class ExitSprite extends ActionSprite {

    public ExitSprite(String filepath) {
        super(filepath);
        setAnchorPoint(1.0f, 1.0f);
        this.setPosition(screenSize.width - 20, screenSize.height - 20);
        this.setScale(DataConstant.SCALE_SIZE);
    }

}
