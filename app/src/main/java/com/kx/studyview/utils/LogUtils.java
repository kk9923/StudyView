package com.kx.studyview.utils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by admin  on 2018/7/20.
 */
public class LogUtils {
    public static void init(){
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static void d(String message) {
        Logger.d(message);
    }


    public static void d(Object obj) {
        Logger.d(obj);
    }


    public static void e(String message) {
        Logger.e(message);

    }

    public static void i(String message) {
        Logger.i(message);

    }

    public static void v(String message) {
        Logger.v(message);

    }

    public static void w(String message) {
        Logger.w(message);
    }

    public static void t(String message) {
        Logger.t(message);
    }

    public static void wtf(String message) {
        Logger.wtf(message);
    }

    public static void xml(String message) {
        Logger.xml(message);
    }

    public static void json(String message) {
        Logger.json(message);

    }
}
