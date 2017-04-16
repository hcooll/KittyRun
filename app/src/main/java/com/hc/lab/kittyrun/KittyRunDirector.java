package com.hc.lab.kittyrun;

import android.graphics.PixelFormat;

import com.hc.lab.kittyrun.action.Action;
import com.hc.lab.kittyrun.constant.DataConstant;
import com.hc.lab.kittyrun.layer.KittyRunLayer;
import com.hc.lab.kittyrun.screenplay.KittyRunSceenPlay;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

/**
 * Created by congwiny on 2017/4/14.
 * <p>
 * 大导演
 */

public class KittyRunDirector {
    KittyRunSceenPlay play;
    CCDirector director;
    KittyRunLayer kittyRunLayer;

    public KittyRunDirector(CCGLSurfaceView surfaceView) {
        director = CCDirector.sharedDirector();
        play = new KittyRunSceenPlay(this, surfaceView.getContext());
        initSetting(surfaceView);
    }

    public void initSetting(CCGLSurfaceView surfaceView) {
        if (surfaceView != null) {
            surfaceView.setZOrderOnTop(true);
            surfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
            surfaceView.getHolder().setFixedSize(DataConstant.WIDTH, DataConstant.HEIGHT);
            surfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);

            director.attachInView(surfaceView);// 开线程
            director.setScreenSize(DataConstant.WIDTH, DataConstant.HEIGHT);
            director.setDeviceOrientation(CCDirector.kCCDeviceOrientationPortrait);// 横屏
            director.setDisplayFPS(false);// 显示帧率

            kittyRunLayer = new KittyRunLayer(play);
            CCScene scene = CCScene.node();
            scene.addChild(kittyRunLayer);
            //导演管理场景
            director.runWithScene(scene);
        }
    }

    /**
     * 开机
     */
    public void startingUp() {
        play.beginAction();
    }


    public void performanceAction(Action... actions) {
        kittyRunLayer.performanceAction(actions);
    }

    public void onResume() {
        director.onResume();
    }

    public void onPause() {
        director.onPause();
    }

    public synchronized void end() {
        director.end();
    }
}
