package com.hc.lab.kittyrun.strategy;

import com.hc.lab.kittyrun.constant.DataConstant;
import com.hc.lab.kittyrun.story.StoryManager;
import com.hc.lab.kittyrun.util.MathRandom;

import org.cocos2d.types.CGPoint;

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

    private static final String[] lawns = new String[]{
            "image/land/low_long_rock.png",
            "image/land/low_short_rock.png",
            "image/land/medium_long_rock.png",
            "image/land/medium_short_rock.png",
            "image/land/high_long_rock.png",
            "image/land/high_short_rock.png"
    };

    private static StrategyManager strategyManager;

    private StrategyManager() {
        this.strategyMode = 1;
    }

    public static StrategyManager getInstance() {
        if (strategyManager == null) {
            synchronized (StoryManager.class) {
                if (strategyManager == null) {
                    strategyManager = new StrategyManager();
                }
            }
        }
        return strategyManager;
    }

    public LawnStrategy getLawnActionStrategy() {
        switch (strategyMode) {
            case MODE_EASY:
                break;
            case MODE_MEDIUM:
                break;
            case MODE_DIFFICULT:
                break;
        }
        LawnStrategy lawnStrategy = new LawnStrategy();
        int position = MathRandom.percentageRandom();
        if (position < 0) {
            position = 0;
        }
        lawnStrategy.lawnPic = lawns[position];
        lawnStrategy.speed = 100;//模拟值，以后可以计算
        lawnStrategy.y = 20;//模拟值
        lawnStrategy.anchor = CGPoint.ccp(0, 0);
        lawnStrategy.position = CGPoint.ccp(DataConstant.WIDTH, 0);
        return lawnStrategy;
    }

    public int getStrategyMode() {
        return strategyMode;
    }

    public void setStrategyMode(int strategyMode) {
        this.strategyMode = strategyMode;
    }
}
