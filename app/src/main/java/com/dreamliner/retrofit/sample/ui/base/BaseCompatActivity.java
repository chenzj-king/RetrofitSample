package com.dreamliner.retrofit.sample.ui.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.dreamliner.easypermissions.AfterPermissionGranted;
import com.dreamliner.easypermissions.EasyPermissions;
import com.dreamliner.retrofit.sample.R;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.yokeyword.fragmentation.SupportActivity;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;


public abstract class BaseCompatActivity extends SupportActivity implements EasyPermissions.PermissionCallbacks,
        LifecycleProvider<ActivityEvent> {

    public UUID mUUID = UUID.randomUUID();

    protected boolean isCanBack = false;

    private boolean isFirstResume = true;

    protected int getLayoutId() {
        return 0;
    }

    protected abstract void initViews(@Nullable Bundle savedInstanceState);

    protected void initSpecialView(@Nullable Bundle savedInstanceState) {
    }

    protected void handleMes(Message msg) {
    }

    protected void getBundleExtras(Bundle extras) {
        isCanBack = extras.getBoolean("isCanBack");
    }

    public boolean doNetError(int errorCode, String errorMsg) {
        return false;
    }

    protected boolean isRegisterEvent() {
        return true;
    }

    protected boolean isRequestPermissionOnResume() {
        return true;
    }

    protected boolean isSuperOnKeyDown() {
        return false;
    }

    protected boolean isSuperOnResume() {
        return false;
    }


    public MyHandler mHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        super.onCreate(savedInstanceState);

        //兼容DataBinding的方式的时候就不需要设置了
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }

        // base setup
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }

        mHandler = new MyHandler(this);

        initSpecialView(savedInstanceState);
        initViews(savedInstanceState);
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    @CallSuper
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
        if (isRequestPermissionOnResume()) {
            checkPermission();
        } else {
            if (!isFirstResume) {
                checkPermission();
            } else {
                isFirstResume = false;
            }
        }

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        hideSoftInputView();
        EasyPermissions.hidePermissionsDialog();
    }

    @Override
    public void finish() {
        super.finish();
    }

    /**
     * startActivity
     *
     * @param clazz
     */
    public void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    public void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * startActivity then finish
     *
     * @param clazz
     */
    public void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    public void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivity with bundle then finish
     *
     * @param clazz
     * @param bundle
     */
    public void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    public void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    protected static class MyHandler extends Handler {

        private final WeakReference<BaseCompatActivity> mActivity;

        public MyHandler(BaseCompatActivity activity) {
            super(Looper.getMainLooper());
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseCompatActivity activity = mActivity.get();
            if (activity != null) {
                // do someThing
                activity.handleMes(msg);
            }
        }
    }

    protected Toast mToast;

    public Toast getToast() {
        return mToast;
    }

    public void showToast(final String msg) {
        showToast(msg, -1, LENGTH_SHORT);
    }

    public void showToast(@StringRes final int resId) {
        showToast(null, resId, LENGTH_SHORT);
    }

    public void showLongToast(final String msg) {
        showToast(msg, -1, LENGTH_LONG);
    }

    public void showLongToast(@StringRes final int resId) {
        showToast(null, resId, LENGTH_LONG);
    }

    private void showToast(final String msg, final @StringRes int resId, int length) {
        mHandler.post(() -> {
            if (mToast == null) {
                mToast = Toast.makeText(BaseCompatActivity.this, "", length);
            }
            mToast.setDuration(length);
            if (TextUtils.isEmpty(msg)) {
                mToast.setText(resId);
            } else {
                mToast.setText(msg);
            }
            mToast.show();
        });
    }

    public Snackbar showSnackBar(View contentView, String string) {
        Snackbar snackbar = Snackbar.make(contentView, string, Snackbar.LENGTH_SHORT);
        snackbar.show();
        return snackbar;
    }

    public Snackbar showSnackBar(View contentView, String string, String action, View.OnClickListener clickListener) {
        Snackbar snackbar = Snackbar.make(contentView, string, Snackbar.LENGTH_INDEFINITE).setAction(action, clickListener);
        snackbar.show();
        return snackbar;
    }

    public Snackbar showSnackBar(View contentView, String string, String action, View.OnClickListener clickListener, Snackbar
            .Callback callback) {
        Snackbar snackbar = Snackbar.make(contentView, string, Snackbar.LENGTH_INDEFINITE)
                .setAction(action, clickListener).setCallback(callback);
        snackbar.show();
        return snackbar;
    }

    public Snackbar showSnackBar(View contentView, String string, Snackbar.Callback callback) {
        Snackbar snackbar = Snackbar.make(contentView, string, Snackbar.LENGTH_SHORT).setCallback(callback);
        snackbar.show();
        return snackbar;
    }


    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /****************
     * 运行时权限管理
     ****************/
    public final static int RC_ALL_PERM = 0x100;
    private boolean isNeverAskAgain = false;

    private static String[] mPermissions = new String[]{};


    protected void doPermissionsSuc() {
    }

    public static void setmPermissions(String[] permissions) {
        mPermissions = permissions;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @AfterPermissionGranted(RC_ALL_PERM)
    protected void checkPermission() {

        String[] perms = mPermissions;

        if (EasyPermissions.hasPermissions(this, perms)) {
            doPermissionsSuc();
        } else {
            String[] deniedPermissions = EasyPermissions.getDeniedPermissions(this, perms);
            if (!isNeverAskAgain) {
                EasyPermissions.requestPermissions(this, getString(R.string.rationale_all), RC_ALL_PERM, deniedPermissions);
            } else {

                ArrayList<String> deniedList = new ArrayList<>();
                Collections.addAll(deniedList, deniedPermissions);
                isNeverAskAgain = EasyPermissions.checkDeniedPermissionsNeverAskAgain(this, getString(R.string.rationale_ask_again),
                        R.string.setting, android.R.string.cancel, deniedList);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (perms.size() == mPermissions.length) {
            doPermissionsSuc();
        } else {
            Log.e("BaseAct", "onPermissionsGranted: 有部分权限没有允许");
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        isNeverAskAgain = EasyPermissions.checkDeniedPermissionsNeverAskAgain(this, getString(R.string.rationale_ask_again),
                R.string.setting, android.R.string.cancel, perms);
    }

    /*****************
     * RxJavaCycle相关
     *****************/
    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }
}
