package com.hc.lab.kittyrun.util;

import com.hc.lab.kittyrun.constant.DataConstant;

import org.cocos2d.types.CGPoint;

/**
 * Created by congwiny on 2017/4/16.
 */

public class SizeConvertUtils {
    private static final int ORIGIN_PIC_WIDTH = 750;
    private static final int ORIGIN_PIC_HEIGHT = 1334;

    public static float getConvertWidth(float originWidth) {
        return originWidth * DataConstant.WIDTH / ORIGIN_PIC_WIDTH;
    }

    public static float getConvertHeight(float originHeight) {
        return originHeight * DataConstant.HEIGHT / ORIGIN_PIC_HEIGHT;
    }

    public static CGPoint getConvertCGPoint(float positionX, float positionY) {
        return CGPoint.ccp(SizeConvertUtils.getConvertWidth(positionX), SizeConvertUtils.getConvertWidth(positionY));
    }
}
