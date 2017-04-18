package com.hc.lab.kittyrun.layer;

import android.util.Log;
import android.view.MotionEvent;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.action.KittyJumpAction;
import com.hc.lab.kittyrun.action.KittyWalkAction;
import com.hc.lab.kittyrun.action.LawnMoveAction;
import com.hc.lab.kittyrun.action.MileAction;
import com.hc.lab.kittyrun.base.BaseLayer;
import com.hc.lab.kittyrun.constant.SpriteConstant;
import com.hc.lab.kittyrun.listener.ActionStatusListener;
import com.hc.lab.kittyrun.screenplay.KittyRunSceenPlay;
import com.hc.lab.kittyrun.sprite.ComboSprite;
import com.hc.lab.kittyrun.sprite.CountdownSprite;
import com.hc.lab.kittyrun.sprite.ExitSprite;
import com.hc.lab.kittyrun.sprite.GiftContainerSprite;
import com.hc.lab.kittyrun.sprite.GiftSprite;
import com.hc.lab.kittyrun.sprite.KittySprite;
import com.hc.lab.kittyrun.sprite.LawnSprite;
import com.hc.lab.kittyrun.sprite.MileSprite;
import com.hc.lab.kittyrun.sprite.MoonSprite;
import com.hc.lab.kittyrun.sprite.RestartSprite;
import com.hc.lab.kittyrun.sprite.TrapSprite;
import com.hc.lab.kittyrun.strategy.KittyJumpStrategy;
import com.hc.lab.kittyrun.strategy.LawnStrategy;
import com.hc.lab.kittyrun.strategy.StrategyManager;
import com.hc.lab.kittyrun.util.CommonUtil;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

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
    StrategyManager mStrategyManager;
    //陷阱
    private LinkedList<TrapSprite> mTrapSpriteList;
    private LinkedList<GiftSprite> mGiftSpriteList;
    private LinkedList<LawnSprite> mLawnSpriteList;

    private ExitSprite mExitSprite;
    private RestartSprite mRestartSprite;
    private KittySprite mKittySpirite;
    private GiftContainerSprite mGiftContainerSpirite;
    private MileSprite mMileSprite;
    private MoonSprite mMoonSprite;
    private ComboSprite mComboSprite;
    private CountdownSprite mCountdownSprite;

    private LawnSprite mCurrentLawnSprite;
    private LawnSprite mPrevLawnSprite;

    private boolean isGameStarted = false;

    public KittyRunLayer(KittyRunSceenPlay sceenPlay) {
        setIsTouchEnabled(true);
        this.mSceenPlay = sceenPlay;
        mStrategyManager = StrategyManager.getInstance();
        mTrapSpriteList = new LinkedList<>();
        mGiftSpriteList = new LinkedList<>();
        mLawnSpriteList = new LinkedList<>();

        initLayerSprite();
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

    // 布置初始场景
    public void initLayerSprite() {
        mMileSprite = new MileSprite("image/mile/0.png");
        mMileSprite.setTag(SpriteConstant.SPRITE_TAG_MILE);
        addChild(mMileSprite);


        mCountdownSprite = new CountdownSprite("image/bounus/3.png");
        mCountdownSprite.setTag(SpriteConstant.SPRITE_TAG_COUNTDOWN);
        mCountdownSprite.setActionStatusListener(this);

        mCurrentLawnSprite = getNewLawnSprite(true);
        mCurrentLawnSprite.setTag(SpriteConstant.SPRITE_TAG_FIRST_LAWN);
        mCurrentLawnSprite.setActionStatusListener(this);

        //生成下一个LawnSprite
        mLawnSpriteList.add(getNewLawnSprite(false));
        addChild(mCurrentLawnSprite, 0);

        mKittySpirite = new KittySprite("image/kitty/run0000.png");
        mKittySpirite.setTag(SpriteConstant.SPRITE_TAG_KITTY);
        mKittySpirite.setPosition(cgSize.width / 4,
                mCurrentLawnSprite.getPosition().y + mCurrentLawnSprite.getContentSize().height);
        addChild(mKittySpirite);
    }

    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        Log.e(TAG, "cc touch began..");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (CommonUtil.isClicke(event, this, mExitSprite)) {
                    gameOver();
                } else if (CommonUtil.isClicke(event, this, mRestartSprite)) {
                    replayGame();
                } else if (isGameStarted) {
                    KittyJumpAction action = new KittyJumpAction();
                    KittyJumpStrategy kittyJumpStrategy = mStrategyManager.getKittyJumpStrategy(mMileSprite.getMiles(), mKittySpirite, mCurrentLawnSprite);
                    action.setStrategy(kittyJumpStrategy);
                    mKittySpirite.run(action);
                }
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
                // 添加游戏结束按钮
                mExitSprite = new ExitSprite("image/game_exit.png");
                addChild(mExitSprite, 1, SpriteConstant.SPRITE_TAG_EXIT);

                // 添加游戏重玩按钮
                mRestartSprite = new RestartSprite("image/game_restart.png");
                addChild(mRestartSprite, 1, SpriteConstant.SPRITE_TAG_RESTART);

                // 开始计算距离
                mMileSprite.run(new MileAction());

                //草坪开始移动
                mCurrentLawnSprite.run(mCurrentLawnSprite.getAction());

                //kitty开始走动
                KittyWalkAction kittyWalkAction = new KittyWalkAction();
                mKittySpirite.run(kittyWalkAction);

                //开启任务调度检测边界
                CCScheduler.sharedScheduler().schedule("checkBoundary", this, 0.05f, false);

                isGameStarted = true;
                break;
            case Action.TYPE_LAWN_MOVE:

                break;
        }
    }

    public LawnSprite getNewLawnSprite(boolean defaultLawn) {
        LawnStrategy lawnStrategy = mStrategyManager.getLawnActionStrategy(defaultLawn);
        Log.e(TAG, "getNewLawnSprite Pic:" + lawnStrategy.lawnPic);
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
        synchronized (this) {
            // 游戏难度设置
            mStrategyManager.setStrategyMode(mMileSprite.getMiles());

            //检测地面边界
            Log.e(TAG, "check boundary..." + Thread.currentThread());
            if (mCurrentLawnSprite.getPosition().x + mCurrentLawnSprite.getContentSize().width <= cgSize.width) {
                //草坪走完了，就得搞下一个草坪滚动，同时生成下一个草坪入队
                mPrevLawnSprite = mCurrentLawnSprite;

                mCurrentLawnSprite = mLawnSpriteList.poll();
                addChild(mCurrentLawnSprite, 0);
                mCurrentLawnSprite.run(mCurrentLawnSprite.getAction());
                mLawnSpriteList.add(getNewLawnSprite(false));
                Log.e(TAG, "boundary lawn sprite size" + mLawnSpriteList.size());
            }

            CGPoint kittyPoint = mKittySpirite.getPosition();
            CGSize kittySize = mKittySpirite.getContentSize();
            float kittyHeight = kittyPoint.y;
            float kittyPositionX = kittyPoint.x + kittySize.width / 2;
            float kittyRightX = kittyPoint.x + kittySize.width;

            CGPoint curLawnPoint = mCurrentLawnSprite.getPosition();
            CGSize curLawnSize = mCurrentLawnSprite.getContentSize();
            float currentLawnHeight = curLawnPoint.y + curLawnSize.height;
            float currentLawnLeftX = curLawnPoint.x;
            float currentLawnRightX = curLawnPoint.x + curLawnSize.width;
            // 检测区域1
            if (kittyPositionX >= currentLawnLeftX
                    && kittyPositionX <= currentLawnRightX
                    && kittyHeight >= currentLawnHeight) {
                // ok
                // Log.e(TAG, "okokok111 kittyPositionX" + kittyPositionX +
                // ",currentLawnLeftX" + currentLawnLeftX + ",currentLawnRightX" + currentLawnRightX);
                return;
            }
            if (mPrevLawnSprite != null) {
                CGPoint preLawnPoint = mPrevLawnSprite.getPosition();
                CGSize preLawnSize = mPrevLawnSprite.getContentSize();
                float prevLawnLeftX = preLawnPoint.x;
                float prevLawnRightX = preLawnPoint.x + preLawnSize.width;
                float prevLawnHeight = preLawnPoint.y + preLawnSize.height;
                // 检测区域2
                if (kittyPositionX >= prevLawnLeftX
                        && kittyPositionX <= prevLawnRightX
                        && kittyHeight >= prevLawnHeight) {
                    // ok
                    // Log.e(TAG, "okokok222 kittyPositionX2: " + kittyPositionX +
                    // ",prevLawnLeftX" + prevLawnLeftX + ",prevLawnRightX" + prevLawnRightX);
                    return;
                }
            }

            // 小人在往上跳，或者小人的高度在地面之上
            // 检测区域3
            if (mKittySpirite.isUp(kittyHeight)
                    || (mKittySpirite.isFlying() && kittyHeight >= currentLawnHeight)) {
                Log.e(TAG, "小人在往上跳，或者小人的高度在地面之上  height=" + kittyHeight + ",curent lawn height=" + currentLawnHeight);
                return;
            }

            // 小人还在飞的状态，而且也没有撞到墙上
            // 检测区域4
            if (mKittySpirite.isFlying()
                    && kittyRightX < currentLawnLeftX) {
                return;
            }

            gameOver();
        }
    }

    private void gameOver() {
        Log.e(TAG, "完蛋去死吧...");
        isGameStarted = false;
        unschedule("checkBoundary");
        mMileSprite.stopRunMile();
        //完蛋去死吧,game over
        mCurrentLawnSprite.stopMove();
        if (mPrevLawnSprite != null) {
            mPrevLawnSprite.stopMove();
        }
        mKittySpirite.fallDown();
        mSceenPlay.stopAction();
    }

    private void replayGame() {
        unschedule("checkBoundary");
        mStrategyManager.initStrategyMode();
        mLawnSpriteList.clear();
        removeAllChildren(true);

        initLayerSprite();
        mSceenPlay.reAction();
    }
}
