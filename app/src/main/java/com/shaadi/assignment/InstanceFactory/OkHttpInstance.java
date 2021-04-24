package com.shaadi.assignment.InstanceFactory;

import com.shaadi.assignment.Application.HeartBeatApplication;
import com.shaadi.assignment.R;

import okhttp3.OkHttpClient;

public class OkHttpInstance {

    private static volatile OkHttpClient okHttpInstance = null;

    private OkHttpInstance() {

        if (okHttpInstance != null) {
            throw new RuntimeException(""+ HeartBeatApplication.getAppInstance().getString(R.string.instance_error));
        }

    }


    public static OkHttpClient getOkHttpInstance() {

        if (okHttpInstance == null) {
            synchronized (OkHttpInstance.class) {
                if (okHttpInstance == null) {
                    okHttpInstance = new OkHttpClient.Builder().build();
                }
            }
        }

        return okHttpInstance;
    }

}
