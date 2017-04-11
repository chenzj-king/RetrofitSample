package com.dreamliner.retrofit.sample.net.client;

import com.dreamliner.retrofit.sample.entity.netbody.shop.GetServiceBody;
import com.dreamliner.retrofit.sample.entity.shop.Good;
import com.dreamliner.retrofit.sample.net.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by chenzj on 2017/4/11.
 */

public interface ShopClient {
    @POST("sq580-store-api/goods/api/gethospitallist")
    Observable<BaseResponse<List<Good>>> getSocialShop(@Body GetServiceBody otherServiceBody);
}
