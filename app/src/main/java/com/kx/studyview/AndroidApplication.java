package com.kx.studyview;

import android.app.Application;
import android.content.Context;

/**
 * Created by admin  on 2018/7/9.
 */
public class AndroidApplication extends Application {
    public static Context mContext ;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
    public static Context getContext(){
        return mContext;
    }
}
