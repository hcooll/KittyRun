package com.hc.lab.kittyrun.layer;

import android.util.Log;
import android.view.MotionEvent;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.action.KittyJumpAction;
import com.hc.lab.kittyrun.action.KittyWalkAction;
import com.hc.lab.kittyrun.action.LawnMoveAction;
import com.hc.lab.kittyrun.base.BaseLayer;
import com.hc.lab.kittyrun.constant.DataConstant;
import com.hc.lab.kittyrun.constant.SpriteConstant;
import com.hc.lab.kittyrun.listener.ActionStatusListener;
import com.hc.lab.kittyrun.screenplay.KittyRunSceenPlay;
import com.hc.lab.kittyrun.sprite.ComboSprite;
import com.hc.lab.kittyrun.sprite.CountdownSprite;
import com.hc.lab.kittyrun.sprite.GiftContainerSprite;
import com.hc.lab.kittyrun.sprite.GiftSprite;
import com.hc.lab.kittyrun.sprite.KittySprite;
import com.hc.lab.kittyrun.sprite.LawnSprite;
import com.hc.lab.kittyrun.sprite.MileSprite;
import com.hc.lab.kittyrun.sprite.MoonSprite;
import com.hc.lab.kittyrun.sprite.TrapSprite;
import com.hc.lab.kittyrun.strategy.KittyJumpStrategy;
import com.hc.lab.kittyrun.strategy.LawnStrategy;
import com.hc.lab.kittyrun.strategy.StrategyManager;

import org.cocos2d.actions.CCScheduler;

import java.util.LinkedList;

/**
 * Created by congwiny on 2017/4/14.
 * <p>
 * 检测碰撞,监测礼物没有被碰撞，发送消息到底
 * <p>
 * 自身碰撞可检测，可操作，不用去向上请示，自己可以做处理！！
 * <p>
 * 检测没用的草坪，礼物，移除
 * <p>
 * Kitty碰撞监测。。
 * 1.检测和陷阱是不是落在先进范围内了
 * 2.跳跃高度和下一个草坪比，如果高度比下一个草坪高度小的话，先跳跃，后失败。
 * <p>
 * 正常oK的话，有个落脚点
 * <p>
 * 未知量，下一个草坪是哪个
 */

public class KittyRunLayer extends BaseLayer implements ActionStatusListener {

    private static final String TAG = KittyRunLayer.class.getSimpleName();
    KittyRunSceenPlay mSceenPlay;
    StrategyManager strategyManager;
    //陷阱
    private LinkedList<TrapSprite> mTrapSpriteList;
    private LinkedList<GiftSprite> mGiftSpriteList;
    private LinkedList<LawnSprite> mLawnSpriteList;

    private KittySprite mKittySpirite;
    private GiftContainerSprite mGiftContainerSpirite;
    private MileSprite mMileSprite;
    private MoonSprite mMoonSprite;
    private ComboSprite mComboSprite;
    private CountdownSprite mCountdownSprite;

    private LawnSprite mCurrentLawnSprite;
    private LawnSprite mPrevLawnSprite;


    public KittyRunLayer(KittyRunSceenPlay sceenPlay) {
        setIsTouchEnabled(true);
        this.mSceenPlay = sceenPlay;
        strategyManager = StrategyManager.getInstance();
        mTrapSpriteList = new LinkedList<>();
        mGiftSpriteList = new LinkedList<>();
        mLawnSpriteList = new LinkedList<>();

        initLayerSprite();
    }


    public void addNewLawnSprite(LawnSprite sprite) {
        mLawnSpriteList.offer(sprite);
    }


    /**
     * 执行一个动作，动作里有具体策略
     * 对已经初始化的精灵操作
     *
     * @param action
     */
    public void performanceAction(Action... actionArray) {
        for (Action action : actionArray) {
            switch (action.type) {
                case Action.TYPE_COUNT_DOWN:
                    if (getChildByTag(SpriteConstant.SPRITE_TAG_COUNTDOWN) == null) {
                        addChild(mCountdownSprite);
                    }
                    mCountdownSprite.setAction(action);
                    mCountdownSprite.run(action);
                    break;
                case Action.TYPE_LAWN_MOVE:

                    break;
                case Action.TYPE_KITTY_WALK:
                    break;

            }
        }
    }

    //布置场景
    public void initLayerSprite() {
        mKittySpirite = new KittySprite("image/kitty/run0000.png");
        mKittySpirite.setTag(SpriteConstant.SPRITE_TAG_KITTY);

        mCountdownSprite = new CountdownSprite("image/bounus/3.png");
        mCountdownSprite.setTag(SpriteConstant.SPRITE_TAG_COUNTDOWN);
        mCountdownSprite.setActionStatusListener(this);

        mCurrentLawnSprite = getNewLawnSprite(true);
        mCurrentLawnSprite.setTag(SpriteConstant.SPRITE_TAG_FIRST_LAWN);
        mCurrentLawnSprite.setActionStatusListener(this);

        //生成下一个LawnSprite
        mLawnSpriteList.add(getNewLawnSprite(false));
        mLawnSpriteList.add(getNewLawnSprite(false));
        addChild(mCurrentLawnSprite, 0);

    }

    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        Log.e(TAG, "cc touch began..");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                KittyJumpAction action = new KittyJumpAction();

                KittyJumpStrategy kittyJumpStrategy = strategyManager
                        .getKittyJumpStrategy(0, mKittySpirite.getPosition(),
                                mCurrentLawnSprite.getPosition().y + mCurrentLawnSprite.getContentSize().height);
                action.setStrategy(kittyJumpStrategy);
                mKittySpirite.run(action);
                break;
        }

        return super.ccTouchesBegan(event);
    }


    @Override
    public void onActionStart(Action action) {

    }

    @Override
    public void onActionStop(Action action) {
        if (action == null) {
            throw new RuntimeException("action can not be null");
        }
        switch (action.type) {
            case Action.TYPE_COUNT_DOWN:
                //草坪开始移动
                mCurrentLawnSprite.run(mCurrentLawnSprite.getAction());

                mKittySpirite.setPosition(cgSize.width / 4, mCurrentLawnSprite.getContentSize().height);
                //倒计时完了Kitty开始跑了
                if (getChildByTag(SpriteConstant.SPRITE_TAG_KITTY) == null) {
                    addChild(mKittySpirite, 1);
                }
                //kitty开始走动
                KittyWalkAction kittyWalkAction = new KittyWalkAction();
                mKittySpirite.run(kittyWalkAction);

                //开启任务调度检测边界
                CCScheduler.sharedScheduler().schedule("checkBoundary", this, 0.05f,
                        false);
                break;
            case Action.TYPE_LAWN_MOVE:

                break;

        }

    }


    public LawnSprite getNewLawnSprite(boolean defaultLawn) {
        LawnStrategy lawnStrategy = strategyManager.getLawnActionStrategy(defaultLawn);
        LawnSprite spirite = new LawnSprite(lawnStrategy.lawnPic);
        spirite.setAnchorPoint(lawnStrategy.anchor);
        spirite.setPosition(lawnStrategy.position);
        spirite.setActionStatusListener(this);
        LawnMoveAction moveAction = new LawnMoveAction();
        moveAction.setStrategy(lawnStrategy);
        spirite.setAction(moveAction);
        return spirite;
    }

    public void checkBoundary(float t) {
        synchronized (KittyRunLayer.class) {

            Log.e(TAG, "check boundary..." + Thread.currentThread());
            //检测自身边界
            if (mCurrentLawnSprite.getPosition().x + mCurrentLawnSprite.getContentSize().width <= cgSize.width) {
                //草坪走完了，就得搞下一个草坪滚动，同时生成下一个草坪入队
                mPrevLawnSprite = mCurrentLawnSprite;

                mCurrentLawnSprite = mLawnSpriteList.poll();
                addChild(mCurrentLawnSprite, 0);
                mCurrentLawnSprite.run(mCurrentLawnSprite.getAction());
                mLawnSpriteList.add(getNewLawnSprite(false));
                Log.e(TAG, "boundary lawn sprite size" + mLawnSpriteList.size());
            }

            float currentLawnLeftX = mCurrentLawnSprite.getPosition().x;
            float currentLawnRightX = mCurrentLawnSprite.getPosition().x + mCurrentLawnSprite.getContentSize().width;
            float kittyPositionX = mKittySpirite.getPosition().x + mKittySpirite.getContentSize().width / 2;
            if (kittyPositionX >= currentLawnLeftX && kittyPositionX <= currentLawnRightX) {
                //ok
                Log.e(TAG, "okokok111 kittyPositionX" + kittyPositionX +
                        ",currentLawnLeftX" + currentLawnLeftX + ",currentLawnRightX" + currentLawnRightX);
                return;
            }


            if (mPrevLawnSprite != null) {
                float prevLawnLeftX = mPrevLawnSprite.getPosition().x;
                float prevLawnRightX = mPrevLawnSprite.getPosition().x + mPrevLawnSprite.getContentSize().width;
                float kittyPositionX2 = mKittySpirite.getPosition().x + mKittySpirite.getContentSize().width / 2;
                if (kittyPositionX2 >= prevLawnLeftX && kittyPositionX2 <= prevLawnRightX) {
                    //ok
                    Log.e(TAG, "okokok222 kittyPositionX2: " + kittyPositionX2 +
                            ",prevLawnLeftX" + prevLawnLeftX + ",prevLawnRightX" + prevLawnRightX);
                    return;
                }
            }


            float prevLawnRightX2 = mPrevLawnSprite.getPosition().x + mPrevLawnSprite.getContentSize().width;
            float currentLawnLeftX2 = mCurrentLawnSprite.getPosition().x;
            float kittyPositionX3 = mKittySpirite.getPosition().x + mKittySpirite.getContentSize().width / 2;
            if (kittyPositionX3 > prevLawnRightX2 && kittyPositionX3 < currentLawnLeftX2) {
                float currentLawnHeight = mCurrentLawnSprite.getPosition().y + mCurrentLawnSprite.getContentSize().height;
                float prevLawnHeight = mPrevLawnSprite.getPosition().y + mPrevLawnSprite.getContentSize().height;
                float kittyHeight = mKittySpirite.getPosition().y;
                if (kittyHeight > currentLawnHeight && kittyHeight > prevLawnHeight) {
                    Log.e(TAG, "kitty height=" + kittyHeight + ",curent lawn height=" + currentLawnHeight);
                    return;
                }
            }

            gameOver();
        }

    }

    private void gameOver() {
        //完蛋去死吧,game over
        mCurrentLawnSprite.stopMove();
        mPrevLawnSprite.stopMove();
        Log.e(TAG, "完蛋去死吧...");
        mKittySpirite.fallDown();
        unschedule("checkBoundary");
    }

}
