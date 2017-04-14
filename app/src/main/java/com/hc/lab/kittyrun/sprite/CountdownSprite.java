package com.hc.lab.kittyrun.sprite;

import com.hc.lab.kittyrun.base.BaseSprite;
import com.hc.lab.kittyrun.layer.RunLayer;
import com.hc.lab.kittyrun.util.CommonUtil;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCSpriteFrame;

import java.util.ArrayList;

/**
 * Created by hc on 2017/4/13 0013.
 * 倒计时动画
 * 计时结束后开始游戏
 */

public class CountdownSprite extends BaseSprite {

    RunLayer runLayer;
    private static ArrayList<CCSpriteFrame> countdownFrames;  // 飞

    public CountdownSprite(RunLayer runLayer) {
        super("image/bounus/0.png");
        this.runLayer = runLayer;
        countdown();
    }

    /**
     * 倒计时动画
     */
    private void countdown() {
        this.runAction(CCSequence.actions(
                CommonUtil.getAnimation(countdownFrames, 3, "image/bounus/%01d.png", 1.0f),
                CCCallFunc.action(this, "startGame")));
    }

    /**
     * 开始游戏
     */
    public void startGame() {
        runLayer.go();
        // 没有作用了就销毁掉
        this.removeSelf();
    }

}
