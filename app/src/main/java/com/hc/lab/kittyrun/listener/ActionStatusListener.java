package com.hc.lab.kittyrun.listener;

import com.hc.lab.kittyrun.action.Action;

/**
 * Created by congwiny on 2017/4/14.
 */

public interface ActionStatusListener {

    void onActionStart(Action action);

    void onActionStop(Action action);
}
