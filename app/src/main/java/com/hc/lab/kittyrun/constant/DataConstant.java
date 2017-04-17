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

    public static final int GAP_WIDTH = 60;

    public static final int MAX_JUMP_HEIGHT = 250;
    public static final int MEDIUM_JUMP_HEIGHT = 150;

    public static final int KITTY_JUMP_VELOCITY= 250;

    public static final int LAWN_MOVE_VELOCITY= 200;
    //重力加速度
    public static final int GA= 10;

}
