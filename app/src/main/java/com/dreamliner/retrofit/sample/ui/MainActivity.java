package com.dreamliner.retrofit.sample.ui;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dreamliner.loadmore.LoadMoreContainer;
import com.dreamliner.loadmore.LoadMoreHandler;
import com.dreamliner.ptrlib.PtrFrameLayout;
import com.dreamliner.retrofit.sample.R;
import com.dreamliner.retrofit.sample.databinding.ActMainBinding;
import com.dreamliner.retrofit.sample.entity.netbody.shop.GetServiceBody;
import com.dreamliner.retrofit.sample.entity.shop.Good;
import com.dreamliner.retrofit.sample.net.DlObserve;
import com.dreamliner.retrofit.sample.net.NetManager;
import com.dreamliner.retrofit.sample.net.NetUtil;
import com.dreamliner.retrofit.sample.net.PageWrapper;
import com.dreamliner.retrofit.sample.ui.base.BaseCompatActivity;
import com.dreamliner.retrofit.sample.util.PixelUtil;
import com.dreamliner.retrofit.sample.util.ValidateUtil;
import com.dreamliner.retrofit.sample.widgets.DlLoadMoreView;
import com.dreamliner.rvhelper.OptimumRecyclerView;
import com.dreamliner.rvhelper.adapter.BaseDBAdapter;
import com.dreamliner.rvhelper.adapter.BaseDataDBAdapter;
import com.dreamliner.rvhelper.interfaces.OnItemClickListener;
import com.dreamliner.rvhelper.interfaces.OnRefreshListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import static com.dreamliner.rvhelper.util.StatusConstant.NET_ERROR;

public class MainActivity extends BaseCompatActivity implements OnItemClickListener<Good>, View.OnClickListener
        , OnRefreshListener, LoadMoreHandler {

    private ActMainBinding mActMainBinding;

    private BaseDataDBAdapter<Good> mAdapter;
    private OptimumRecyclerView mOptimumRecyclerView;
    private int mPage = 1;

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        mActMainBinding = DataBindingUtil.setContentView(this, R.layout.act_main);

        mAdapter = new BaseDBAdapter<>(this, R.layout.item_db_service_shop);

        mOptimumRecyclerView = mActMainBinding.optimumRv;
        mOptimumRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mOptimumRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(Color.parseColor("#efeef3"))
                .size(PixelUtil.dp2px(1)).showLastDivider().build());
        mOptimumRecyclerView.setAdapter(mAdapter);
        mOptimumRecyclerView.setEmptyOnClick(this);

        mOptimumRecyclerView.setRefreshListener(this);
        mOptimumRecyclerView.getLoadMoreContainer().setItemLeftToLoadMore(1);
        mOptimumRecyclerView.setLoadMoreHandler(this, new DlLoadMoreView(this));

        getGoodsByNet(true);
    }

    private void getGoodsByNet(boolean isRefresh) {
        if (isRefresh) {
            mPage = 1;
        }
        NetManager.INSTANCE.getShopClient().getSocialShop(new GetServiceBody(String.valueOf(mPage)))
                .compose(bindToLifecycle())
                .compose(NetUtil.handleResultWithTotal())
                .subscribe(new DlObserve<PageWrapper<List<Good>>>() {
                    @Override
                    public void onResponse(PageWrapper<List<Good>> listPageWrapper) {
                        mPage++;
                        if (ValidateUtil.isValidate(listPageWrapper.getData())) {
                            if (mPage == 1) {
                                mAdapter.update(listPageWrapper.getData());
                            } else {
                                mAdapter.addAll(listPageWrapper.getData());
                            }
                        }
                        if (mAdapter.getData().size() >= listPageWrapper.getTotal()) {
                            mOptimumRecyclerView.loadMoreFinish(false, false);
                        } else {
                            mOptimumRecyclerView.loadMoreFinish(false, true);
                        }
                    }

                    @Override
                    public void onError(int errorCode, String errorMsg) {
                        mOptimumRecyclerView.setEmptyType(NET_ERROR);
                        mAdapter.clear();
                    }
                });
    }

    @Override
    public void onRefresh(PtrFrameLayout ptrFrameLayout) {
        getGoodsByNet(true);
    }

    @Override
    public void onLoadMore(LoadMoreContainer loadMoreContainer) {
        getGoodsByNet(false);
    }

    @Override
    public void onClick(View v) {
        mOptimumRecyclerView.showLoadingView();
        getGoodsByNet(true);
    }

    @Override
    public void onItemClick(View v, int position, Good good) {
        showToast("点击到条目" + (position + 1) + "\n" + good.toString());
    }
}
