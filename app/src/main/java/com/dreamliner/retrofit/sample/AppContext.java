package com.dreamliner.retrofit.sample;

import android.app.Application;

import com.dreamliner.retrofit.sample.net.NetManager;
import com.dreamliner.retrofit.sample.util.PixelUtil;

/**
 * Created by chenzj on 2017/4/11.
 */

public class AppContext extends Application {

    public static AppContext mInstance;

    public static AppContext getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        //初始化shop的请求
        NetManager.INSTANCE.initShopClient();
        //初始化单位转换
        PixelUtil.init(this);
    }
}
