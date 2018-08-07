package com.dreamliner.retrofit.sample.net;

import android.os.Looper;
import android.text.TextUtils;

import com.afollestad.materialdialogs.MaterialDialog;
import com.dreamliner.retrofit.sample.net.base.BaseObserver;
import com.dreamliner.retrofit.sample.ui.base.BaseCompatActivity;

import java.lang.ref.WeakReference;


/**
 * @author chenzj
 * @Title: DlObserve
 * @Description: 类的描述 -
 * @date 2017/3/1 16:18
 * @email admin@chenzhongjin.cn
 */
public abstract class DlObserve<T> extends BaseObserver<T> {

    private WeakReference<BaseCompatActivity> mWeakReference;
    private MaterialDialog mMaterialDialog;
    private CharSequence mLoadingTips;
    private boolean isAutoShowToast;

    public DlObserve() {
    }

    public DlObserve(BaseCompatActivity baseCompatActivity) {
        mWeakReference = new WeakReference<>(baseCompatActivity);
    }

    public DlObserve(BaseCompatActivity baseCompatActivity, CharSequence loadingTips) {
        this(baseCompatActivity);
        this.mLoadingTips = loadingTips;
    }

    public DlObserve(BaseCompatActivity baseCompatActivity, boolean isAutoShowToast) {
        this(baseCompatActivity);
        this.isAutoShowToast = isAutoShowToast;
    }

    public DlObserve(BaseCompatActivity baseCompatActivity, CharSequence loadingTips, boolean isAutoShowToast) {
        this(baseCompatActivity);
        this.mLoadingTips = loadingTips;
        this.isAutoShowToast = isAutoShowToast;
    }

    @Override
    protected void onStart() {
        super.onStart();
        BaseCompatActivity baseCompatActivity = mWeakReference.get();
        if (null != baseCompatActivity && Looper.getMainLooper().getThread() == Thread.currentThread()
                && !TextUtils.isEmpty(mLoadingTips)) {
            mMaterialDialog = new MaterialDialog.Builder(baseCompatActivity)
                    .content(mLoadingTips)
                    .progress(true, 0)
                    .build();
            mMaterialDialog.show();
        }
    }

    @Override
    public void handleError(int errorCode, String errorMsg) {
        try {
            //需要进行一层全局的拦截.例如登录信息过期等全局弹框
            if (isAutoShowToast) {
                BaseCompatActivity baseCompatActivity = mWeakReference.get();
                baseCompatActivity.showToast(errorMsg);
            }
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
        onAfter();
    }

    public void onAfter() {
        if (null != mMaterialDialog && Looper.getMainLooper().getThread() == Thread.currentThread()) {
            mMaterialDialog.dismiss();
            mMaterialDialog = null;
        }
    }

    public abstract void onResponse(T t);

    public abstract void onError(int errorCode, String errorMsg);
}
