package com.dreamliner.retrofit.sample.widgets;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamliner.retrofit.sample.R;
import com.dreamliner.rvhelper.loading.LoadingLayout;

/**
 * @author chenzj
 * @Title: DlLoadingLayout
 * @Description: 类的描述 -
 * @date 2016/10/9 22:57
 * @email admin@chenzhongjin.cn
 */
public class DlLoadingLayout extends LoadingLayout {

    private ImageView mLoadingIv;
    private TextView mLoadingTipTv;

    public DlLoadingLayout(Context context) {
        this(context, null);
    }

    public DlLoadingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DlLoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLoadingIv = (ImageView) findViewById(R.id.loading_iv);
        mLoadingTipTv = (TextView) findViewById(R.id.loading_tip_tv);
        mLoadingTipTv.setText("加载中…");
    }

    @Override
    public void onShowLoading() {
        ((AnimationDrawable) mLoadingIv.getDrawable()).start();
    }

    @Override
    public void onHideLoading() {
        ((AnimationDrawable) mLoadingIv.getDrawable()).stop();
    }
}
