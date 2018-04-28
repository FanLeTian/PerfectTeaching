package com.fan.perfectteaching.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * by y on 2017/5/28.
 */

public class SimpleData {


    public static List<SimpleBannerModel> initModel() {
        List<SimpleBannerModel> mDatas = new ArrayList<>();
        mDatas.add(new SimpleBannerModel("http://www.xatu.cn/images2016/banner2.png"));
        mDatas.add(new SimpleBannerModel("http://www.xatu.cn/images2016/banner5.png"));
        mDatas.add(new SimpleBannerModel("http://www.xatu.cn/images2016/banner8.png"));
        mDatas.add(new SimpleBannerModel("http://www.xatu.cn/images2016/banner3.png"));
        return mDatas;
    }

}