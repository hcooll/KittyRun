package com.hc.lab.kittyrun.sprite;

import android.util.Log;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.action.TipsAction;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCFadeIn;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

/**
 * Created by hc on 2017/4/18 0018.
 */

public class TipsSprite extends ActionSprite {

    private CCLabel ccLabel;
    private String mTips;

    public TipsSprite(String string) {
        super(string);
        this.setAnchorPoint(0.0f, 0.0f);
        this.setPosition(screenSize.width / 2, screenSize.height / 2);
        this.setTextureRect(CGRect.zero());

        mTips = "";
        ccLabel = CCLabel.makeLabel(mTips,
                CGSize.make(screenSize.width / 4 * 3, screenSize.height / 3),
                CCLabel.TextAlignment.CENTER,
                "Roboto-Bold.ttf", 18.0f);
        ccLabel.setAnchorPoint(0.5f, 0.0f);
        ccLabel.setPosition(0.0f, 0.0f);
        ccLabel.setColor(ccColor3B.ccc3(122, 103, 182));//初始值
        this.addChild(ccLabel);
    }

    public void setString() {
        ccLabel.setString(mTips);
    }

    @Override
    public void run(Action action) {
        super.run(action);
        stopAllActions();
        unschedule("fadeout");
        TipsAction tipsAction = (TipsAction) action;
        mTips = tipsAction.tips;
        ccLabel.runAction(CCSequence.actions(CCFadeOut.action(0.3f), CCCallFunc.action(this, "setString"), CCFadeIn.action(0.3f)));
        if (tipsAction.isAutoDismiss) {
            CCScheduler.sharedScheduler().schedule("fadeout", this, 3.0f, false);
        }
    }

    public void fadeout(float t) {
        Log.e("FirstGuide", "提示文案自动消失");
        unschedule("fadeout"); // 结束上一次的定时任务
        ccLabel.runAction(CCSequence.actions(CCFadeOut.action(0.3f), CCCallFunc.action(this, "dismiss")));
    }

    public void dismiss() {
        stop();
    }

}
