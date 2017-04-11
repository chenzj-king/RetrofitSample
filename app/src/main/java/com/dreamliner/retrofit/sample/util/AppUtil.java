package com.dreamliner.retrofit.sample.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.dreamliner.retrofit.sample.AppContext;

import java.io.File;
import java.util.List;

/**
 * @author chenzj
 * @Title: AppConfig
 * @Description: 类的描述 - AppPrefs管理类
 * @date 2015/6/28
 * @email admin@chenzhongjin.cn
 */
public class AppUtil extends CommonUtil {

    private static String mdevicetype = "";
    private static String mplatform = "";
    private static String mdeviceId = "";


    public static void installationApk(Context context, File filePaths) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(filePaths), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void installationApk(Context context, String filePaths) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(filePaths), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void callPhone(Activity activity, String telStr) {
        if (!TextUtils.isEmpty(telStr)) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("tel:" + telStr));
                activity.startActivity(intent);
            } catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(activity, "该社区还没有登记电话号码!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(activity, "该社区还没有登记电话号码!", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> packages = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (packages != null) {
            for (int i = 0; i < packages.size(); i++) {
                String pn = packages.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static PackageInfo getPackageInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            return pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ApplicationInfo getApplicationInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getChanel(Context context) {
        return "dreamliner";
    }

    public static String getVersionName(Context context) {

        PackageInfo packageInfo = getPackageInfo(context);
        if (null != packageInfo) {
            return packageInfo.versionName;
        }
        return "";
    }

    public static int getVersionCode(Context context) {

        PackageInfo packageInfo = getPackageInfo(context);
        if (null != packageInfo) {
            return packageInfo.versionCode;
        }
        return 1;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion() {
        try {
            PackageManager manager = AppContext.getInstance().getPackageManager();
            PackageInfo info = manager.getPackageInfo(AppContext.getInstance().getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getDeviceType(Context context) {

        if (TextUtils.isEmpty(mdevicetype)) {
            getPhoneMes(context);
        }
        return TextUtils.isEmpty(mdevicetype) ? "" : mdevicetype;
    }


    public static String getPlatform(Context context) {
        if (TextUtils.isEmpty(mplatform)) {
            getPhoneMes(context);
        }
        return TextUtils.isEmpty(mplatform) ? "" : mplatform;
    }

    public static String getDeviceId(Context context) {
        if (TextUtils.isEmpty(mdeviceId)) {
            getPhoneMes(context);
        }
        return TextUtils.isEmpty(mdeviceId) ? "" : mdeviceId;
    }

    public static void getPhoneMes(Context context) {
        try {
            TelephonyManager mTm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            String mtype = Build.MODEL; // 手机型号
            String mtyb = Build.BRAND;//手机品牌
            mdevicetype = mtyb + "-" + mtype;

            String platform = Build.VERSION.RELEASE;//手机Android系统版本
            String display = Build.DISPLAY;//手机系统名称
            mplatform = "Android版本：" + platform + " 系统名称：" + display;

            mdeviceId = mTm.getDeviceId();//手机设备IME
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
