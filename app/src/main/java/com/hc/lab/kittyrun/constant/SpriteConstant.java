package com.hc.lab.kittyrun.constant;

import com.hc.lab.kittyrun.util.SizeConvertUtils;

import org.cocos2d.types.CGPoint;

/**
 * Created by congwiny on 2017/4/16.
 */

public class SpriteConstant {
    public static final int SPRITE_TAG_KITTY = 0x100;

    /**
     * 暂时没用
     */
    public static final int SPRITE_TAG_ROAD_ROCK01 = 0x201;
    public static final int SPRITE_TAG_ROAD_ROCK02 = 0x202;
    public static final int SPRITE_TAG_ROAD_ROCK03 = 0x203;
    public static final int SPRITE_TAG_ROAD_ROCK04 = 0x204;
    public static final int SPRITE_TAG_ROAD_ROCK05 = 0x205;
    public static final int SPRITE_TAG_ROAD_ROCK06 = 0x206;

    public static final int SPRITE_TAG_ROAD_SNOW01 = 0x301;
    public static final int SPRITE_TAG_ROAD_SNOW02 = 0x302;
    public static final int SPRITE_TAG_ROAD_SNOW03 = 0x303;
    public static final int SPRITE_TAG_ROAD_SNOW04 = 0x304;
    public static final int SPRITE_TAG_ROAD_SNOW05 = 0x305;
    public static final int SPRITE_TAG_ROAD_SNOW06 = 0x306;

    //具体某种礼物以礼物的Gift Id作为tag
    public static final int SPRITE_TAG_GIFT_CONTAINER = 0x401;
    public static final int SPRITE_TAG_MILE = 0x402;
    public static final int SPRITE_TAG_MOON = 0x403;
    public static final int SPRITE_TAG_COMBO = 0x404;
    public static final int SPRITE_TAG_COUNTDOWN = 0x405;
    public static final int SPRITE_TAG_FIRST_LAWN= SPRITE_TAG_ROAD_ROCK01;
    public static final int SPRITE_TAG_CLOSE= 0x406;

    //根据测量原图得来
    public static final float ORIGN_POSITION_X_KITTY = 233;
    public static final float ORIGN_POSITION_Y_KITTY = 446;


}
