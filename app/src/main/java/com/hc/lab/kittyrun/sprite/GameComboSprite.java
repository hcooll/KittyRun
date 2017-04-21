package com.hc.lab.kittyrun.sprite;

import com.hc.lab.kittyrun.constant.DataConstant;
import com.hc.lab.kittyrun.util.SizeConvertUtils;

import org.cocos2d.nodes.CCLabelAtlas;
import org.cocos2d.types.CGRect;

/**
 * Created by congwiny on 2017/4/16.
 */

public class GameComboSprite extends ActionSprite {

    private CCLabelAtlas labelAtlas;
    private ComboSprite comboSprite;
    private int mCombo;

    public GameComboSprite(String filepath) {
        super(filepath);

        float comboX = SizeConvertUtils.getConvertWidth(DataConstant.ORIGIN_GAME_COMBO_X);
        float comboY = SizeConvertUtils.getConvertWidth(DataConstant.ORIGIN_GAME_COMBO_Y);
        float marginRight = SizeConvertUtils.getConvertWidth(DataConstant.ORIGIN_GAME_COMBO_MARGIN_RIGHT);

        setAnchorPoint(1f, 1f);
        setPosition(comboX-marginRight, comboY);

        setTextureRect(CGRect.zero());

        labelAtlas = new CCLabelAtlas("0123456789", "image/combo/combo.png",
                DataConstant.COMBO_NUMBER_WIDTH, DataConstant.COMBO_NUMBER_HEIGHT, '0');
        labelAtlas.setAnchorPoint(1f, 1f);
        labelAtlas.setString(String.valueOf(0));
        addChild(labelAtlas);

        comboSprite = new ComboSprite("image/combo/game_combo.png");
        comboSprite.setAnchorPoint(0.5f, 1f);
        comboSprite.setPosition(labelAtlas.getPosition().x - labelAtlas.getWidth() / 2
                , labelAtlas.getPosition().y - labelAtlas.getHeight());
        addChild(comboSprite);
        setScale(DataConstant.SCALE_SIZE);

    }

    public void addCombo(int combo) {
        mCombo += combo;
        labelAtlas.setString(String.valueOf(mCombo));
        comboSprite.setPosition(labelAtlas.getPosition().x - labelAtlas.getWidth() / 2
                , labelAtlas.getPosition().y - labelAtlas.getHeight());
    }

    public void resetCombo(){
        mCombo = 0;
        labelAtlas.setString(String.valueOf(mCombo));
        comboSprite.setPosition(labelAtlas.getPosition().x - labelAtlas.getWidth() / 2
                , labelAtlas.getPosition().y - labelAtlas.getHeight());
    }

}
