package com.kx.studyview.utils;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.kx.studyview.AndroidApplication;

/**
 * Created by admin  on 2018/7/9.
 */
public class ToastUtils {
    private static Toast  toast ;
    public static  void showToast(@NonNull String message){
        if (toast ==null){
            toast = Toast.makeText(AndroidApplication.getContext(),message,Toast.LENGTH_SHORT);
        }
        toast.setText(message);
        toast.show();
    }
}
