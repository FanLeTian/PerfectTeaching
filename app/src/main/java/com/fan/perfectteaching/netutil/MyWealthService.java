package com.fan.perfectteaching.netutil;

import com.fan.perfectteaching.beans.HomeBean;
import com.fan.perfectteaching.beans.LabDetailBean;
import com.fan.perfectteaching.beans.LoginBean;
import com.fan.perfectteaching.beans.OrderBean;
import com.fan.perfectteaching.beans.RegisterBean;
import com.fan.perfectteaching.beans.SelectBean;
import com.fan.perfectteaching.beans.StatusBean;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface MyWealthService {

    @GET("login")
    Observable<LoginBean> getLogin(@QueryMap Map<String, String> map);

    @GET("register")
    Observable<RegisterBean> getRegister(@QueryMap Map<String, String> map);

    @GET("labs")
    Observable<HomeBean> getLabs();


    @GET("lab/{id}")
    Observable<LabDetailBean> getLabById(@Path("id") Integer id);

    @GET("update")
    Observable<StatusBean> setPersonInfo(@QueryMap Map<String, String> map);

    @GET("{userId}/{id}")
    Observable<SelectBean> selectLab(@Path("userId") String userId, @Path("id") Integer labId);

    @GET("labs/{id}")
    Observable<OrderBean> getAllOder(@Path("id") String id);

    @POST("/labs/commit")
    Observable<StatusBean> commit(HashMap<String, String> params);
}