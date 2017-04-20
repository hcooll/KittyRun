package com.hc.lab.kittyrun.layer;

import android.content.Context;
import android.util.Log;

import com.hc.lab.kittyrun.R;
import com.hc.lab.kittyrun.action.GiftEnterAction;
import com.hc.lab.kittyrun.action.TipsAction;
import com.hc.lab.kittyrun.model.GiftModel;
import com.hc.lab.kittyrun.screenplay.KittyRunSceenPlay;
import com.hc.lab.kittyrun.sprite.GiftSprite;
import com.hc.lab.kittyrun.strategy.GiftStrategy;
import com.hc.lab.kittyrun.strategy.StrategyManager;

import java.util.LinkedList;

/**
 * Created by hc on 2017/4/20 0020.
 */

public class FirstGuideHelper {

    Context context;
    KittyRunSceenPlay kittyRunSceenPlay;

    boolean isFirstGuide = false;
    boolean isLoudlyTipsShow = false;
    boolean isLoudlyGreateTipsShow = false;
    boolean isGiftTipsShow = false;
    boolean isGiftGreateTipsShow = false;
    GiftSprite mGuideGiftSprite;

    public void init(KittyRunSceenPlay kittyRunSceenPlay, KittyRunLayer kittyRunLayer, Context context, boolean isFirstGuide) {
        this.context = context;
        this.kittyRunSceenPlay = kittyRunSceenPlay;
        this.isFirstGuide = isFirstGuide;
        this.isLoudlyTipsShow = false;
        this.isLoudlyGreateTipsShow = false;
        this.isGiftTipsShow = false;
        this.isGiftGreateTipsShow = false;
        generateGuideGift(kittyRunLayer, context);
    }

    // 跳过了第一个坑
    public void checkFirstTrip() {
        if (isFirstGuide) {
            if (!isLoudlyGreateTipsShow) {
                if (isLoudlyTipsShow) {
                    isLoudlyGreateTipsShow = true;
                    kittyRunSceenPlay.showTips(new TipsAction(context.getString(R.string.tips_guide_loudly_greate)));
                    Log.e("FirstGuide", "新手引导：跳过了第一个坑");
                }
            }
        }
    }

    // 引导玩家发出声音跳过第一个坑，之后再显示引导礼物
    public GiftSprite getGiftSprite(LinkedList<GiftSprite> attachedGiftSprite) {
        if (isFirstGuide) {
            if (!isLoudlyTipsShow) {
                isLoudlyTipsShow = true;
                // 出现新的草坪，提示玩家发出声音跳起来
                kittyRunSceenPlay.showTips(new TipsAction(context.getString(R.string.tips_guide_loudly)));
                Log.e("FirstGuide", "新手引导：出现新的草坪，提示玩家发出声音跳起来");
                return null;
            } else {
                isGiftTipsShow = true;
                Log.e("FirstGuide", "新手引导：出现引导礼物");
                mGuideGiftSprite.setVisible(true);
                return mGuideGiftSprite;
            }
        }
        return attachedGiftSprite.poll();
    }

    // 撞到礼物了
    public void checkGift() {
        if (isFirstGuide) {
            if (!isGiftGreateTipsShow) {
                if (isGiftTipsShow) {
                    isGiftGreateTipsShow = true;
                    // 通过了新手测试
                    isFirstGuide = false;
                    kittyRunSceenPlay.clearanceTheGuide();
                    mGuideGiftSprite.removeSelf();
                    kittyRunSceenPlay.showTips(new TipsAction(context.getString(R.string.tips_guide_gift_greate), true));
                    Log.e("FirstGuide", "新手引导：获得了新手礼物");
                }
            }
        }
    }

    // 游戏结束了，如果玩家没有通过引导，显示提示文案让玩家再试一次
    public void gameOver() {
        if (isFirstGuide) {
            if (!isLoudlyGreateTipsShow) {
                kittyRunSceenPlay.showTips(new TipsAction(context.getString(R.string.tips_guide_loudly_failure)));
            } else if (!isGiftGreateTipsShow) {
                kittyRunSceenPlay.showTips(new TipsAction(context.getString(R.string.tips_guide_gift_failure)));
            }
        }
    }

    // 生成引导礼物
    private void generateGuideGift(KittyRunLayer kittyRunLayer, Context context) {
        if (isFirstGuide && mGuideGiftSprite == null) {
            mGuideGiftSprite = getGuideGiftSprite(context);
            mGuideGiftSprite.setVisible(false);
            kittyRunLayer.addChild(mGuideGiftSprite, 1);
        }
    }


    private GiftSprite getGuideGiftSprite(Context context) {
        GiftModel giftModel = new GiftModel();
        giftModel.setGuideGift();
        GiftStrategy giftStrategy = StrategyManager.getInstance().getGiftStrategy(giftModel);
        giftStrategy.setGiftModel(giftModel);
        GiftSprite giftSprite = new GiftSprite("image/gift/guide.png");
        GiftEnterAction giftEnterAction = new GiftEnterAction();
        giftEnterAction.setStrategy(giftStrategy);
        giftSprite.setAction(giftEnterAction);
        return giftSprite;
    }

}
