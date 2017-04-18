package com.hc.lab.kittyrun.strategy;

import android.util.Log;

import com.hc.lab.kittyrun.constant.DataConstant;
import com.hc.lab.kittyrun.sprite.KittySprite;
import com.hc.lab.kittyrun.sprite.LawnSprite;
import com.hc.lab.kittyrun.model.GiftModel;
import com.hc.lab.kittyrun.util.MathRandom;
import com.hc.lab.kittyrun.util.PhysicsUtils;

import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import java.util.Random;

/**
 * Created by congwiny on 2017/4/15.
 * <p>
 * 控制游戏的策略
 */

public class StrategyManager {

    // 游戏难度
    public static final int MODE_EASY = 1;
    public static final int MODE_MEDIUM = 2;
    public static final int MODE_DIFFICULT = 3;

    // 地面高度等级
    public static final int LAWN_HEIGHT_DEGREE_LOW = 1;
    public static final int LAWN_HEIGHT_DEGREE_MEDIUM = 2;
    public static final int LAWN_HEIGHT_DEGREE_HIGH = 3;

    // 地面宽度等级
    public static final int LAWN_WIDTH_DEGREE_SHORT = 1;
    public static final int LAWN_WIDTH_DEGREE_LONG = 2;

    private int strategyMode;
    private Random mRandom;

    private static final float[] KITTY_JUMP_HEIGHT = new float[]{
            DataConstant.MAX_JUMP_HEIGHT,
            DataConstant.MEDIUM_JUMP_HEIGHT
    };


    private static StrategyManager strategyManager;

    private StrategyManager() {
        mRandom = new Random();
        initStrategyMode();
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

    public void initStrategyMode() {
        this.strategyMode = MODE_EASY;
    }

    public LawnStrategy getLawnActionStrategy(boolean isDefalut) {
        LawnStrategy lawnStrategy = new LawnStrategy();
        if (isDefalut) {
            lawnStrategy.lawnPic = getLawnImagePic(strategyMode, LAWN_WIDTH_DEGREE_SHORT, LAWN_HEIGHT_DEGREE_MEDIUM);
            lawnStrategy.speed = DataConstant.LAWN_MOVE_VELOCITY;// 模拟值，以后可以计算
            lawnStrategy.anchor = CGPoint.ccp(0, 0.0f);
            lawnStrategy.position = CGPoint.ccp(DataConstant.WIDTH / 6, getLawnHeight(LAWN_HEIGHT_DEGREE_MEDIUM));
        } else {
            int widthDegree = mRandom.nextInt(2) + 1;
            int hightDegree = mRandom.nextInt(3) + 1;
            lawnStrategy.lawnPic = getLawnImagePic(strategyMode, widthDegree, hightDegree);
            lawnStrategy.speed = DataConstant.LAWN_MOVE_VELOCITY;// 模拟值，以后可以计算
            lawnStrategy.anchor = CGPoint.ccp(0f, 0.0f);
            lawnStrategy.position = CGPoint.ccp(DataConstant.WIDTH + getLawnGap(strategyMode), getLawnHeight(hightDegree));
        }
        Log.e("getLawnActionStrategy", "getLawnActionStrategy strategyMode: " + strategyMode);
        return lawnStrategy;
    }

    private int getLawnGap(int strategyMode) {
        switch (strategyMode) {
            case MODE_EASY:
                return DataConstant.GAP_WIDTH + mRandom.nextInt(30);
            case MODE_MEDIUM:
                return DataConstant.GAP_WIDTH + mRandom.nextInt(50);
            case MODE_DIFFICULT:
                return DataConstant.GAP_WIDTH + mRandom.nextInt(70);
        }
        return DataConstant.GAP_WIDTH;
    }

    private String getLawnImagePic(int strategyMode, int widthDegree, int hightDegree) {
        switch (strategyMode) {
            case MODE_EASY:
                if (widthDegree == LAWN_WIDTH_DEGREE_SHORT) {
                    if (hightDegree == LAWN_HEIGHT_DEGREE_LOW) {
                        return "image/land/low_short_grass.png";
                    } else if (hightDegree == LAWN_HEIGHT_DEGREE_MEDIUM) {
                        return "image/land/medium_short_grass.png";
                    } else {
                        return "image/land/high_short_grass.png";
                    }
                } else {
                    if (hightDegree == LAWN_HEIGHT_DEGREE_LOW) {
                        return "image/land/low_long_grass.png";
                    } else if (hightDegree == LAWN_HEIGHT_DEGREE_MEDIUM) {
                        return "image/land/medium_long_grass.png";
                    } else {
                        return "image/land/high_long_grass.png";
                    }
                }
            case MODE_MEDIUM:
                if (widthDegree == LAWN_WIDTH_DEGREE_SHORT) {
                    if (hightDegree == LAWN_HEIGHT_DEGREE_LOW) {
                        return "image/land/low_short_rock.png";
                    } else if (hightDegree == LAWN_HEIGHT_DEGREE_MEDIUM) {
                        return "image/land/medium_short_rock.png";
                    } else {
                        return "image/land/high_short_rock.png";
                    }
                } else {
                    if (hightDegree == LAWN_HEIGHT_DEGREE_LOW) {
                        return "image/land/low_long_rock.png";
                    } else if (hightDegree == LAWN_HEIGHT_DEGREE_MEDIUM) {
                        return "image/land/medium_long_rock.png";
                    } else {
                        return "image/land/high_long_rock.png";
                    }
                }
            case MODE_DIFFICULT:
                if (widthDegree == LAWN_WIDTH_DEGREE_SHORT) {
                    if (hightDegree == LAWN_HEIGHT_DEGREE_LOW) {
                        return "image/land/low_short_snow.png";
                    } else if (hightDegree == LAWN_HEIGHT_DEGREE_MEDIUM) {
                        return "image/land/medium_short_snow.png";
                    } else {
                        return "image/land/high_short_snow.png";
                    }
                } else {
                    if (hightDegree == LAWN_HEIGHT_DEGREE_LOW) {
                        return "image/land/low_long_snow.png";
                    } else if (hightDegree == LAWN_HEIGHT_DEGREE_MEDIUM) {
                        return "image/land/medium_long_snow.png";
                    } else {
                        return "image/land/high_long_snow.png";
                    }
                }
        }
        return "";
    }

    private int getLawnHeight(int degree) {
        switch (degree) {
            case LAWN_HEIGHT_DEGREE_LOW:
                return DataConstant.LAWN_HEIGHT_LAW;
            case LAWN_HEIGHT_DEGREE_MEDIUM:
                return DataConstant.LAWN_HEIGHT_MEDIUN;
            case LAWN_HEIGHT_DEGREE_HIGH:
                return DataConstant.LAWN_HEIGHT_HIGH;
        }
        return 0;
    }

    public KittyJumpStrategy getKittyJumpStrategy(long miles, KittySprite kittySprite, LawnSprite preLawnSprite, LawnSprite curLawnSprite) {
        KittyJumpStrategy kittyJumpStrategy = new KittyJumpStrategy();
        int index = mRandom.nextInt(2);
        kittyJumpStrategy.jumpHeight = KITTY_JUMP_HEIGHT[index];

        CGPoint kittyPosition = kittySprite.getPosition();
        CGSize kittySize = kittySprite.getContentSize();
        CGPoint curLawnPosition = curLawnSprite.getPosition();
        CGSize curLawnSize = curLawnSprite.getContentSize();

        Log.e("getKittyJumpStrategy", "kittyHeight=" + kittyPosition.y
                + ",kittyLeftX=" + kittyPosition.x
                + ",kittyRightX=" + (kittyPosition.x + kittySize.width)
                + ",currentLawnHeight=" + (curLawnPosition.y + curLawnSize.height)
                + ",currentLawnLeftX=" + curLawnPosition.x
                + ",currentLawnRightX=" + (curLawnPosition.x + curLawnSize.width));

        float duration = PhysicsUtils.getKittyJumpTime(kittyJumpStrategy.jumpHeight, kittyPosition.y, kittyPosition.y);// 跳到当前位置需要的时间
        float lawnMoveDis = DataConstant.LAWN_MOVE_VELOCITY * duration; // 草坪移动的距离
        // 小人跳不过脚下的地面，或者小人跳的最高点没有下一个草坪高
        if ((preLawnSprite != null
                && lawnMoveDis < ((preLawnSprite.getPosition().x + preLawnSprite.getContentSize().width) - (kittyPosition.x + kittySize.width / 3)))
                || (kittyPosition.y + kittyJumpStrategy.jumpHeight) < (curLawnPosition.y + curLawnSize.height)) {
            Log.e("getKittyJumpStrategy", "跳到当前的位置");
            kittyJumpStrategy.duration = duration;
            kittyJumpStrategy.toPosition = CGPoint.ccp(kittyPosition.x, kittyPosition.y);
            return kittyJumpStrategy;
        } else {
            Log.e("getKittyJumpStrategy", "跳到下一个草坪");
            kittyJumpStrategy.duration = PhysicsUtils.getKittyJumpTime(kittyJumpStrategy.jumpHeight, kittyPosition.y, curLawnPosition.y + curLawnSize.height);
            kittyJumpStrategy.toPosition = CGPoint.ccp(kittyPosition.x, curLawnPosition.y + curLawnSize.height);
        }
        Log.e("getKittyJumpStrategy", "小人的跳跃策略 getKittyJumpStrategy : " + kittyJumpStrategy);
        return kittyJumpStrategy;
    }

    public void setStrategyMode(long miles) {
        if (miles < 600) {
            this.strategyMode = MODE_EASY;
        } else if (miles < 1100) {
            this.strategyMode = MODE_MEDIUM;
        } else {
            this.strategyMode = MODE_DIFFICULT;
        }
        Log.e("", "setStrategyMode miles: " + miles + " strategyMode: " + this.strategyMode);
    }

    public GiftStrategy getGiftStrategy(GiftModel giftModel){
        GiftStrategy giftStrategy = new GiftStrategy();
        giftStrategy.setGiftModel(giftModel);
        return giftStrategy;
    }
}
