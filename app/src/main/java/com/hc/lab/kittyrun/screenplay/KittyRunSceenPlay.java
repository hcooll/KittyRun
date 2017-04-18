package com.hc.lab.kittyrun.screenplay;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.hc.lab.kittyrun.KittyRunDirector;
import com.hc.lab.kittyrun.action.CountDownAction;
import com.hc.lab.kittyrun.action.GiftEnterAction;
import com.hc.lab.kittyrun.constant.PreferenceConstant;
import com.hc.lab.kittyrun.listener.OnGiftResLoadListener;
import com.hc.lab.kittyrun.model.GiftModel;
import com.hc.lab.kittyrun.model.GiftResMoel;
import com.hc.lab.kittyrun.strategy.GiftStrategy;
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
    //开始了才接受外部消息
    private boolean isKittyRunStart;

    private LinkedList<GiftModel> mGiftModelList;

    public KittyRunSceenPlay(KittyRunDirector director, Context context) {
        mDirector = director;
        mContext = context;
        mPref = new PreferenceUtils(context, PreferenceConstant.SHARE_PREF_FILE_NAME);
        mGiftModelList = new LinkedList<>();
    }

    public void beginAction() {
        boolean isFirstGuide = mPref.getPrefBoolean(PreferenceConstant.PREF_KEY_IS_GUIDE, false);
        mDirector.performanceAction(new CountDownAction());
        GiftModel giftModel = new GiftModel();
        newGiftPlace(giftModel);
    }

    public void stopAction() {
        mDirector.shutDown();
    }

    public void reAction() {
        beginAction();

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
                GiftStrategy giftStrategy = new GiftStrategy();
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
