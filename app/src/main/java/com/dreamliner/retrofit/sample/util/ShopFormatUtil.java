package com.dreamliner.retrofit.sample.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.DecimalFormat;

/**
 * Created by chenzj on 2017/4/6.
 */

public class ShopFormatUtil {

    public static String getPriceVal(double price) {
        if (price == 0) {
            return "免费";
        } else {
            return customFormat("¥0.##", price);
        }
    }

    @SuppressLint("DefaultLocale")
    public static String getDistanceVal(String storeAbb, int distance) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(storeAbb)) {
            stringBuilder.append(storeAbb).append(" ");
        } else {
            stringBuilder.append("");
        }
        if (distance == 0) {
            stringBuilder.append("");
        } else if (distance > 1000) {
            stringBuilder.append(String.format("%.1f", distance / 1000.0)).append("km");
        } else {
            stringBuilder.append(distance).append("m");
        }
        return stringBuilder.toString();
    }

    @SuppressLint("DefaultLocale")
    public static String getVisitCount(int visitCount) {
        if (visitCount > 10000) {
            return String.format("人气%dw", visitCount / 10000);
        } else {
            return String.format("人气%d", visitCount);
        }
    }

    public static String customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return output;
    }
}
