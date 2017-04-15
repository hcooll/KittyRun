package com.hc.lab.kittyrun.layer;

import android.view.MotionEvent;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.base.BaseLayer;
import com.hc.lab.kittyrun.sprite.ActionSprite;
import com.hc.lab.kittyrun.story.Story;

import org.cocos2d.types.CGPoint;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by congwiny on 2017/4/14.
 */

public class KittyRunLayer extends BaseLayer {

    private HashMap<ActionSprite, Story> storyHashMap;


    public KittyRunLayer() {
        storyHashMap = new HashMap<>();
    }

    public void performanceStory(Story... storys) {

        for (Story story : storys) {
            HashMap<ActionSprite, Action> hashMap = story.getPlot();
            Set<ActionSprite> actionSprites = hashMap.keySet();
            for (ActionSprite actionSprite : actionSprites) {
                Action action = hashMap.get(actionSprite);
                if (!storyHashMap.containsKey(actionSprite)) {
                    //先这么设置。要想设置Spirite的位置，就得设置Strategy
                    if (action.getStrategy() == null) {
                        actionSprite.setAnchorPoint(CGPoint.ccp(0.5f, 0.5f));
                        actionSprite.setPosition(cgSize.width / 2, cgSize.height / 2);
                    }
                    actionSprite.setLayer(this);
                    this.addChild(actionSprite);
                    storyHashMap.put(actionSprite, story);
                }
                actionSprite.run(action);
            }
        }
    }

    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        return super.ccTouchesBegan(event);
    }

}
