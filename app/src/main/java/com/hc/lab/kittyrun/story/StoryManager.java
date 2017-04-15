package com.hc.lab.kittyrun.story;

import android.util.Log;
import android.util.SparseArray;

import com.hc.lab.kittyrun.action.CountDownAction;
import com.hc.lab.kittyrun.action.GuideAction;
import com.hc.lab.kittyrun.action.JumpAction;
import com.hc.lab.kittyrun.action.MileAction;
import com.hc.lab.kittyrun.action.MoveAction;
import com.hc.lab.kittyrun.action.WalkAction;
import com.hc.lab.kittyrun.screenplay.ScreenPlay;
import com.hc.lab.kittyrun.sprite.CountdownSprite2;
import com.hc.lab.kittyrun.sprite.GuideSpirite;
import com.hc.lab.kittyrun.sprite.KittySpirite2;
import com.hc.lab.kittyrun.sprite.LawnSpirite;
import com.hc.lab.kittyrun.sprite.MileSprite;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
            GuideSpirite guideSpirite = new GuideSpirite(mPlay);

            guideStory.addPlot(guideSpirite, new GuideAction());
        }

        CountdownSprite2 countdownSprite2 = new CountdownSprite2(mPlay);
        guideStory.addPlot(countdownSprite2, new CountDownAction());


        return guideStory;
    }

    public Story getNewLawnStory() {
        NewLawnStory newLawnStory = new NewLawnStory();
        LawnSpirite spirite = new LawnSpirite(mPlay);
        newLawnStory.addPlot(spirite, new MoveAction());
        return newLawnStory;
    }

    public Story getKittyRunStory(boolean isJump) {
        KittyRunStory kittyRunStory = new KittyRunStory();
        KittySpirite2 kittySpirite2 = new KittySpirite2(mPlay);
        MileSprite mileSprite = new MileSprite(mPlay);
        if (isJump) {
            kittyRunStory.addPlot(kittySpirite2, new JumpAction());
        } else {
            kittyRunStory.addPlot(kittySpirite2, new WalkAction());
        }
        kittyRunStory.addPlot(mileSprite, new MileAction());
        return kittyRunStory;
    }

}
