package com.dreamliner.retrofit.sample.ui;

import android.os.Bundle;
import android.util.Log;

import com.dreamliner.retrofit.sample.R;
import com.dreamliner.retrofit.sample.databinding.ActMainBinding;
import com.dreamliner.retrofit.sample.entity.rsp.Device;
import com.dreamliner.retrofit.sample.entity.rsp.Login;
import com.dreamliner.retrofit.sample.net.DlObserve;
import com.dreamliner.retrofit.sample.net.NetManager;
import com.dreamliner.retrofit.sample.ui.base.BaseCompatActivity;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class MainActivity extends BaseCompatActivity<ActMainBinding> {

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_main);

        getDemoByNet();
        getDemoListByNet();
    }

    private void getDemoByNet() {
        NetManager.INSTANCE.getMockClient().demo()
                .compose(transformerOnIo())
                .subscribe(new DlObserve<Login>(this) {
                    @Override
                    public void onResponse(Login login) {
                        Log.i("TAG", login.toString());
                    }

                    @Override
                    public void onError(int errorCode, String errorMsg) {
                    }
                })
        ;
    }

    private void getDemoListByNet() {
        NetManager.INSTANCE.getMockClient().demoList()
                .compose(transformerArrayOnIo())
                .subscribe(new DlObserve<List<Device>>(this) {
                    @Override
                    public void onResponse(List<Device> devices) {
                        Log.i("TAG", devices.toString());
                    }

                    @Override
                    public void onError(int errorCode, String errorMsg) {
                    }
                });
    }
}
