package com.shaadi.assignment.InstanceFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shaadi.assignment.Application.HeartBeatApplication;
import com.shaadi.assignment.R;

public class GsonInstance {

    private static volatile Gson gsonInstance = null;

    private GsonInstance() {

        if (gsonInstance != null) {
            throw new RuntimeException(""+ HeartBeatApplication.getAppInstance().getString(R.string.instance_error));
        }

    }

    public static Gson getGsonInstance() {

        if (gsonInstance == null) {
            synchronized (GsonInstance.class) {
                if (gsonInstance == null) {
                    gsonInstance = new GsonBuilder().setLenient().create();
                }
            }
        }

        return gsonInstance;
    }

}
