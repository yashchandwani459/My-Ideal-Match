package com.shaadi.assignment.InstanceFactory;

import com.shaadi.assignment.Application.HeartBeatApplication;
import com.shaadi.assignment.R;
import com.shaadi.assignment.Utility.AppConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static volatile Retrofit retrofitInstance = null;

    private RetrofitInstance() {

        if (retrofitInstance != null) {
            throw new RuntimeException(""+ HeartBeatApplication.getAppInstance().getString(R.string.instance_error));
        }

    }


    public static Retrofit getRetrofitInstance() {

        if (retrofitInstance == null) {
            synchronized (RetrofitInstance.class) {
                if (retrofitInstance == null) {
                    retrofitInstance = new Retrofit.Builder()
                            .baseUrl(AppConstants.BASE_URL)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create(GsonInstance.getGsonInstance()))
                            .client(OkHttpInstance.getOkHttpInstance())
                            .build();
                }
            }
        }
        return retrofitInstance;

    }

}
