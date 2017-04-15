package com.hc.lab.kittyrun.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public PreferenceUtils(Context context, String file) {
        sp = context.getSharedPreferences(file, context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public String getPrefString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public void setPrefString(String key, String value) {
        editor.putString(key, value).commit();
    }

    public boolean getPrefBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public boolean hasKey(String key) {
        return sp.contains(key);
    }

    public void setPrefBoolean(String key, boolean value) {
        editor.putBoolean(key, value).commit();
    }

    public void setPrefInt(String key, int value) {
        editor.putInt(key, value).commit();
    }

    public int getPrefInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public void setPrefFloat(String key, float value) {
        editor.putFloat(key, value).commit();
    }

    public float getPrefFloat(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public void setPrefLong(String key, long value) {
        editor.putLong(key, value).commit();
    }

    public long getPrefLong(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    public void clearPreference() {
        editor.clear();
        editor.commit();
    }
}
