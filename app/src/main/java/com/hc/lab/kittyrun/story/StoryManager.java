package com.hc.lab.kittyrun.story;

import com.hc.lab.kittyrun.action.CountDownAction;
import com.hc.lab.kittyrun.action.GuideAction;
import com.hc.lab.kittyrun.action.JumpAction;
import com.hc.lab.kittyrun.action.MileAction;
import com.hc.lab.kittyrun.action.MoveAction;
import com.hc.lab.kittyrun.action.WalkAction;
import com.hc.lab.kittyrun.screenplay.ScreenPlay;
import com.hc.lab.kittyrun.sprite.CountdownSprite;
import com.hc.lab.kittyrun.sprite.GuideSpirite;
import com.hc.lab.kittyrun.sprite.KittySpirite;
import com.hc.lab.kittyrun.sprite.LawnSpirite;
import com.hc.lab.kittyrun.sprite.MileSprite;

import org.cocos2d.types.CGRect;

/**
 * Created by congwiny on 2017/4/14.
 */

public class StoryManager {

    private static final String TAG = StoryManager.class.getSimpleName();
    public static StoryManager storyManager;
    //电影剧本默认
    private ScreenPlay mPlay;

    public static StoryManager getInstance(ScreenPlay play) {
        if (storyManager == null) {
            synchronized (StoryManager.class) {
                if (storyManager == null) {
                    storyManager = new StoryManager(play);
                }
            }
        }
        return storyManager;
    }


    private StoryManager(ScreenPlay play) {
        mPlay = play;
    }

    public Story getDefaultStory(boolean firstGuide) {
        GuideStory guideStory = new GuideStory();
        if (firstGuide) {
            GuideSpirite guideSpirite = new GuideSpirite("blank.png");
            guideSpirite.setActionStatusListener(mPlay);
            guideStory.addPlot(guideSpirite, new GuideAction());
        }

        CountdownSprite countdownSprite = new CountdownSprite("image/bounus/0.png");
        countdownSprite.setActionStatusListener(mPlay);
        guideStory.addPlot(countdownSprite, new CountDownAction());


        return guideStory;
    }

    public Story getNewLawnStory() {
        NewLawnStory newLawnStory = new NewLawnStory();
        LawnSpirite spirite = new LawnSpirite("image/land/high_long_snow.png");
        spirite.setActionStatusListener(mPlay);
        newLawnStory.addPlot(spirite, new MoveAction());
        return newLawnStory;
    }

    public Story getKittyRunStory(boolean isJump) {
        KittyRunStory kittyRunStory = new KittyRunStory();
        KittySpirite kittySpirite = new KittySpirite("image/kitty/run0000.png");
        kittySpirite.setActionStatusListener(mPlay);

        CGRect cgRect = CGRect.make(-20, 20, 200, 100);
        MileSprite mileSprite = new MileSprite("blank.png", cgRect);

        mileSprite.setActionStatusListener(mPlay);

        if (isJump) {
            kittyRunStory.addPlot(kittySpirite, new JumpAction());
        } else {
            kittyRunStory.addPlot(kittySpirite, new WalkAction());
        }
        kittyRunStory.addPlot(mileSprite, new MileAction());
        return kittyRunStory;
    }

}
