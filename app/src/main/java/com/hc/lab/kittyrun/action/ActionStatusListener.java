package com.hc.lab.kittyrun.action;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.story.Story;

/**
 * Created by congwiny on 2017/4/14.
 */

public interface ActionStatusListener {

    void onActionStart(Action action);

    void onActionStop(Action action);
}
