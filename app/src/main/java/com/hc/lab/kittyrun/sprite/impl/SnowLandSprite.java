package com.hc.lab.kittyrun.sprite.impl;

import com.hc.lab.kittyrun.sprite.LandSprite;

import org.cocos2d.types.CGPoint;

/**
 * Created by hc on 2017/4/13 0013.
 */

public class SnowLandSprite extends LandSprite {

    public static String getImage(int highDegree, int widthDegree) {
        switch (highDegree) {
            case HIGH_DEGREE_LOW:
                if (widthDegree == WIDTH_DEGREE_SHORT) {
                    return "image/land/low_short_snow.png";
                } else {
                    return "image/land/low_long_snow.png";
                }
            case HIGH_DEGREE_MEDIUM:
                if (widthDegree == WIDTH_DEGREE_SHORT) {
                    return "image/land/medium_short_snow.png";
                } else {
                    return "image/land/medium_long_snow.png";
                }
            case HIGH_DEGREE_HIGH:
                if (widthDegree == WIDTH_DEGREE_SHORT) {
                    return "image/land/high_short_snow.png";
                } else {
                    return "image/land/high_long_snow.png";
                }
        }
        return "";
    }

    public SnowLandSprite(String filePath, CGPoint start, CGPoint end) {
        super(filePath, start, end);
    }

}
