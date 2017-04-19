package com.hc.lab.kittyrun.constant;

/**
 * Created by hc on 2017/4/15 0015.
 */

public class DataConstant {

    /**
     * 游戏宽高比 9 : 16，根据视屏推流的大小而定
     * 游戏中的精灵都根据此宽高的百分比来设置大小
     */
    public static final int WIDTH = 360;
    public static final int HEIGHT = 640;

    public static final int GAP_WIDTH = 60; // 地面之间的距离

    public static final int MAX_JUMP_HEIGHT = 250;  // 跳跃高度
    public static final int MEDIUM_JUMP_HEIGHT = 200;

    public static final float MAX_JUMP_TIME = 2.0f;  // 跳跃时间
    public static final float MEDIUM_JUMP_TIME = 1.5f;

    public static final int LAWN_MOVE_TIME = 4; // 草坪移动一个屏幕的时间，单位：秒
    public static final int LAWN_MOVE_VELOCITY = WIDTH / LAWN_MOVE_TIME; // 草坪移动的速度

    public static final int MILE_NUMBER_WIDTH = 38; // 距离数字的宽高大小
    public static final int MILE_NUMBER_HEIGHT = 46;

    public static final int BOUNUS_NUMBER_WIDTH = 38; // 距离数字的宽高大小
    public static final int BOUNUS_NUMBER_HEIGHT = 46;

    public static final int MILE_PER_SECEND = 100; // 每一秒增加100米

    // 地面的高度
    public static final int LAWN_HEIGHT_LAW = 0;
    public static final int LAWN_HEIGHT_MEDIUN = 50;
    public static final int LAWN_HEIGHT_HIGH = 100;

    // 计算碰撞时的误差大小
    public static final int MISTAKE_DISTANCE = 5;


    public static final int KITTY_JUMP_VELOCITY = 250;

    //重力加速度
    public static final int GA = 10;

    //根据图片的大小
    public static final float ORIGIN_GIFT_WIDTH = 100;
    public static final float ORIGIN_AVATAR_WIDTH = 90;

    public static final float ORIGIN_GIFT_BAR_WIDTH = 300;
    public static final float ORIGIN_GIFT_BAR_HEIGHT = 76;


    public static final float ORIGIN_GIFT1_MOVE_X = 455;
    public static final float ORIGIN_GIFT2_MOVE_X = 546;
    public static final float ORIGIN_GIFT3_MOVE_X = 634;

    public static final float GIFT_MOVE_X_VELOCITY = 600;

    public static final float ORIGIN_GIFT_BAR_POSITON_Y = 1126;
    public static final float ORIGIN_BOUNUS_POSITON_Y = 1126;

}
