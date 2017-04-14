package com.hc.lab.kittyrun.layer;

import android.view.MotionEvent;

import com.hc.lab.kittyrun.base.BaseLayer;
import com.hc.lab.kittyrun.sprite.CountdownSprite;
import com.hc.lab.kittyrun.sprite.KittySprite;
import com.hc.lab.kittyrun.sprite.LandSprite;
import com.hc.lab.kittyrun.sprite.impl.RockLandSprite;

import org.cocos2d.types.CGPoint;

import static com.hc.lab.kittyrun.sprite.LandSprite.HIGH_DEGREE_HIGH;

/**
 * Created by hc on 2017/4/13 0013.
 * <p>
 * 主游戏图层
 */

public class RunLayer extends BaseLayer {


    private static final int lowest = 200; // 最低点

    private boolean isStart;

    // Kitty精灵
    KittySprite kittySprite;

    public RunLayer() {
        super();

        ready(); // 准备，倒计时
    }

    private void ready() {
        CountdownSprite countdownSprite = new CountdownSprite(this);
        countdownSprite.setPosition(cgSize.width / 2, cgSize.height - 100);
        this.addChild(countdownSprite);
    }

    public void go() {

        // 游戏开始
        isStart = true;


        // 地面
        LandSprite landSprite = new RockLandSprite(
                RockLandSprite.getImage(LandSprite.HIGH_DEGREE_HIGH, LandSprite.WIDTH_DEGREE_LONG),
                CGPoint.make(cgSize.width, lowest),
                CGPoint.make(-cgSize.width, lowest)
        );
        this.addChild(landSprite);


        // Kitty精灵
        kittySprite = new KittySprite();
        kittySprite.setPosition(cgSize.width / 3, lowest);
        this.addChild(kittySprite);


        setIsTouchEnabled(true);
    }


    @Override
    public boolean ccTouchesBegan(MotionEvent event) {

        System.out.println("ccTouchesBegan: " + event);

        if (isStart) {
            kittySprite.fly(KittySprite.FLY_DEGREE_MEDIUM);
        }

        return super.ccTouchesBegan(event);
    }
}
