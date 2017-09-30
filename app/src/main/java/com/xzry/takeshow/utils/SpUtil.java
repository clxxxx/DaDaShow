package com.xzry.takeshow.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.xzry.takeshow.DaDaApplication;


/**
 * Created by 周东阳 on 2016/4/15.
 */
public class SpUtil {
    private static SpUtil INSTANCE;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public static SpUtil getInstance() {
        if(INSTANCE==null){
            synchronized (SpUtil.class){
                if(INSTANCE==null){
                    INSTANCE = new SpUtil();
                }
            }
        }
        return INSTANCE;
    }

    public SpUtil() {
        preferences = DaDaApplication.getInsatnce().getSharedPreferences("user", Context.MODE_PRIVATE);
//        preferences = PreferenceManager.getDefaultSharedPreferencesName()
//                PreferenceManager.getDefaultSharedPreferences();
    }

    public void putBoolean(String key, boolean value) {
        editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    public Boolean getBoolean(String key, Object defaultObject) {
       return preferences.getBoolean(key, (Boolean) defaultObject);
    }

    public void putFloat(String key, float value) {
        editor = preferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public float getFloat(String key, Object defaultObject) {
        return preferences.getFloat(key, (Float) defaultObject);
    }

    public void putInt(String key, int value) {
        editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public int getInt(String key, Object defaultObject) {
        return preferences.getInt(key, (Integer) defaultObject);
    }

    public void putLong(String key, long value) {
        editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }
    public long getLong(String key, Object defaultObject) {
        return preferences.getLong(key, (Long) defaultObject);
    }

    public void putString(String key, String value) {
        editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public String getString(String key, Object defaultObject) {
        return preferences.getString(key, (String) defaultObject);
    }

    public void remove(String key) {
        editor = preferences.edit();
        if (preferences.contains(key))
            editor.remove(key);
        editor.commit();
    }
    public void clear(){
        editor = preferences.edit();
        editor.clear().commit();
    }
}
