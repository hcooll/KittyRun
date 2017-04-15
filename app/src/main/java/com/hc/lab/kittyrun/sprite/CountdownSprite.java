package com.hc.lab.kittyrun.sprite;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.screenplay.ScreenPlay;
import com.hc.lab.kittyrun.story.Story;
import com.hc.lab.kittyrun.util.CommonUtil;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGRect;

/**
 * Created by congwiny on 2017/4/14.
 */

public class CountdownSprite extends ActionSprite {


    public CountdownSprite(String filepath) {
        super(filepath);
    }

    public CountdownSprite(String filepath, CGRect rect) {
        super(filepath, rect);
    }

    /**
     * 倒计时动画
     */
    private void countdown() {
        this.runAction(CCSequence.actions(
                CommonUtil.getAnimation(null, 3, "image/bounus/%01d.png", 1.0f),
                CCCallFunc.action(this, "startGame")));
    }


    @Override
    public void run(Action action) {
        super.run(action);
        countdown();
    }

    /**
     * 开始游戏
     */
    public void startGame() {
        // 没有作用了就销毁掉
        this.removeSelf();
        if (mActionListener != null) {
            mActionListener.onActionStop(mAction);
        }
    }
}
