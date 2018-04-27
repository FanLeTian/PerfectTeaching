package com.fan.perfectteaching.netutil;

import com.fan.perfectteaching.Config;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;



public class MyWealthApi {
    private static volatile MyWealthApi instance = null;

    public MyWealthService getMyWealthService() {
        return myWealthService;
    }

    private MyWealthService myWealthService = null;

    private MyWealthApi() {
        Retrofit retrofit = buildRetrofit();
        myWealthService = retrofit.create(MyWealthService.class);
    }

    public static MyWealthApi getInstance() {
        MyWealthApi vInstance = instance;
        if (vInstance == null) {
            synchronized (MyWealthApi.class) {
                vInstance = instance;
                if (vInstance == null) {
                    vInstance = new MyWealthApi();
                    instance = vInstance;
                }
            }
        }
        return vInstance;
    }

    private Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Config.baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(buildOkHttpClient())
                .build();
    }

    private OkHttpClient buildOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .addInterceptor(logging)
                .build();
    }
}