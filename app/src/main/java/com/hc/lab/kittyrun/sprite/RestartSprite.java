package com.hc.lab.kittyrun.sprite;

import com.hc.lab.kittyrun.constant.DataConstant;

import static com.networkbench.agent.impl.m.x.f;

/**
 * Created by hc on 2017/4/17 0017.
 */

public class RestartSprite extends ActionSprite {

    public RestartSprite(String filepath) {
        super(filepath);
        setAnchorPoint(0.5f, 0.5f);
        this.setPosition(screenSize.width / 2, screenSize.height / 2);
        setScale(DataConstant.SCALE_SIZE);

    }

}
