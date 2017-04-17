package com.hc.lab.kittyrun.strategy;

import android.util.Log;

import com.hc.lab.kittyrun.constant.DataConstant;
import com.hc.lab.kittyrun.util.MathRandom;
import com.hc.lab.kittyrun.util.PhysicsUtils;

import org.cocos2d.types.CGPoint;

import java.util.Random;

/**
 * Created by congwiny on 2017/4/15.
 * <p>
 * 控制游戏的策略
 */

public class StrategyManager {

    public static final int MODE_EASY = 1;
    public static final int MODE_MEDIUM = 2;
    public static final int MODE_DIFFICULT = 3;
    private int strategyMode;
    private Random mRandom;

    private static final String[] lawns = new String[]{
            "image/land/low_long_rock.png",
            "image/land/low_short_rock.png",
            "image/land/medium_long_rock.png",
            "image/land/medium_short_rock.png",
            "image/land/high_long_rock.png",
            "image/land/high_short_rock.png"
    };

    private static final float[] kittyJumpHeight = new float[]{
            DataConstant.MAX_JUMP_HEIGHT,
            DataConstant.MEDIUM_JUMP_HEIGHT
    };


    private static StrategyManager strategyManager;

    private StrategyManager() {
        this.strategyMode = 1;
        mRandom = new Random();
    }

    public static StrategyManager getInstance() {
        if (strategyManager == null) {
            synchronized (StrategyManager.class) {
                if (strategyManager == null) {
                    strategyManager = new StrategyManager();
                }
            }
        }
        return strategyManager;
    }

    public LawnStrategy getLawnActionStrategy(boolean isDefalut) {
        switch (strategyMode) {
            case MODE_EASY:
                break;
            case MODE_MEDIUM:
                break;
            case MODE_DIFFICULT:
                break;
        }
        LawnStrategy lawnStrategy = new LawnStrategy();
        int gap = DataConstant.GAP_WIDTH + mRandom.nextInt(10);
        if (!isDefalut) {
            int position = MathRandom.percentageRandom();
            if (position < 0) {
                position = 0;
            }
            lawnStrategy.lawnPic = lawns[position];
            lawnStrategy.speed = DataConstant.LAWN_MOVE_VELOCITY;//模拟值，以后可以计算
            lawnStrategy.y = 0;//模拟值
            lawnStrategy.anchor = CGPoint.ccp(0, 0);

            lawnStrategy.position = CGPoint.ccp(DataConstant.WIDTH + gap, 0);
        } else {
            lawnStrategy.lawnPic = lawns[0];
            lawnStrategy.speed = DataConstant.LAWN_MOVE_VELOCITY;//模拟值，以后可以计算
            lawnStrategy.y = 0;//模拟值
            lawnStrategy.anchor = CGPoint.ccp(0, 0);
            lawnStrategy.position = CGPoint.ccp(0, 0);
        }
        return lawnStrategy;
    }

    public KittyJumpStrategy getKittyJumpStrategy(float jumpHeight, CGPoint kittyOriginPoint,
                                                  float nextLawnHeight) {

        KittyJumpStrategy kittyJumpStrategy = new KittyJumpStrategy();
        int index = mRandom.nextInt(2);
        kittyJumpStrategy.jumpHeight = kittyJumpHeight[index];

        kittyJumpStrategy.duration = PhysicsUtils
                .getKittyJumpTime(kittyJumpStrategy.jumpHeight, kittyOriginPoint.y, nextLawnHeight);
        CGPoint toPosition = new CGPoint();

        toPosition.x = kittyOriginPoint.x;
        toPosition.y = nextLawnHeight;
        kittyJumpStrategy.toPosition = toPosition;
        Log.e("xxx", "jump data==" + kittyJumpStrategy);
        return kittyJumpStrategy;
    }

    public int getStrategyMode() {
        return strategyMode;
    }

    public void setStrategyMode(int strategyMode) {
        this.strategyMode = strategyMode;
    }
}
