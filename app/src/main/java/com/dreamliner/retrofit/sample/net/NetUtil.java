package com.dreamliner.retrofit.sample.net;

import com.dreamliner.retrofit.sample.net.base.BaseCodeRsp;
import com.dreamliner.retrofit.sample.net.base.BaseRsp;
import com.dreamliner.retrofit.sample.net.base.DlException;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author chenzj
 * @Title: NetUtil
 * @Description: 类的描述 -
 * @date 2017/3/1 16:32
 * @email admin@chenzhongjin.cn
 */
public class NetUtil {

    public static <T extends BaseCodeRsp> ObservableTransformer<T, T> handleCodeOnIO() {
        try {
            return baseResponseObservable -> baseResponseObservable
                    .subscribeOn(Schedulers.io())
                    .flatMap(baseResponse -> {
                        //根据业务逻辑进行判断code等内容
                        if (baseResponse.getCode() == 200) {
                            return Observable.just(baseResponse);
                        } else {
                            return Observable.error(new DlException(baseResponse.getCode(), baseResponse.getDesc()));
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
            return baseResponseObservable -> baseResponseObservable
                    .subscribeOn(Schedulers.io())
                    .flatMap(baseResponse -> Observable.error(new Throwable("服务器错误")));
        }
    }

    public static <T> ObservableTransformer<BaseRsp<T>, T> handleResultOnIO() {
        return baseResponseObservable -> baseResponseObservable
                .compose(handleCodeOnIO())
                .flatMap(baseRsp -> {
                    if (null != baseRsp.getData()) {
                        return Observable.just(baseRsp.getData());
                    } else {
                        return Observable.empty();
                    }
                });
    }

    public static <T> ObservableTransformer<BaseRsp<T>, T> handleArrayResultOnIO() {
        return baseResponseObservable -> baseResponseObservable
                .compose(handleCodeOnIO())
                .flatMap(baseRsp -> {
                    if (null != baseRsp.getDataList()) {
                        return Observable.just(baseRsp.getDataList());
                    } else {
                        return Observable.empty();
                    }
                });
    }

    public static <T> ObservableTransformer<T, T> observeOnMain() {
        return observable -> observable.observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> ioToMain() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
