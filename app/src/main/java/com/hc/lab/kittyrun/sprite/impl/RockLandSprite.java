package com.hc.lab.kittyrun.sprite.impl;

import com.hc.lab.kittyrun.sprite.LandSprite;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.util.CGPointUtil;

/**
 * Created by hc on 2017/4/13 0013.
 * <p>
 * 石头地面
 */

public class RockLandSprite extends LandSprite {


    public static String getImage(int highDegree, int widthDegree) {
        switch (highDegree) {
            case HIGH_DEGREE_LOW:
                if (widthDegree == WIDTH_DEGREE_SHORT) {
                    return "image/land/low_short_rock.png";
                } else {
                    return "image/land/low_long_rock.png";
                }
            case HIGH_DEGREE_MEDIUM:
                if (widthDegree == WIDTH_DEGREE_SHORT) {
                    return "image/land/medium_short_rock.png";
                } else {
                    return "image/land/medium_long_rock.png";
                }
            case HIGH_DEGREE_HIGH:
                if (widthDegree == WIDTH_DEGREE_SHORT) {
                    return "image/land/high_short_rock.png";
                } else {
                    return "image/land/high_long_rock.png";
                }
        }
        return "";
    }


    public RockLandSprite(String filePath, CGPoint start, CGPoint end) {
        super(filePath, start, end);
    }




}
