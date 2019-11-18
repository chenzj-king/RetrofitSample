package com.dreamliner.retrofit.sample.net;

import com.dreamliner.retrofit.sample.net.base.DlException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * @author chenzj
 * @Title: NetUtil
 * @Description: 类的描述 -
 * @date 2017/3/1 16:32
 * @email admin@chenzhongjin.cn
 */
public class NetUtil {

    public static <T> ObservableTransformer<BaseResponse<T>, BaseResponse<T>> handleResult() {
        try {
            return baseResponseObservable -> baseResponseObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(baseResponse -> {
                        if (baseResponse.getCode() == 200) {
                            if (baseResponse.getData() != null) {
                                return Observable.just(baseResponse);
                            } else {
                                //这种情况是没有data的情况下需要走onComplete来进行处理
                                return Observable.empty();
                            }
                        } else {
                            return Observable.error(new DlException(baseResponse.getCode(), baseResponse.getMessage()));
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            return baseResponseObservable -> baseResponseObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(baseResponse -> Observable.error(new Throwable("服务器错误")));
        }
    }

    public static MultipartBody.Part[] getPartByMap(String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            Map<String, String> map = new HashMap<>();
            for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
                String key = it.next();
                map.put(key, jsonObject.getString(key));
            }
            return getPartByMap(map);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MultipartBody.Part[] getPartByMap(Map<String, String> stringMap) {
        MultipartBody.Part[] parts = new MultipartBody.Part[stringMap.size()];
        int i = 0;
        for (String key : stringMap.keySet()) {
            parts[i++] = MultipartBody.Part.createFormData(key, stringMap.get(key));
        }
        return parts;
    }
}
