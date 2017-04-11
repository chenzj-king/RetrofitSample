package com.dreamliner.retrofit.sample.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.dreamliner.retrofit.sample.R;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by chenzj on 2017/3/8.
 */
public class DataBindingAdapter {

    @BindingAdapter(value = {"imageUrl", "defaultImg", "radius"}, requireAll = false)
    public static void setImage(ImageView imageView, String imageUrl, Drawable defaultImg, int radius) {
        DrawableRequestBuilder drawableTypeRequest = Glide.with(imageView.getContext()).load(imageUrl).crossFade();
        if (defaultImg != null) {
            drawableTypeRequest.placeholder(defaultImg).error(defaultImg);
        } else {
            drawableTypeRequest.placeholder(R.drawable.bg_defautl_imageview).error(R.drawable.bg_defautl_imageview);
        }
        if (radius > 0) {
            drawableTypeRequest.bitmapTransform(new CenterCrop(imageView.getContext()),
                    new RoundedCornersTransformation(imageView.getContext(), PixelUtil.dp2px(radius), 0));
        } else {
            drawableTypeRequest.bitmapTransform(new CenterCrop(imageView.getContext()));
        }
        drawableTypeRequest.into(imageView);
    }
}

