package com.hc.lab.kittyrun.action;

/**
 * Created by hc on 2017/4/19 0019.
 */

public class TipsAction extends Action {

    public String tips;
    public boolean isAutoDismiss; // 是否自动消失

    public TipsAction(String tips) {
        this(tips, false);
    }

    public TipsAction(String tips, boolean isAutoDismiss) {
        this.type = TYPE_SHOW_TIPS;
        this.tips = tips;
        this.isAutoDismiss = isAutoDismiss;
    }
}
