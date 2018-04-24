package com.dreamliner.retrofit.sample.ui;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dreamliner.retrofit.sample.R;
import com.dreamliner.retrofit.sample.databinding.ActMainBinding;
import com.dreamliner.retrofit.sample.entity.Tfhd;
import com.dreamliner.retrofit.sample.entity.TfhdResult;
import com.dreamliner.retrofit.sample.entity.netbody.GetTfhdBody;
import com.dreamliner.retrofit.sample.net.DlObserve;
import com.dreamliner.retrofit.sample.net.NetManager;
import com.dreamliner.retrofit.sample.net.NetUtil;
import com.dreamliner.retrofit.sample.ui.base.BaseCompatActivity;
import com.dreamliner.retrofit.sample.util.PixelUtil;
import com.dreamliner.rvhelper.adapter.BaseDBAdapter;
import com.dreamliner.rvhelper.adapter.BaseDataDBAdapter;
import com.dreamliner.rvhelper.interfaces.OnItemClickListener;
import com.google.gson.Gson;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import static com.dreamliner.retrofit.sample.net.NetUtil.getPartByMap;

public class MainActivity extends BaseCompatActivity implements OnItemClickListener<Tfhd> {

    private ActMainBinding mBinding;

    private BaseDataDBAdapter<Tfhd> mAdapter;

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_main);

        mAdapter = new BaseDBAdapter<>(this, R.layout.item_db_tfhd);

        mBinding.optimumRv.setLayoutManager(new LinearLayoutManager(this));
        mBinding.optimumRv.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).color(Color.parseColor("#efeef3"))
                .size(PixelUtil.dp2px(1)).showLastDivider().build());
        mBinding.optimumRv.setAdapter(mAdapter);
        mBinding.optimumRv.setEmptyOnClick(v -> {
            mBinding.optimumRv.showLoadingView();
            getTfhdListByNet();
        });

        mBinding.optimumRv.setRefreshListener(ptrFrameLayout -> getTfhdListByNet());

        getTfhdListByNet();
    }

    private void getTfhdListByNet() {
        NetManager.INSTANCE.getGovClient().getTfhdList(getPartByMap(new Gson().toJson(new GetTfhdBody())))
                .compose(NetUtil.handleResult())
                .compose(bindToLifecycle())
                .subscribe(new DlObserve<TfhdResult>() {
                    @Override
                    public void onResponse(TfhdResult tfhdResult) {
                        mBinding.optimumRv.loadSuccess(tfhdResult.getList().getContent());
                    }

                    @Override
                    public void onError(int errorCode, String errorMsg) {
                        mBinding.optimumRv.loadFail();
                    }
                });
    }

    @Override
    public void onItemClick(View v, int position, Tfhd tfhd) {
        showToast("点击到条目" + (position + 1) + "\n" + tfhd.toString());
    }
}
