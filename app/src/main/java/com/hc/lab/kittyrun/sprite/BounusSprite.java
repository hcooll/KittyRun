package com.hc.lab.kittyrun.sprite;

import com.hc.lab.kittyrun.constant.DataConstant;
import com.hc.lab.kittyrun.util.SizeConvertUtils;

import org.cocos2d.nodes.CCLabelAtlas;
import org.cocos2d.types.CGRect;

/**
 * Created by congwiny on 2017/4/19.
 */

public class BounusSprite extends ActionSprite {
    private CCLabelAtlas labelAtlas;
    private MoonSprite moonSprite;
    private int mBounus;

    public BounusSprite(String filepath) {
        super(filepath);

        setAnchorPoint(0f, 0f);
        float positionX = SizeConvertUtils
                .getConvertWidth(DataConstant.ORIGIN_BOUNUS_POSITON_X);
        float positionY = SizeConvertUtils
                .getConvertWidth(DataConstant.ORIGIN_BOUNUS_POSITON_Y);
        setPosition(positionX, positionY);

        setTextureRect(CGRect.zero());
        moonSprite = new MoonSprite("image/bounus/moon.png");
        moonSprite.setAnchorPoint(0f, 0.5f);
        addChild(moonSprite);

        labelAtlas = new CCLabelAtlas("0123456789", "image/bounus/bounus.png",
                DataConstant.BOUNUS_NUMBER_WIDTH, DataConstant.BOUNUS_NUMBER_HEIGHT, '0');
        labelAtlas.setAnchorPoint(0f, 0.5f);
        float marginLeft = SizeConvertUtils.getConvertWidth(DataConstant.ORIGIN_BOUNUS_MARGIN_RIGHT);
        labelAtlas.setPosition(moonSprite.getPosition().x + moonSprite.getContentSize().width + marginLeft, moonSprite.getPosition().y);
        labelAtlas.setString(String.valueOf(0));
        addChild(labelAtlas);
        setScale(DataConstant.SCALE_SIZE);

    }

    public void resetBounus() {
        mBounus = 0;
        labelAtlas.setString(String.valueOf(mBounus));
    }

    public void addBounus(int bounus) {
        mBounus += bounus;
        labelAtlas.setString(String.valueOf(mBounus));
    }


}
