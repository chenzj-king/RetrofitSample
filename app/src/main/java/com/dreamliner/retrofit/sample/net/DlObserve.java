package com.dreamliner.retrofit.sample.net;

import com.dreamliner.retrofit.sample.net.base.BaseObserver;


/**
 * @author chenzj
 * @Title: DlObserve
 * @Description: 类的描述 -
 * @date 2017/3/1 16:18
 * @email admin@chenzhongjin.cn
 */
public abstract class DlObserve<T> extends BaseObserver<T> {

    @Override
    public void handleError(int errorCode, String errorMsg) {
        try {
            //需要进行一层全局的拦截.例如登录信息过期等全局弹框
            onError(errorCode, errorMsg);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            onAfter();
        }
    }

    @Override
    public void onNext(T t) {
        onResponse(t);
    }

    public void onAfter() {
    }

    public abstract void onResponse(T t);

    public abstract void onError(int errorCode, String errorMsg);
}
