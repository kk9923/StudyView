package com.kx.studyview;

import android.app.Application;
import android.content.Context;

import com.kx.studyview.utils.LogUtils;

/**
 * Created by admin  on 2018/7/9.
 */
public class AndroidApplication extends Application {
    public static Context mContext ;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        LogUtils.init();
    }
    public static Context getContext(){
        return mContext;
    }
}
