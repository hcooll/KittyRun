package com.hc.lab.kittyrun;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.hc.lab.kittyrun.layer.RunLayer;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

public class MainActivity extends AppCompatActivity {

    // 导演
    KittyRunDirector director;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CCGLSurfaceView surfaceView = new CCGLSurfaceView(this);
        addContentView(surfaceView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        director = new KittyRunDirector(surfaceView);
        director.startingUp();

//        // 设置背景透明
//        surfaceView.setZOrderOnTop(true);
//        surfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
//        surfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
//
//        director = CCDirector.sharedDirector();
//        director.attachInView(surfaceView);// 开线程
//        director.setScreenSize(340, 680);
//        director.setDeviceOrientation(CCDirector.kCCDeviceOrientationPortrait);// 横屏
//        director.setDisplayFPS(true);// 不显示帧率
//        CCScene scene = CCScene.node();
//        scene.addChild(new RunLayer());
//        //导演管理场景
//        director.runWithScene(scene);


    }

    @Override
    protected void onResume() {
        director.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        director.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        director.end();
        super.onDestroy();
    }

}
