package com.hc.lab.kittyrun.sprite;

import com.hc.lab.kittyrun.constant.DataConstant;
import com.hc.lab.kittyrun.util.SizeConvertUtils;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

/**
 * Created by congwiny on 2017/4/16.
 */

public class GiftBarSprite extends ActionSprite {
    public GiftBarSprite(String filepath) {
        super(filepath);
        initGiftBar();
    }

    public GiftBarSprite(String filepath, CGRect rect) {
        super(filepath, rect);
        initGiftBar();
    }


    private void initGiftBar() {
        setColor(ccColor3B.ccc3(255, 20, 147));
        float width = SizeConvertUtils.getConvertWidth(DataConstant.ORIGIN_GIFT_BAR_WIDTH);
        float height = SizeConvertUtils.getConvertWidth(DataConstant.ORIGIN_GIFT_BAR_HEIGHT);
        setContentSize(CGSize.make(width, height));
        setAnchorPoint(CGPoint.ccp(0f, 0.5f));
        float positionY = SizeConvertUtils.getConvertWidth(1126);
        setPosition(DataConstant.WIDTH, positionY);

    }

    public void show() {
        stopAllActions();
        this.runAction(CCMoveTo.action(0.5f,
                CGPoint.ccp(screenSize.width - getContentSize().width,
                        getPosition().y)));
    }

    public void disappear() {
        stopAllActions();
        this.runAction(CCMoveTo.action(0.5f,
                CGPoint.ccp(screenSize.width,
                        getPosition().y)));
    }

}
