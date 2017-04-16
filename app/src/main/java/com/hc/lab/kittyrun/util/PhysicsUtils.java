package com.hc.lab.kittyrun.util;

import android.util.Log;

import com.hc.lab.kittyrun.constant.DataConstant;

/**
 * Created by congwiny on 2017/4/16.
 */

public class PhysicsUtils {

    public static float getMoveTime(float v, float s) {
        return s / v;
    }

    public static float getKittyJumpTime(float jumpHeight, float kittyPositionY, float nextLawnHeight) {
        Log.e("xxx","jump data="+jumpHeight+",poy"+kittyPositionY+",nexHeht"+nextLawnHeight);
        return (jumpHeight * 2 + (kittyPositionY - nextLawnHeight)) / DataConstant.KITTY_JUMP_VELOCITY;
    }
}
