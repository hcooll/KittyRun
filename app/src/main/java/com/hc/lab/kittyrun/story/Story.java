package com.hc.lab.kittyrun.story;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.screenplay.ScreenPlay;
import com.hc.lab.kittyrun.sprite.ActionSprite;

import java.util.HashMap;

/**
 * Created by congwiny on 2017/4/14.
 */

public class Story {
    protected int type;
    public HashMap<ActionSprite, Action> plotHashMap;

    public Story() {
        this.plotHashMap = new HashMap<>();
    }

    public void addPlot(ActionSprite sprite, Action action) {
        plotHashMap.put(sprite, action);
    }

    public HashMap<ActionSprite, Action> getPlot() {
        return plotHashMap;
    }

}
