package com.hc.lab.kittyrun.base;

import android.graphics.Bitmap;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

/**
 * Created by hc on 2017/4/13 0013.
 */

public class BaseSprite extends CCSprite {
    protected CGSize screenSize;

    public BaseSprite(String filepath) {
        super(filepath);
        initSprite();
    }

    public BaseSprite(String filepath, CGRect rect) {
        super(filepath, rect);
        initSprite();
    }

    public BaseSprite(Bitmap image, String key) {
        super(image, key);
        initSprite();
    }

    public BaseSprite(CCTexture2D texture) {
        super(texture);
        initSprite();
    }

    public BaseSprite(CCTexture2D texture, CGRect rect) {
        super(texture, rect);
        initSprite();
    }

    private void initSprite() {
        screenSize = CCDirector.sharedDirector().screenSize();
    }

    @Override
    public CGSize getContentSize() {
        return CGSize.make(contentSize_.width * scaleX_, contentSize_.height * scaleY_);
    }
}
