package com.bumslap.bum.POSproject;

import android.app.Activity;
import android.app.Application;

import com.kakao.auth.KakaoSDK;

/**
 * Created by bum on 12/8/17.
 */

public class GlobalApplicationActivity extends Application {
    private static volatile GlobalApplicationActivity obj = null;
    private static volatile Activity currentActivity = null;

    @Override
    public void onCreate() {
        super.onCreate();
        obj = this;
        KakaoSDK.init( new KakaoSDKAdapterActivity());

    }

    public static GlobalApplicationActivity getGlobalApplicationContext() {
        return obj;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    // Activity가 올라올때마다 Activity의 onCreate에서 호출해줘야한다.
    public static void setCurrentActivity(Activity currentActivity) {
        GlobalApplicationActivity.currentActivity = currentActivity;
    }
}



