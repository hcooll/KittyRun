package com.hc.lab.kittyrun;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by congwiny on 2017/4/17.
 */

public class KittyRunApp extends Application{

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
