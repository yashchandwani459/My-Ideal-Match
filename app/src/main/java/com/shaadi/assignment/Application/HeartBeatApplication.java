package com.shaadi.assignment.Application;

import android.app.Application;

public class HeartBeatApplication extends Application {

    static Application appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance=this;
    }

    public static Application getAppInstance(){
        return appInstance;
    }

}
