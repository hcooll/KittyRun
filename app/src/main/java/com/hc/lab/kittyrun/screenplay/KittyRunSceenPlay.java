package com.hc.lab.kittyrun.screenplay;

import android.content.Context;
import android.util.Log;

import com.hc.lab.kittyrun.KittyRunDirector;
import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.constant.PreferenceConstant;
import com.hc.lab.kittyrun.story.Story;
import com.hc.lab.kittyrun.story.StoryManager;
import com.hc.lab.kittyrun.util.PreferenceUtils;

import java.util.LinkedList;

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
    //电影剧本默认
    private StoryManager mStoryManager;
    private LinkedList<Story> mDefaultStories;
    //开始了才接受外部消息
    private boolean isKittyRunStart;

    public KittyRunSceenPlay(KittyRunDirector director, Context context) {
        mDirector = director;
        mContext = context;
        mPref = new PreferenceUtils(context, PreferenceConstant.SHARE_PREF_FILE_NAME);
        //取SharePref，看有没有引导过。。
        mStoryManager = StoryManager.getInstance(this);
    }


    @Override
    public void onActionStart(Action action) {
        switch (action.type) {
            case Action.TYPE_COUNT_DOWN:
                Log.e(TAG, "count down start");
                break;
            case Action.TYPE_WALK:
                Log.e(TAG, "kitty walk start");
                break;
        }
    }

    @Override
    public void onActionStop(Action action) {
        switch (action.type) {
            case Action.TYPE_COUNT_DOWN:
                Log.e(TAG, "count down stop");
                //执行 Kitty走的情节
                //mDirector.instructStory(mStoryManager.getKittyRunStory(false));
                break;

        }

    }

    public void beginSceenPlay() {
        boolean isFirstGuide = mPref.getPrefBoolean(PreferenceConstant.PREF_KEY_IS_GUIDE, false);
        Story beginStory = mStoryManager.getDefaultStory(isFirstGuide);
        Story newLawnStory = mStoryManager.getNewLawnStory();
        mDirector.instructStory(beginStory, newLawnStory, mStoryManager.getKittyRunStory(false));

    }

    /**
     * 由外部调用，创建或者重复利用新的Story
     *
     * @param story
     */
    public synchronized void nextStory(Story story) {

    }

}
