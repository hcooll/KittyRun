package com.hc.lab.kittyrun.screenplay;

import android.content.Context;

import com.hc.lab.kittyrun.KittyRunDirector;
import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.action.CountDownAction;
import com.hc.lab.kittyrun.constant.PreferenceConstant;
import com.hc.lab.kittyrun.util.PreferenceUtils;

/**
 * Created by congwiny on 2017/4/14.
 * <p>
 * KittyRun的电影剧本
 */

public class KittyRunSceenPlay extends ScreenPlay {

    private static final String TAG = KittyRunSceenPlay.class.getSimpleName();
    private KittyRunDirector mDirector;
    private Context mContext;
    private PreferenceUtils mPref;
    //开始了才接受外部消息
    private boolean isKittyRunStart;

    public KittyRunSceenPlay(KittyRunDirector director, Context context) {
        mDirector = director;
        mContext = context;
        mPref = new PreferenceUtils(context, PreferenceConstant.SHARE_PREF_FILE_NAME);
    }

    public void beginAction() {
        boolean isFirstGuide = mPref.getPrefBoolean(PreferenceConstant.PREF_KEY_IS_GUIDE, false);
        mDirector.performanceAction(new CountDownAction());

    }

    /**
     * 由外部调用，创建或者重复利用新的Story
     *
     * @param story
     */
    public synchronized void nextAction(Action action) {

    }

}
