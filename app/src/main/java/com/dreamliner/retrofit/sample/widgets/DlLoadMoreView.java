package com.dreamliner.retrofit.sample.widgets;

import android.content.Context;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dreamliner.loadingdrawable.LoadingView;
import com.dreamliner.loadmore.LoadMoreContainer;
import com.dreamliner.loadmore.LoadMoreUIHandler;
import com.dreamliner.retrofit.sample.R;

/**
 * @author chenzj
 * @Title: DlLoadmoreView
 * @Description: 类的描述 -
 * @date 2016/10/9 22:18
 * @email admin@chenzhongjin.cn
 */
public class DlLoadMoreView extends RelativeLayout implements LoadMoreUIHandler {

    private LoadingView mLoadingView;
    private TextView mFooterTv;

    public DlLoadMoreView(Context context) {
        this(context, null);
    }

    public DlLoadMoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DlLoadMoreView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupViews();
    }

    private void setupViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_dl_footerview, this);
        mLoadingView = (LoadingView) findViewById(R.id.footer_indicatorview);
        mFooterTv = (TextView) findViewById(R.id.footer_tv);
    }

    @Override
    public void onLoading(LoadMoreContainer container) {
        setVisibility(VISIBLE);
        mLoadingView.setVisibility(VISIBLE);
        mFooterTv.setVisibility(VISIBLE);
        mFooterTv.setText("加载更多…");
    }

    @Override
    public void onLoadFinish(LoadMoreContainer container, boolean empty, boolean hasMore) {
        if (!hasMore) {
            if (empty) {
                setFooterTv(R.string.cube_views_load_more_loaded_empty);
            } else {
                setFooterTv("已经到底啦~");
            }
        } else {
            setVisibility(INVISIBLE);
        }
    }

    @Override
    public void onWaitToLoadMore(LoadMoreContainer container) {
        setFooterTv(R.string.cube_views_load_more_click_to_load_more);
    }

    @Override
    public void onLoadError(LoadMoreContainer container, int errorCode, String errorMessage) {
        if (errorCode == -1000) {
            setVisibility(GONE);
        } else {
            setFooterTv(R.string.cube_views_load_more_error);
        }
    }

    protected void setFooterTv(String stringStr) {
        setVisibility(VISIBLE);
        mLoadingView.setVisibility(GONE);
        mFooterTv.setVisibility(VISIBLE);
        mFooterTv.setText(stringStr);
    }

    protected void setFooterTv(@StringRes int stringId) {
        setVisibility(VISIBLE);
        mLoadingView.setVisibility(GONE);
        mFooterTv.setVisibility(VISIBLE);
        mFooterTv.setText(stringId);
    }
}
