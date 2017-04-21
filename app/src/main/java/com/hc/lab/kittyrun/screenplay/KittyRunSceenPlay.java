package com.hc.lab.kittyrun.screenplay;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.hc.lab.kittyrun.KittyRunDirector;
import com.hc.lab.kittyrun.action.CountDownAction;
import com.hc.lab.kittyrun.action.GiftEnterAction;
import com.hc.lab.kittyrun.action.ReAction;
import com.hc.lab.kittyrun.action.TipsAction;
import com.hc.lab.kittyrun.constant.PreferenceConstant;
import com.hc.lab.kittyrun.listener.OnGiftResLoadListener;
import com.hc.lab.kittyrun.model.GiftModel;
import com.hc.lab.kittyrun.model.GiftResMoel;
import com.hc.lab.kittyrun.strategy.GiftStrategy;
import com.hc.lab.kittyrun.strategy.StrategyManager;
import com.hc.lab.kittyrun.util.GlideWrapper;
import com.hc.lab.kittyrun.util.PreferenceUtils;

import java.util.LinkedList;

/**
 * Created by congwiny on 2017/4/14.
 * <p>
 * KittyRun的电影剧本
 */

public class KittyRunSceenPlay extends ScreenPlay implements OnGiftResLoadListener {

    private static final String TAG = KittyRunSceenPlay.class.getSimpleName();
    private KittyRunDirector mDirector;
    private Context mContext;
    private PreferenceUtils mPref;

    private static final int FIRST_GIFT = 111;
    private static final int SECOND_GIFT = 222;
    private static final int THRID_GIFT = 333;
    //开始了才接受外部消息
    private boolean isKittyRunStart;

    private Handler mhandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case FIRST_GIFT:
                    GiftModel giftModel1 = new GiftModel();
                    newGiftPlace(giftModel1);
                    break;
                case SECOND_GIFT:
                    GiftModel giftModel2 = new GiftModel();
                    giftModel2.setSecondGift();
                    newGiftPlace(giftModel2);
                    break;
                case THRID_GIFT:
                    GiftModel giftModel3 = new GiftModel();
                    giftModel3.setThirdGift();
                    newGiftPlace(giftModel3);
                    break;
            }
        }
    };

    private LinkedList<GiftModel> mGiftModelList;

    public KittyRunSceenPlay(KittyRunDirector director, Context context) {
        mDirector = director;
        mContext = context;
        mPref = new PreferenceUtils(context, PreferenceConstant.SHARE_PREF_FILE_NAME);
        mGiftModelList = new LinkedList<>();
    }

    public void beginAction() {
        boolean isFirstGuide = mPref.getPrefBoolean(PreferenceConstant.PREF_KEY_IS_GUIDE, true);
        Log.e("beginAction", "isFirstGuide: " + isFirstGuide);
        mDirector.performanceAction(new CountDownAction(isFirstGuide));

        mhandler.sendEmptyMessageDelayed(FIRST_GIFT, 1000);
        mhandler.sendEmptyMessageDelayed(SECOND_GIFT, 2000);
        mhandler.sendEmptyMessageDelayed(THRID_GIFT, 3000);
    }

    public void stopAction() {
        mDirector.shutDown();
    }

    public void reAction() {
        beginAction();
    }

    public void showReAction() {
        mDirector.performanceAction(new ReAction());
    }

    // 通过了新手引导
    public void clearanceTheGuide() {
        mPref.setPrefBoolean(PreferenceConstant.PREF_KEY_IS_GUIDE, false);
    }

    public void showTips(TipsAction action){
        mDirector.performanceAction(action);
    }

    //来了新的礼物
    public void newGiftPlace(GiftModel giftModel) {
        mGiftModelList.offer(giftModel);
        GlideWrapper.loadGiftRes(mContext, giftModel, this);
    }

    @Override
    public void onGiftResLoadSuccess(GiftResMoel giftResMoel, GiftModel giftModel) {
        generateGiftStragegy(giftResMoel, giftModel);
    }

    @Override
    public void onGiftResLoadFailed(Exception e, GiftModel giftModel) {
        for (GiftModel tmpGiftModel : mGiftModelList) {
            if (giftModel.equals(tmpGiftModel)) {
                mGiftModelList.remove(tmpGiftModel);
                break;
            }
        }
    }

    public void generateGiftStragegy(GiftResMoel giftResMoel, GiftModel giftModel) {
        for (GiftModel tmpGiftModel : mGiftModelList) {
            if (giftModel.equals(tmpGiftModel)) {
                mGiftModelList.remove(tmpGiftModel);
                GiftStrategy giftStrategy = StrategyManager.getInstance().getGiftStrategy(giftModel);
                giftStrategy.setGiftModel(giftModel);
                giftStrategy.setGiftResMoel(giftResMoel);
                Log.e(TAG, "gift res model gift bmp=" + giftResMoel.giftBmp);
                Log.e(TAG, "gift res model avatar bmp=" + giftResMoel.avatarBmp);
                GiftEnterAction giftEnterAction = new GiftEnterAction();
                giftEnterAction.setStrategy(giftStrategy);
                //发送礼物。。
                mDirector.performanceAction(giftEnterAction);
                break;
            }
        }
    }

}
