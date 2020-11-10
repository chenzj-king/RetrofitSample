package com.dreamliner.retrofit.sample.net.client;


import com.dreamliner.retrofit.sample.entity.rsp.Device;
import com.dreamliner.retrofit.sample.entity.rsp.Login;
import com.dreamliner.retrofit.sample.net.base.BaseRsp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface MockClient {

    @POST("/report/demo")
    Observable<BaseRsp<Login>> demo();

    @POST("/report/demoList")
    Observable<BaseRsp<List<Device>>> demoList();
}
