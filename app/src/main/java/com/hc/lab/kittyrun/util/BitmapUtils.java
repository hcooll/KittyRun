package com.hc.lab.kittyrun.util;

/**
 * Created by congwiny on 2017/4/17.
 */

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by kince on 17-2-6.
 * Bitmap基类工具类
 */
public class BitmapUtils {

    /**
     * 按照新的宽高缩放
     *
     * @param source
     * @param newHeight
     * @param newWidth
     * @return
     */
    public static Bitmap scaleCenterCrop(Bitmap source, int newHeight, int newWidth) {
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();

        // Compute the scaling factors to fit the new height and width, respectively.
        // To cover the final image, the final scaling will be the bigger
        // of these two.
        float xScale = (float) newWidth / sourceWidth;
        float yScale = (float) newHeight / sourceHeight;
        float scale = Math.max(xScale, yScale);

        // Now get the size of the source bitmap when scaled
        float scaledWidth = scale * sourceWidth;
        float scaledHeight = scale * sourceHeight;

        // Let's find out the upper left coordinates if the scaled bitmap
        // should be centered in the new size give by the parameters
        float left = (newWidth - scaledWidth) / 2;
        float top = (newHeight - scaledHeight) / 2;

        // The target rectangle for the new, scaled version of the source bitmap will now
        // be
        RectF targetRect = new RectF(left, top, left + scaledWidth, top + scaledHeight);
        // Finally, we create a new bitmap of the specified size and draw our new,
        // scaled bitmap onto it.
        Bitmap result = null;
        try {
            Bitmap dest = Bitmap.createBitmap(newWidth, newHeight, source.getConfig());
            Canvas canvas = new Canvas(dest);
            canvas.drawBitmap(source, null, targetRect, null);
            //原图回收
            //recycleBitmap(source);
        } catch (Error e) {
            e.printStackTrace();
        }
        if (result == null) {
            result = source;
        }
        return result;
    }

    public static RectF scaleCenterCropRect(int srcW, int srcH, int destW, int destH) {
        float xScale = (float) destW / srcW;
        float yScale = (float) destH / srcH;
        float scale = Math.max(xScale, yScale);

        // Now get the size of the source bitmap when scaled
        float sw = scale * srcW;
        float sh = scale * srcH;

        float left = (destW - sw) / 2;
        float top = (destH - sh) / 2;
        RectF result = new RectF();
        result.left = left;
        result.right = left + sw;
        result.top = top;
        result.bottom = top + sh;
        return result;
    }

    public static Bitmap decodeImageBytes(byte[] bytes, int width, int height) {
        Bitmap bmp = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new ByteArrayInputStream(bytes), null, options);
            options.inSampleSize = calculateInSampleSize(options, width, height);
            options.inJustDecodeBounds = false;
            bmp = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes), null, options);
        } catch (Error e) { //防OOM
            e.printStackTrace();
        }
        return bmp;
    }

    /**
     * 从文件中解析图片，并缩放到widthxheight
     *
     * @param filePath
     * @param width
     * @param height
     * @return
     */
    public static Bitmap decodeFile(String filePath, int width, int height) {
        Bitmap bmp = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            options.inSampleSize = calculateInSampleSize(options, width, height);
            options.inJustDecodeBounds = false;
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (Exception e) { //防OOM
            e.printStackTrace();
        }
        return bmp;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
                                            int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static Bitmap getCircleBitmap(Bitmap bitmap,float border) {
        int borderWidth = dpToPx(border);
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        final Rect rectBorder = new Rect(borderWidth / 2, borderWidth / 2, bitmap.getWidth() - borderWidth / 2, bitmap.getHeight() - borderWidth / 2);
        final RectF rectFBorder = new RectF(rectBorder);
        final Paint mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(Color.WHITE);
        mBorderPaint.setStrokeWidth(borderWidth);

        canvas.drawOval(rectFBorder, mBorderPaint);

        return output;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }

    public static Bitmap createBitmapSafely(int width, int height) {
        try {
            return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
        }
        return null;
    }

    /**
     * 保存图片
     *
     * @param file
     * @param bitmap
     * @param format
     * @param quality
     * @return
     */
    public static boolean saveBitmapToFile(File file, Bitmap bitmap, Bitmap.CompressFormat format, int quality) {
        if (bitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(format, quality, stream);

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write(stream.toByteArray());
                fos.flush();
            } catch (Exception e) {
                return false;
            } finally {
                try {
                    if (stream != null) {
                        stream.close();
                    }
                    if (fos != null) {
                        fos.close();
                    }
                } catch (Exception e) {
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 得到bitmap的大小
     */
    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {    //API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {//API 12
            return bitmap.getByteCount();
        }
        // 在低版本中用一行的字节x高度
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }

    /**
     * @param view
     * @return 当前View的bitmap数据
     */
    public static Bitmap clip(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, boolean shouldClip, int width) {
        int clipSize = 4;
        int w = shouldClip ? bitmap.getWidth() - (clipSize * 2) : bitmap.getWidth();
        int h = bitmap.getHeight();
        float scaleWidth = ((float) width / w);
        if (!shouldClip && scaleWidth == 1) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleWidth);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, shouldClip ? clipSize : 0, 0, w, h, matrix, true);
        bitmap.recycle();
        return newbmp;
    }

    /**
     * @param backBitmap
     * @param shouldClip
     * @param frontBitmap
     * @return 叠加
     */
    public static Bitmap mergeBitmap(Bitmap backBitmap, boolean shouldClip, Bitmap frontBitmap) {
        if (backBitmap == null || backBitmap.isRecycled()
                || frontBitmap == null || frontBitmap.isRecycled()) {
            return null;
        }
        backBitmap = zoomBitmap(backBitmap, shouldClip, frontBitmap.getWidth());
        int topOffset = (backBitmap.getHeight() - frontBitmap.getHeight()) / 2;
        Bitmap mergeBitmap;
        if (topOffset > 0) {
            mergeBitmap = Bitmap.createBitmap(backBitmap, 0, topOffset, frontBitmap.getWidth(), frontBitmap.getHeight());
            Canvas canvas = new Canvas(mergeBitmap);
            canvas.drawBitmap(frontBitmap, 0, 0, null);
        } else {
            mergeBitmap = Bitmap.createBitmap(backBitmap, 0, 0, backBitmap.getWidth(), backBitmap.getHeight());
            Canvas canvas = new Canvas(mergeBitmap);
            canvas.drawBitmap(frontBitmap, 0, topOffset, null);
        }
        return mergeBitmap;
    }

    /**
     * @param realImage
     * @param maxImageSize
     * @param filter       抗锯齿
     * @return
     */
    public static Bitmap scaleBitmapByRatio(Bitmap realImage, float maxImageSize,
                                            boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

}
