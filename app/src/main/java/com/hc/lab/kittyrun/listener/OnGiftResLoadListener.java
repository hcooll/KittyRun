package com.hc.lab.kittyrun.listener;

import android.graphics.Bitmap;

import com.hc.lab.kittyrun.model.GiftModel;
import com.hc.lab.kittyrun.model.GiftResMoel;

/**
 * Created by congwiny on 2017/4/17.
 */

public interface OnGiftResLoadListener {
    void onGiftResLoadSuccess(GiftResMoel giftResMoel, GiftModel giftModel);

    void onGiftResLoadFailed(Exception e,GiftModel giftModel);
}
