package com.fan.perfectteaching.netutil;

import com.fan.perfectteaching.beans.HomeBean;
import com.fan.perfectteaching.beans.LoginBean;
import com.fan.perfectteaching.beans.RegisterBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface MyWealthService {

    @GET("login")
    Observable<LoginBean> getLogin(@QueryMap Map<String, String> map);

    @GET("register")
    Observable<RegisterBean> getRegister(@QueryMap Map<String, String> map);

    @GET("labs")
    Observable<HomeBean> getLabs();

}