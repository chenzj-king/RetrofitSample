package com.dreamliner.retrofit.sample.net.client;

import com.dreamliner.retrofit.sample.entity.TfhdResult;
import com.dreamliner.retrofit.sample.net.BaseResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by chenzj on 2017/4/11.
 */

public interface GovClient {

    @Multipart
    @POST("m/mvehxh/getTfhdList")
    Observable<BaseResponse<TfhdResult>> getTfhdList(@Part MultipartBody.Part... parts);
}
