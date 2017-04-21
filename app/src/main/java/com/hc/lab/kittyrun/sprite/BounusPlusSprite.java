package com.hc.lab.kittyrun.sprite;

import android.util.Log;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.constant.DataConstant;
import com.hc.lab.kittyrun.strategy.BounusPlusStrategy;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCLabelAtlas;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.utils.javolution.TextBuilder;

/**
 * Created by congwiny on 2017/4/19.
 */

public class BounusPlusSprite extends ActionSprite {

    private CCLabelAtlas labelAtlas;
    private MoonSprite moonSprite;
    private TextBuilder textBuilder = new TextBuilder();

    public BounusPlusSprite(String filepath) {
        super(filepath);

        setAnchorPoint(0.5f, 0.5f);
        setPosition(screenSize.width / 2, screenSize.height / 2);

        setTextureRect(CGRect.zero());
        labelAtlas = new CCLabelAtlas("0123456789", "image/bounus/bounus2.png",
                DataConstant.BOUNUS_NUMBER_WIDTH, DataConstant.BOUNUS_NUMBER_HEIGHT, '0');
        labelAtlas.setAnchorPoint(0.5f, 0.5f);
        labelAtlas.setString(":15");
        addChild(labelAtlas);

        moonSprite = new MoonSprite("image/bounus/moon.png");
        moonSprite.setAnchorPoint(0f, 0.5f);
        moonSprite.setPosition(labelAtlas.getPosition().x + labelAtlas.getWidth() / 2, labelAtlas.getPosition().y);
        addChild(moonSprite);

        setScale(DataConstant.SCALE_SIZE);
    }

    @Override
    public void run(Action action) {
        super.run(action);
        if (action != null) {
            BounusPlusStrategy bounusPlusStrategy = (BounusPlusStrategy) action.getStrategy();
            if (bounusPlusStrategy != null) {
                Log.e("yyyy", "run bounus..." + bounusPlusStrategy.bounusCount);
                if (bounusPlusStrategy.bounusCount > 0) {
                    textBuilder.reset();
                    setPosition(bounusPlusStrategy.position);
                    labelAtlas.setString(textBuilder.append(":").append(bounusPlusStrategy.bounusCount));
                    moonSprite.setPosition(labelAtlas.getPosition().x + labelAtlas.getWidth() / 2, labelAtlas.getPosition().y);
                    CCSequence ccSequence = CCSequence.actions(CCDelayTime.action(1.0f),
                            CCCallFunc.action(this, "show"),
                            CCMoveTo.action(0.5f, CGPoint.ccp(getPosition().x, getPosition().y + 20)),
                            CCFadeOut.action(1.0f),
                            CCCallFunc.action(this, "hide"));
                    runAction(ccSequence);
                }
            }
        }

    }

    public void show() {
        setVisible(true);
    }


    public void hide() {
        setVisible(false);
    }
}
