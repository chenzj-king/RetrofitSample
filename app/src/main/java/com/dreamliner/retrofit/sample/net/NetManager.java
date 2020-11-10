package com.dreamliner.retrofit.sample.net;

import android.webkit.URLUtil;

import com.dreamliner.retrofit.sample.AppContext;
import com.dreamliner.retrofit.sample.BuildConfig;
import com.dreamliner.retrofit.sample.net.base.DlException;
import com.dreamliner.retrofit.sample.net.client.MockClient;
import com.dreamliner.retrofit.sample.util.AppUtil;

import java.util.concurrent.TimeUnit;

import lombok.Getter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dreamliner.retrofit.sample.net.base.ErrorCode.NET_DISABLE;


/**
 * @author chenzj
 * @Title: NetManager
 * @Description: 类的描述 -
 * @date 2017/3/1 16:21
 * @email admin@chenzhongjin.cn
 */
@Getter
public enum NetManager {

    INSTANCE;

    private MockClient mockClient;

    public void init() {
        initGovClient();
    }

    private void initGovClient() {
        OkHttpClient.Builder mOkHttpClient = new OkHttpClient.Builder();
        Retrofit.Builder mRetrofit = new Retrofit.Builder();
        setCommonSetting(mOkHttpClient, mRetrofit, BuildConfig.hostUrl);
        mockClient = mRetrofit.client(mOkHttpClient.build()).build().create(MockClient.class);
    }

    private void setCommonSetting(OkHttpClient.Builder okhttpBuilder, Retrofit.Builder retrofitBuilder, String hostUrl) {
        setCommonSetting(okhttpBuilder, retrofitBuilder, hostUrl, 10, 10, 10);
    }

    private void setCommonSetting(OkHttpClient.Builder okhttpBuilder, Retrofit.Builder retrofitBuilder, String hostUrl,
                                  int conTimeout, int writeTimeout, int readTimeout) {
        if (!URLUtil.isValidUrl(hostUrl)) {
            throw new IllegalArgumentException("please setup the validUrl");
        } else {
            retrofitBuilder.baseUrl(hostUrl);
        }
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        okhttpBuilder.connectTimeout(conTimeout, TimeUnit.SECONDS);
        okhttpBuilder.writeTimeout(writeTimeout, TimeUnit.SECONDS);
        okhttpBuilder.readTimeout(readTimeout, TimeUnit.SECONDS);

        //常用参数拦截器
        ParamsInterceptor paramsInterceptor = new ParamsInterceptor();
        okhttpBuilder.addInterceptor(paramsInterceptor);

        //日志拦截器
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        okhttpBuilder.addInterceptor(loggingInterceptor);

        okhttpBuilder.addInterceptor(chain -> {
            if (AppUtil.isNetworkAvailable(AppContext.getInstance())) {
                return chain.proceed(chain.request());
            } else {
                throw new DlException(NET_DISABLE, "网络连接失败，请开启您的网络连接，并重试！");
            }
        });
    }
}
