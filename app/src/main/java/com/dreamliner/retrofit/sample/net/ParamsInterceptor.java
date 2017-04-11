package com.dreamliner.retrofit.sample.net;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenzj on 2017/3/2.
 */
public class ParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();

        long timeStamp = System.currentTimeMillis();
        String timestamp = String.valueOf(timeStamp);

        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(oldRequest.url())
                .headers(oldRequest.headers())
                .addHeader("appVersion", "1.0.0")//工具类自己获取
                .addHeader("osType", "2")
                .addHeader("timestamp", timestamp)
                .build();
        return chain.proceed(newRequest);
    }
}
