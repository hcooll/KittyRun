package com.hc.lab.kittyrun.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.hc.lab.kittyrun.constant.DataConstant;
import com.hc.lab.kittyrun.listener.OnGiftResLoadListener;
import com.hc.lab.kittyrun.model.GiftModel;
import com.hc.lab.kittyrun.model.GiftResMoel;

/**
 * Created by congwiny on 2017/4/17.
 */

public class GlideWrapper {

    public static final String TAG = GlideWrapper.class.getSimpleName();

    public static void loadGiftRes(final Context context, final GiftModel giftModel, final OnGiftResLoadListener loadListener) {

        float width = SizeConvertUtils.getConvertWidth(DataConstant.ORIGIN_GIFT_WIDTH);

        Glide.with(context)
                .load(giftModel.giftIcon)
                .asBitmap()
                .override((int) width, (int) width)
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {

                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                        //float width = SizeConvertUtils.getConvertWidth(DataConstant.ORIGIN_GIFT_WIDTH);
                        // Bitmap bmp = BitmapUtils.zoomBitmap(resource, (int) width, (int) width);
                        // Bitmap circleBmp = BitmapUtils.getCircleBitmap(bmp,1);
                        loadNextGiftRes(context, giftModel, loadListener, resource);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        Log.e(TAG, "load errror" + e);
                        if (loadListener != null) {
                            loadListener.onGiftResLoadFailed(e, giftModel);
                        }
                    }
                });
    }

    private static void loadNextGiftRes(final Context context,
                                        final GiftModel giftModel,
                                        final OnGiftResLoadListener loadListener,
                                        final Bitmap bitmap) {

        float width = SizeConvertUtils.getConvertWidth(DataConstant.ORIGIN_AVATAR_WIDTH);

        Glide.with(context)
                .load(giftModel.senderAvatar)
                .asBitmap()
                .override((int) width, (int) width)
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {

                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        final GiftResMoel giftResMoel = new GiftResMoel();

                        //Bitmap bmp = BitmapUtils.zoomBitmap(resource, (int) width, (int) width);
                        Bitmap circleBmp = BitmapUtils.getCircleBitmap(resource, 0f);
                        giftResMoel.avatarBmp = circleBmp;
                        giftResMoel.giftBmp = bitmap;
                        Log.e(TAG, "gift resource ready avatarBmp=" + giftResMoel.avatarBmp);
                        if (loadListener != null) {
                            loadListener.onGiftResLoadSuccess(giftResMoel, giftModel);
                        }
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        Log.e(TAG, "load errror" + e);
                        if (loadListener != null) {
                            loadListener.onGiftResLoadFailed(e, giftModel);
                        }
                    }
                });
    }


}
